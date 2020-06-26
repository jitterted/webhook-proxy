package com.jitterted.overlay.webhook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class WebhookNotifier {

  private static final String TOPIC_TRELLO = "/topic/trello";

  private final SimpMessagingTemplate simpMessagingTemplate;

  @Autowired
  public WebhookNotifier(SimpMessagingTemplate simpMessagingTemplate) {
    this.simpMessagingTemplate = simpMessagingTemplate;
  }

  public void sendTrelloEvent() {
    simpMessagingTemplate.convertAndSend(TOPIC_TRELLO, "HeyGuys");
  }

}
