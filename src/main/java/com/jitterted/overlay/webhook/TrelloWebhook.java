package com.jitterted.overlay.webhook;

public class TrelloWebhook {
  private String id;
  private String description;
  private String idModel;
  private String callbackURL;

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
}
