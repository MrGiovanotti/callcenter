package ec.com.nashira.callcenter.entities.dto;

import java.io.Serializable;
import java.util.Date;

public class ActionDto implements Serializable {
  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private Integer id;
  private String username;
  private String description;
  private Date actionDate;
  private String host;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getActionDate() {
    return actionDate;
  }

  public void setActionDate(Date actionDate) {
    this.actionDate = actionDate;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }


}
