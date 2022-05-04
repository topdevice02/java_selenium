package api.response;

import api.model.UserTimeData;

public class UserTimeResponse extends UserTimeData {
  public String updatedAt;

  public UserTimeResponse(){
  }

  public UserTimeResponse(String name, String job, String updatedAt) {
    super(name, job);
    this.updatedAt = updatedAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }
}
