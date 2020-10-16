package ec.com.nashira.callcenter.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import ec.com.nashira.callcenter.entities.dto.ProvinceDto;

@Entity
@Table(name = "provinces")
public class Province implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;
  private boolean enabled;

  @OneToMany(mappedBy = "province")
  @LazyCollection(LazyCollectionOption.FALSE)
  private List<City> cities;

  public Province(ProvinceDto provinceDto) {
    id = provinceDto.getId();
    name = provinceDto.getName();
    enabled = provinceDto.isEnabled();
    cities = provinceDto.getCities();
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
