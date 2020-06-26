
package com.jitterted.overlay.webhook.trello;


public class TrelloPayloadAction {

    private String id;
    private String idMemberCreator;
    private TrelloPayloadActionData data;
    private String type;
    private String date;

    public TrelloPayloadAction() {
    }

    public TrelloPayloadAction(String id, String idMemberCreator, TrelloPayloadActionData data, String type, String date) {
        this.id = id;
        this.idMemberCreator = idMemberCreator;
        this.data = data;
        this.type = type;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdMemberCreator() {
        return idMemberCreator;
    }

    public void setIdMemberCreator(String idMemberCreator) {
        this.idMemberCreator = idMemberCreator;
    }

    public TrelloPayloadActionData getData() {
        return data;
    }

    public void setData(TrelloPayloadActionData data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
