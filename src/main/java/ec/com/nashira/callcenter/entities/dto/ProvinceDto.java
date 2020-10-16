package ec.com.nashira.callcenter.entities.dto;

import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import ec.com.nashira.callcenter.entities.City;

public class ProvinceDto implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private Integer id;

  @NotEmpty
  private String name;

  private boolean enabled;

  private List<City> cities;

  public List<City> getCities() {
    return cities;
  }

  public void setCities(List<City> cities) {
    this.cities = cities;
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

}
