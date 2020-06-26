
package com.jitterted.overlay.webhook.trello;


public class TrelloPayloadActionDataCard {

    private String idList;
    private String id;
    private String name;
    private Integer idShort;
    private String shortLink;

    public TrelloPayloadActionDataCard() {
    }

    public TrelloPayloadActionDataCard(String idList, String id, String name, Integer idShort, String shortLink) {
        this.idList = idList;
        this.id = id;
        this.name = name;
        this.idShort = idShort;
        this.shortLink = shortLink;
    }

    public String getIdList() {
        return idList;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getIdShort() {
        return idShort;
    }

    public String getShortLink() {
        return shortLink;
    }

    public void setIdList(String idList) {
        this.idList = idList;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdShort(Integer idShort) {
        this.idShort = idShort;
    }

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }

    @Override
    public String toString() {
        return "TrelloResponseActionDataCard{" +
            "idList='" + idList + '\'' +
            ", id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", idShort=" + idShort +
            ", shortLink='" + shortLink + '\'' +
            '}';
    }
}
