package com.jitterted.overlay.webhook;

import com.jitterted.overlay.webhook.trello.TrelloPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class TrelloWebhookController {

  public static final Logger LOGGER = LoggerFactory.getLogger(TrelloWebhookController.class);

  private static final String TRELLO_WEBHOOK_REGISTRATION_URL = "https://api.trello.com/1/webhooks"; // 1/tokens/{token}/webhooks/";

  private final RestTemplate restTemplate = new RestTemplate();

  private final TrelloConfig trelloConfig;
  private final WebhookNotifier webhookNotifier;

  @Autowired
  public TrelloWebhookController(TrelloConfig trelloConfig, WebhookNotifier webhookNotifier) {
    this.trelloConfig = trelloConfig;
    this.webhookNotifier = webhookNotifier;
  }

  @GetMapping("/")
  public String homePage(Model model) {
    ResponseEntity<TrelloWebhook[]> webhookEntity = restTemplate
        .getForEntity("https://api.trello.com/1/tokens/{token}/webhooks?key={key}",
                      TrelloWebhook[].class,
                      trelloConfig.getApitoken(),
                      trelloConfig.getApikey());

    List<TrelloWebhook> webhookList = Arrays.asList(webhookEntity.getBody());
    model.addAttribute("webhooks", webhookList);

    return "index";
  }

  @PostMapping(path = "/register", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
  public String registerTrelloWebhook(
      WebhookRegistrationRequest registrationRequest,
      WebRequest webRequest,
      RedirectAttributes redirectAttributes) {
    String origin = extractHostFrom(webRequest);
    String callbackUrl = origin + "/trello/" + registrationRequest.getWebhookName();

    try {
      ResponseEntity<TrelloWebhookRegistrationResponse> response =
          registerTrelloWebhook(callbackUrl, registrationRequest.getIdToWatch());

      LOGGER.info("Registration response from Trello: status = {}, body = '{}'",
                  response.getStatusCode(),
                  response.getBody());

      redirectAttributes.addFlashAttribute("success", response.getBody());
    } catch (HttpClientErrorException ex) {
      redirectAttributes.addFlashAttribute("error", ex.getResponseBodyAsString() + " (Status = " + ex.getStatusCode() + " )");
    }

    return "redirect:/registered";
  }

  private String extractHostFrom(WebRequest webRequest) {
    String origin = webRequest.getHeader(HttpHeaders.ORIGIN);
    if (origin != null && origin.contains("localhost")) {
      origin = "https://4cae77f50ec5.ngrok.io"; // TODO: tell user they should be going thru ngrok
    }
    return origin;
  }

  @PostMapping("/delete")
  public String deleteWebhook(@RequestParam("webhookId") String webhookId) {

    String deleteUrl = "https://api.trello.com/1/webhooks/{webhookId}?key={key}&token={token}";
    var uriVariables = Map.of("webhookId", webhookId,
                              "key", trelloConfig.getApikey(),
                              "token", trelloConfig.getApitoken());

    restTemplate.delete(deleteUrl, uriVariables);

    return "redirect:/deleted";
  }

  @GetMapping("/deleted")
  public String deletedWebhook() {
    return "deleted";
  }

  private ResponseEntity<TrelloWebhookRegistrationResponse> registerTrelloWebhook(String callbackUrl, String idToWatch) {
    LOGGER.info("Registering webhook for Trello with callback URL={}", callbackUrl);

    TrelloWebhookRegistrationRequest request =
        new TrelloWebhookRegistrationRequest(trelloConfig,
                                             callbackUrl,
                                             idToWatch,
                                             "Webhook for Overlay");

    return restTemplate
        .postForEntity(TRELLO_WEBHOOK_REGISTRATION_URL,
                       request,
                       TrelloWebhookRegistrationResponse.class);
  }

  @GetMapping("/registered")
  public String registrationCompleted(Model model) {
    return "registered";
  }

  @RequestMapping(method = RequestMethod.HEAD, path = "/trello/{callbackName}")
  public ResponseEntity<?> trelloWebhookVerification(@PathVariable("callbackName") String callbackName) {
    LOGGER.info("Received HEAD request from Trello for callback='{}'", callbackName);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/trello/{callbackName}")
  public ResponseEntity<?> trelloWebhook(RequestEntity<TrelloPayload> payload,
                                         @PathVariable("callbackName") String callbackName) {
    String idForModelBeingWatched = payload.getBody().getModel().getId();
    LOGGER.info("Trello Payload, model id = {}", idForModelBeingWatched);
    TrelloEvent trelloEvent = new TrelloEvent(callbackName, idForModelBeingWatched);
    webhookNotifier.sendTrelloEvent(trelloEvent);
    LOGGER.info("Trello event sent via websocket.");
    return ResponseEntity.ok().build();
  }
}
