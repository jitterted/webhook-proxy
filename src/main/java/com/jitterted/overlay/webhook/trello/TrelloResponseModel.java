
package com.jitterted.overlay.webhook.trello;


public class TrelloResponseModel {

    private String id;
    private String name;
    private Boolean closed;
    private Integer pos;
    private String idBoard;

    public TrelloResponseModel() {
    }

    public TrelloResponseModel(String id, String name, Boolean closed, Integer pos, String idBoard) {
        this.id = id;
        this.name = name;
        this.closed = closed;
        this.pos = pos;
        this.idBoard = idBoard;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getClosed() {
        return closed;
    }

    public Integer getPos() {
        return pos;
    }

    public String getIdBoard() {
        return idBoard;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }

    public void setIdBoard(String idBoard) {
        this.idBoard = idBoard;
    }

}
