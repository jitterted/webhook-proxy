package com.jitterted.overlay.webhook;

public class WebhookRegistrationRequest {
  private String idToWatch;
  private String webhookName;

  public String getIdToWatch() {
    return idToWatch;
  }

  public void setIdToWatch(String idToWatch) {
    this.idToWatch = idToWatch;
  }

  public String getWebhookName() {
    return webhookName;
  }

  public void setWebhookName(String webhookName) {
    this.webhookName = webhookName;
  }
}
