package ec.com.nashira.callcenter.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import ec.com.nashira.callcenter.entities.dto.ActionDto;

@Entity
@Table(name = "actions")
public class Action implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotEmpty
  private String username;

  @NotEmpty
  private String description;

  @Column(name = "action_date")
  @NotNull
  private Date actionDate;

  @NotEmpty
  private String host;

  public Action(ActionDto actionDto) {
    id = actionDto.getId();
    username = actionDto.getUsername();
    description = actionDto.getDescription();
    actionDate = actionDto.getActionDate();
    host = actionDto.getHost();
  }

  public Action() {

  }

  @PrePersist
  public void prePersist() {
    actionDate = new Date();
  }

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
