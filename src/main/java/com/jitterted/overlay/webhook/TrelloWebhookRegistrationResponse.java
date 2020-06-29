package com.jitterted.overlay.webhook;

public class TrelloWebhookRegistrationResponse {
  private String id;
  private String description;
  private String idModel;
  private String callbackURL;
  private boolean active;
  private int consecutiveFailures;
  private String firstConsecutiveFailDate;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getIdModel() {
    return idModel;
  }

  public void setIdModel(String idModel) {
    this.idModel = idModel;
  }

  public String getCallbackURL() {
    return callbackURL;
  }

  public void setCallbackURL(String callbackURL) {
    this.callbackURL = callbackURL;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public int getConsecutiveFailures() {
    return consecutiveFailures;
  }

  public void setConsecutiveFailures(int consecutiveFailures) {
    this.consecutiveFailures = consecutiveFailures;
  }

  public String getFirstConsecutiveFailDate() {
    return firstConsecutiveFailDate;
  }

  public void setFirstConsecutiveFailDate(String firstConsecutiveFailDate) {
    this.firstConsecutiveFailDate = firstConsecutiveFailDate;
  }

  @Override
  public String toString() {
    return "TrelloWebhookRegistrationResponse: " +
        "Webhook ID='" + id + '\'' +
        ", Description='" + description + '\'' +
        ", Model ID='" + idModel + '\'' +
        ", Callback URL='" + callbackURL + '\'' +
        ", Active=" + active;
  }
}
