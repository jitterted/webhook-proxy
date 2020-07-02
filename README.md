# JitterTed's Webhook Proxy for Overlay

Proxies Webhook callbacks from Trello (soon to add Twitch) via WebSocket
to a Vue app running on a local machine.

Vue front-end overlay code is at https://github.com/jitterted/jitteroverlay

## Trello Board

Trello board holding the features to work on, etc.: https://trello.com/b/wKSOndRf/jitterted-overlays

API reference docs for Trello are here: https://developer.atlassian.com/cloud/trello/rest/ 

## Requirements

Requires environment variables for Trello API token and key. More info soon.

## TO DO

* Add exception handling for the `delete` webhook function

## Lessons Learned

Change error handling like this: 

//    restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
//      @Override
//      public boolean hasError(ClientHttpResponse response) throws IOException {
//        return false;
//      }
//    });

But doesn't handle things well if the API returns different objects for different status codes
