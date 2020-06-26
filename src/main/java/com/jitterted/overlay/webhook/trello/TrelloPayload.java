
package com.jitterted.overlay.webhook.trello;


public class TrelloPayload {

    private TrelloResponseModel model;
    private TrelloPayloadAction action;

    public TrelloPayload() {
    }

    public TrelloPayload(TrelloResponseModel model, TrelloPayloadAction action) {
        this.model = model;
        this.action = action;
    }

    public TrelloResponseModel getModel() {
        return model;
    }

    public TrelloPayloadAction getAction() {
        return action;
    }

    public void setModel(TrelloResponseModel model) {
        this.model = model;
    }

    public void setAction(TrelloPayloadAction action) {
        this.action = action;
    }

}
