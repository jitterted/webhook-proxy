
package com.jitterted.overlay.webhook.trello;


public class TrelloPayloadActionDataBoard {

    private String id;
    private String name;
    private String shortLink;

    public TrelloPayloadActionDataBoard() {
    }

    public TrelloPayloadActionDataBoard(String id, String name, String shortLink) {
        this.id = id;
        this.name = name;
        this.shortLink = shortLink;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortLink() {
        return shortLink;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }

}
