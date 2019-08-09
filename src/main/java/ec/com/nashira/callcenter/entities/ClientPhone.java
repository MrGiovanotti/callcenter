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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "client_phones")
public class ClientPhone implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "telephone_number")
	@NotEmpty
	private String telephoneNumber;

	@Column(name = "telephone_status")
	private String telephoneStatus;

	@Column(name = "call_attempts")
	@NotNull
	private int callAttempts = 0;

	@NotNull
	private boolean callable;

	@ManyToOne
	@JoinColumn(name = "client_info_id")
	private ClientInfo clientInfo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getTelephoneStatus() {
		return telephoneStatus;
	}

	public void setTelephoneStatus(String telephoneStatus) {
		this.telephoneStatus = telephoneStatus;
	}

	public int getCallAttempts() {
		return callAttempts;
	}

	public void setCallAttempts(int callAttempts) {
		this.callAttempts = callAttempts;
	}

	public boolean isCallable() {
		return callable;
	}

	public void setCallable(boolean callable) {
		this.callable = callable;
	}

	public ClientInfo getClientInfo() {
		return clientInfo;
	}

	public void setClientInfo(ClientInfo clientInfo) {
		this.clientInfo = clientInfo;
	}

}
