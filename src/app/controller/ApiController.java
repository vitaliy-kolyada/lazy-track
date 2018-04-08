package app.controller;

import app.util.SecurityUtils;
import java.util.Collections;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class ApiController {

  protected HttpHeaders getAnonimousHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", "application/json");
    headers.add("X-Requested-With", "XMLHttpRequest");
    headers.add("Cache-Control", "no-cache");
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    return headers;
  }

  protected HttpHeaders getHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", "application/json");
    headers.add("X-Requested-With", "XMLHttpRequest");
    headers.add("Cache-Control", "no-cache");
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    headers.add("Authorization", SecurityUtils.getAuthHeader());
    return headers;
  }
}
