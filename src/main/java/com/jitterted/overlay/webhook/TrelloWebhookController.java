package com.jitterted.overlay.webhook;

import com.jitterted.overlay.webhook.trello.TrelloPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
public class TrelloWebhookController {

  public static final Logger LOGGER = LoggerFactory.getLogger(TrelloWebhookController.class);

  private static final String TRELLO_MODEL_ID_TO_WATCH = "5ee298bac53199290301955a";
  private static final String TRELLO_WEBHOOK_REGISTRATION_URL = "https://api.trello.com/1/webhooks"; // 1/tokens/{token}/webhooks/";

  private final RestTemplate restTemplate = new RestTemplate();

  private final TrelloConfig trelloConfig;
  private final WebhookNotifier webhookNotifier;

  @Autowired
  public TrelloWebhookController(TrelloConfig trelloConfig, WebhookNotifier webhookNotifier) {
    this.trelloConfig = trelloConfig;
    this.webhookNotifier = webhookNotifier;
//    restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
//      @Override
//      public boolean hasError(ClientHttpResponse response) throws IOException {
//        return false;
//      }
//    });
  }

  @PostMapping("/register")
  public String registerTrelloWebhook(RequestEntity<String> requestEntity,
                                      RedirectAttributes redirectAttributes) {
    String origin = requestEntity.getHeaders().getOrigin();
    if (origin.contains("localhost")) {
      origin = "https://052f12294ec0.ngrok.io";
    }
    String callbackUrl = origin + "/trello";

    try {
      ResponseEntity<TrelloWebhookRegistrationResponse> response =
          registerTrelloWebhook(callbackUrl);

      LOGGER.info("Registration response from Trello: status = {}, body = '{}'",
                  response.getStatusCode(),
                  response.getBody());

      redirectAttributes.addFlashAttribute("success", response.getBody());
    } catch (HttpClientErrorException ex) {
      redirectAttributes.addFlashAttribute("error", ex.getResponseBodyAsString() + " (Status = " + ex.getStatusCode() + " )");
    }

    return "redirect:/registered";
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

  private ResponseEntity<TrelloWebhookRegistrationResponse> registerTrelloWebhook(String callbackUrl) {
    LOGGER.info("Registering webhook for Trello with callback URL={}", callbackUrl);

    TrelloWebhookRegistrationRequest request =
        new TrelloWebhookRegistrationRequest(trelloConfig,
                                             callbackUrl,
                                             TRELLO_MODEL_ID_TO_WATCH,
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

  @RequestMapping(method = RequestMethod.HEAD, path = "/trello")
  public ResponseEntity<?> trelloWebhookVerification() {
    LOGGER.info("Received HEAD request from Trello");
    return ResponseEntity.ok().build();
  }

  @PostMapping("/trello")
  public ResponseEntity<?> trelloWebhook(RequestEntity<TrelloPayload> payload) {
    LOGGER.info("Trello Payload, Card = {}", payload.getBody().getAction().getData().getCard());
    webhookNotifier.sendTrelloEvent();
    LOGGER.info("Trello event sent via websocket.");
    return ResponseEntity.ok().build();
  }
}
