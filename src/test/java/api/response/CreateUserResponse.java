package api.response;

import api.model.UserTimeData;

public class CreateUserResponse extends UserTimeData {
  private Integer id;
  private String createdAt;

  public CreateUserResponse(){}

  public CreateUserResponse(Integer id, String createdAt) {
    this.id = id;
    this.createdAt = createdAt;
  }

  public CreateUserResponse(String name, String job, Integer id, String createdAt) {
    super(name, job);
    this.id = id;
    this.createdAt = createdAt;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }
}
