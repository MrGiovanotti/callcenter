package ec.com.nashira.callcenter.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "substatus")
public class Substatus implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty
	private String name;

	@NotNull
	private boolean enabled = true;

	@NotNull
	@Column(name = "management_enabler")
	private boolean managementEnabler;

	@NotNull
	@Column(name = "recall_enabler")
	private boolean recallEnabler;

	@NotNull
	private boolean manageable;

	@ManyToOne
	@JoinColumn(name = "status_id")
	private Status status;

	@OneToMany(mappedBy = "substatus", fetch = FetchType.EAGER)
	private List<SubstatusDetail> substatusDetails;

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

	public boolean isManagementEnabler() {
		return managementEnabler;
	}

	public void setManagementEnabler(boolean managementEnabler) {
		this.managementEnabler = managementEnabler;
	}

	public boolean isRecallEnabler() {
		return recallEnabler;
	}

	public void setRecallEnabler(boolean recallEnabler) {
		this.recallEnabler = recallEnabler;
	}

	public boolean isManageable() {
		return manageable;
	}

	public void setManageable(boolean manageable) {
		this.manageable = manageable;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<SubstatusDetail> getSubstatusDetails() {
		return substatusDetails;
	}

	public void setSubstatusDetails(List<SubstatusDetail> substatusDetails) {
		this.substatusDetails = substatusDetails;
	}

}
