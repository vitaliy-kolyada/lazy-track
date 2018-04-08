package app.controller;

import app.model.RestURL;
import app.model.dto.LoginRequest;
import app.util.SecurityUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class UserApiController extends ApiController {

  public void login(String login, String password) {
    LoginRequest loginRequest = new LoginRequest(login, password);
    RestTemplate restTemplate = new RestTemplate();
    HttpEntity<Object> requestEntity = new HttpEntity<>(loginRequest, getAnonimousHeaders());
    ResponseEntity responseEntity = restTemplate.exchange(RestURL.LOGIN.getUrl(), HttpMethod.POST, requestEntity, String.class);
    SecurityUtils.authentificate(responseEntity);
  }
}
