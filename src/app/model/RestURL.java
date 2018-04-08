package app.model;

public enum RestURL {
  LOGIN("http://localhost:8080/api/auth/login", RestMethod.POST),
  REGISTER("http://localhost:8080/register", RestMethod.POST),
  CREATE_NEW_USER("http://localhost:8080/api/users/create", RestMethod.POST);


  String url;
  RestMethod method;

  RestURL(String url, RestMethod method) {
    this.url = url;
    this.method = method;
  }

  public String getUrl() {
    return url;
  }

  public RestMethod getMethod() {
    return method;
  }
}
