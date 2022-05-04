package api;

public class SuccessRegistration {
  private Integer id;
  private String token;

  public SuccessRegistration() {
  }

  public SuccessRegistration(Integer id, String token) {
    this.id = id;
    this.token = token;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "SuccessRegistration{" +
            "id=" + id +
            ", token='" + token + '\'' +
            '}';
  }
}
