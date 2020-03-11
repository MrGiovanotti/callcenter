package ec.com.nashira.callcenter.entities.dto;

import java.io.Serializable;
import java.util.List;
import ec.com.nashira.callcenter.entities.Authority;

public class UserDto implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private Integer id;
  private String name;
  private String username;
  private String password;
  private boolean enabled;
  private boolean deleted;
  private String image;
  private List<Authority> authorities;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public List<Authority> getAuthorities() {
    return authorities;
  }

  public void setAuthorities(List<Authority> authorities) {
    this.authorities = authorities;
  }



}
