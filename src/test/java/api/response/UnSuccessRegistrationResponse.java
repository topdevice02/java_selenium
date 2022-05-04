package api.response;

public class UnSuccessRegistrationResponse {
  private String error;

  public UnSuccessRegistrationResponse() {
  }

  public UnSuccessRegistrationResponse(String error) {
    this.error = error;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }
}
