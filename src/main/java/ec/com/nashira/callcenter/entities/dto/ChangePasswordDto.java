package ec.com.nashira.callcenter.entities.dto;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;

public class ChangePasswordDto implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  Integer id;

  @NotEmpty
  private String oldPassword;

  @NotEmpty
  private String newPassword;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getOldPassword() {
    return oldPassword;
  }

  public void setOldPassword(String oldPassword) {
    this.oldPassword = oldPassword;
  }

  public String getNewPassword() {
    return newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }



}
