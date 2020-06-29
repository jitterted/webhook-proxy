package com.jitterted.overlay.webhook;

public class TrelloWebhookRegistrationRequest {
  private String key;
  private String callbackURL;
  private String idModel;
  private String description;
  private String token;

  public TrelloWebhookRegistrationRequest(TrelloConfig trelloConfig, String callbackURL, String idModel, String description) {
    this.key = trelloConfig.getApikey();
    this.token = trelloConfig.getApitoken();
    this.callbackURL = callbackURL;
    this.idModel = idModel;
    this.description = description;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getCallbackURL() {
    return callbackURL;
  }

  public void setCallbackURL(String callbackURL) {
    this.callbackURL = callbackURL;
  }

  public String getIdModel() {
    return idModel;
  }

  public void setIdModel(String idModel) {
    this.idModel = idModel;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
