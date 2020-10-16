package ec.com.nashira.callcenter.entities.dto;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import ec.com.nashira.callcenter.entities.Province;

public class CityDto implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private Integer id;

  @NotEmpty
  private String name;

  private boolean enabled;

  @NotNull
  Province province;

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
