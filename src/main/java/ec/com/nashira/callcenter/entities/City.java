package ec.com.nashira.callcenter.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import ec.com.nashira.callcenter.entities.dto.CityDto;

@Entity
@Table(name = "cities")
public class City implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;
  private boolean enabled;

  @ManyToOne
  @JoinColumn(name = "provinces_id")
  Province province;

  public City(CityDto cityDto) {
    id = cityDto.getId();
    name = cityDto.getName();
    enabled = cityDto.isEnabled();
    province = cityDto.getProvince();
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

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public Province getProvince() {
    return province;
  }

  public void setProvince(Province province) {
    this.province = province;
  }

}
