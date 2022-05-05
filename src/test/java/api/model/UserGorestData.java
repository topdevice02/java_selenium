package api.model;

public class UserGorestData {
  public Integer id;
  public String name;
  public String email;
  public String gender;
  public String status;

  public UserGorestData(){}

  public UserGorestData(Integer id, String name, String email, String gender, String status) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.gender = gender;
    this.status = status;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getGender() {
    return gender;
  }

  public String getStatus() {
    return status;
  }

  @Override
  public String toString() {
    return "UserGorestData{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", gender='" + gender + '\'' +
            ", status='" + status + '\'' +
            '}';
  }
}
