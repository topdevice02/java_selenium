package api.model;

public class RegisterData {
  private String email;
  private String password;



  public RegisterData(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public RegisterData(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
