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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "client_info")
public class ClientInfo implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "database_id")
	private Database database;

	@OneToMany(mappedBy = "clientInfo", fetch = FetchType.EAGER)
	private List<ClientPhone> clientPhones;

	@OneToMany(mappedBy = "clientInfo", fetch = FetchType.LAZY)
	private List<CallHistory> callHistories;

	@OneToOne(mappedBy = "clientInfo", fetch = FetchType.EAGER)
	private CallInfo callInfo;

	@Column(name = "identification_number")
	private String identificationNumber;

	@NotEmpty
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Database getDatabase() {
		return database;
	}

	public void setDatabase(Database database) {
		this.database = database;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ClientPhone> getClientPhones() {
		return clientPhones;
	}

	public void setClientPhones(List<ClientPhone> clientPhones) {
		this.clientPhones = clientPhones;
	}

	public List<CallHistory> getCallHistories() {
		return callHistories;
	}

	public void setCallHistories(List<CallHistory> callHistories) {
		this.callHistories = callHistories;
	}

}
