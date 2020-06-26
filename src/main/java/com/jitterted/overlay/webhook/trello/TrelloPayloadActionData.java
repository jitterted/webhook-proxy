
package com.jitterted.overlay.webhook.trello;


public class TrelloPayloadActionData {

    private TrelloResponseActionDataOld old;
    private TrelloPayloadActionDataCard card;
    private TrelloPayloadActionDataBoard board;
    private TrelloResponseActionDataListBefore listBefore;
    private TrelloResponseActionDataListAfter listAfter;

    public TrelloPayloadActionData() {
    }

    public TrelloPayloadActionData(TrelloResponseActionDataOld old, TrelloPayloadActionDataCard card, TrelloPayloadActionDataBoard board, TrelloResponseActionDataListBefore listBefore, TrelloResponseActionDataListAfter listAfter) {
        this.old = old;
        this.card = card;
        this.board = board;
        this.listBefore = listBefore;
        this.listAfter = listAfter;
    }

    public TrelloResponseActionDataOld getOld() {
        return old;
    }

    public TrelloPayloadActionDataCard getCard() {
        return card;
    }

    public TrelloPayloadActionDataBoard getBoard() {
        return board;
    }

    public TrelloResponseActionDataListBefore getListBefore() {
        return listBefore;
    }

    public TrelloResponseActionDataListAfter getListAfter() {
        return listAfter;
    }

    public void setOld(TrelloResponseActionDataOld old) {
        this.old = old;
    }

    public void setCard(TrelloPayloadActionDataCard card) {
        this.card = card;
    }

    public void setBoard(TrelloPayloadActionDataBoard board) {
        this.board = board;
    }

    public void setListBefore(TrelloResponseActionDataListBefore listBefore) {
        this.listBefore = listBefore;
    }

    public void setListAfter(TrelloResponseActionDataListAfter listAfter) {
        this.listAfter = listAfter;
    }

}
