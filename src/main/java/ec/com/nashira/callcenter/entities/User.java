package ec.com.nashira.callcenter.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import ec.com.nashira.callcenter.entities.dto.UserDto;

@Entity
@Table(name = "users")
public class User implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  @Column(unique = true, nullable = false)
  private String username;

  private String password;

  private boolean enabled;

  private boolean deleted = false;

  private String image;

  @ManyToOne
  @JoinColumn(name = "authorities_id")
  private Authority authority;

  public User(UserDto userDto) {
    id = userDto.getId();
    name = userDto.getName();
    username = userDto.getUsername();
    password = userDto.getPassword();
    enabled = userDto.isEnabled();
    deleted = false;
    image = userDto.getImage();
    authority = userDto.getAuthority();
  }

  public User() {

  }

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

  public Authority getAuthority() {
    return authority;
  }

  public void setAuthority(Authority authority) {
    this.authority = authority;
  }

}
