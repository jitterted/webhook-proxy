package com.jitterted.overlay.webhook;

public class TrelloEvent {
  private String callbackName;
  private String idForModelBeingWatched;

  public TrelloEvent(String callbackName, String idForModelBeingWatched) {
    this.callbackName = callbackName;
    this.idForModelBeingWatched = idForModelBeingWatched;
  }

  public String getCallbackName() {
    return callbackName;
  }

  public void setCallbackName(String callbackName) {
    this.callbackName = callbackName;
  }

  public String getIdForModelBeingWatched() {
    return idForModelBeingWatched;
  }

  public void setIdForModelBeingWatched(String idForModelBeingWatched) {
    this.idForModelBeingWatched = idForModelBeingWatched;
  }
}
