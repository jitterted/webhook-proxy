package com.jitterted.overlay.webhook;

import com.jitterted.overlay.webhook.trello.TrelloPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Controller
public class TrelloWebhookController {

  private static final String TRELLO_MODEL_ID_TO_WATCH = "5ee298bac53199290301955a";
  private static final String TRELLO_WEBHOOK_REGISTRATION_URL = "https://api.trello.com/1/tokens/{token}/webhooks/";

  private final RestTemplate restTemplate = new RestTemplate();

  private final TrelloConfig trelloConfig;

  @Autowired
  public TrelloWebhookController(TrelloConfig trelloConfig) {
    this.trelloConfig = trelloConfig;
  }

  @PostMapping("/register")
  public String registerTrelloWebhook() {
    TrelloWebhookRegistrationRequest request =
        new TrelloWebhookRegistrationRequest(trelloConfig.getApikey(),
                                             "https://052f12294ec0.ngrok.io/trello",
                                             TRELLO_MODEL_ID_TO_WATCH,
                                             "Webhook for Overlay");
    ResponseEntity<Map> response = restTemplate
        .postForEntity(TRELLO_WEBHOOK_REGISTRATION_URL,
                       request,
                       Map.class,
                       Map.of("token", trelloConfig.getApitoken()));
    System.out.println(response);
    if (!response.getStatusCode().is2xxSuccessful()) {
      return "redirect:/";
    }
    return "redirect:/registered";
  }

  @GetMapping("/registered")
  public String registrationCompleted() {
    return "registered.html";
  }

  @RequestMapping(method = RequestMethod.HEAD, path="/trello")
  public ResponseEntity<String> trelloWebhookVerification() {
    return ResponseEntity.ok("");
  }

  @PostMapping("/trello")
  public ResponseEntity<String> trelloWebhook(RequestEntity<TrelloPayload> payload) {
    System.out.println("Trello Payload:");
    System.out.println(payload.getBody().getAction().getData().getCard());
    System.out.println("Type: " + payload.getBody().getAction().getType());
    System.out.println("Data List After Name:");
    System.out.println(payload.getBody().getAction().getData().getListAfter().getName());
    return ResponseEntity.ok("");
  }
}
