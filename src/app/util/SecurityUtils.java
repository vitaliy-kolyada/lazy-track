package app.util;

import app.model.dto.AuthToken;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
public class SecurityUtils {
  private static AuthToken authToken;

  public static String getAuthHeader() {
    return "Baerer " + authToken.getToken();
  }

  public static void authentificate(ResponseEntity responseEntity) {
    String response = String.valueOf(responseEntity.getBody());
    SecurityUtils.authToken = AuthToken.parseToken(response);

  }
}
