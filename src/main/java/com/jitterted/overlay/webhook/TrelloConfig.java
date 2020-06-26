package com.jitterted.overlay.webhook;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("trello")
public class TrelloConfig {

  private String apikey;
  private String apitoken;
  private String secret;

  public String getApikey() {
    return apikey;
  }

  public void setApikey(String apikey) {
    this.apikey = apikey;
  }

  public String getApitoken() {
    return apitoken;
  }

  public void setApitoken(String apitoken) {
    this.apitoken = apitoken;
  }

  public String getSecret() {
    return secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }
}
