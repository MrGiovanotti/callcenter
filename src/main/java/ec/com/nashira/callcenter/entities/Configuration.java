package ec.com.nashira.callcenter.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "configurations")
public class Configuration implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "max_users_number")
	private int maxUsersNumber;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getMaxUsersNumber() {
		return maxUsersNumber;
	}

	public void setMaxUsersNumber(int maxUsersNumber) {
		this.maxUsersNumber = maxUsersNumber;
	}

}
