package app.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthToken {
  public final static String TOKEN_PREFIX = "{\"token\":\"";
  public final static String REFRESH_TOKEN_PREFIX = "\"refreshToken\":";
  private String token;
  private String refreshToken;

  public static AuthToken parseToken(String response) {
    String[] fields = response.split(",");
    String tokenPrefix = "{\"token\":\"";
    String refreshTokenPrefix = "\"refreshToken\":";

    String token;
    String refreshToken;
    if (fields[0].startsWith(tokenPrefix) && fields[1].startsWith(refreshTokenPrefix)) {
      token = fields[0].substring(tokenPrefix.length(), fields[0].length() - 1);
      refreshToken = fields[1].substring(refreshTokenPrefix.length() + 1, fields[1].length() - 2);
      return new AuthToken(token, refreshToken);
    } else {
      throw new IllegalArgumentException("Can not parse response.");
    }
  }
}
