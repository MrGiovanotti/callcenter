package ec.com.nashira.callcenter.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.tomcat.jni.User;

@Entity
@Table(name = "call_info")
public class CallInfo implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	private int attempts = 0;

	private String status;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "agent_name")
	private String agentName;

	@Column(name = "scheduling_date")
	private Date schedulingDate;

	@Column(name = "end_date")
	private Date endDate;

	private String observation;

	@OneToOne
	@JoinColumn(name = "client_info_id")
	private ClientInfo clientInfo;

	@ManyToOne
	@JoinColumn(name = "substatus_id")
	private Substatus substatus;

	@Column(name = "substatus_detail")
	private String substatusDetail;

	@ManyToOne
	@JoinColumn(name = "users_id")
	private User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public Date getSchedulingDate() {
		return schedulingDate;
	}

	public void setSchedulingDate(Date schedulingDate) {
		this.schedulingDate = schedulingDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public ClientInfo getClientInfo() {
		return clientInfo;
	}

	public void setClientInfo(ClientInfo clientInfo) {
		this.clientInfo = clientInfo;
	}

	public Substatus getSubstatus() {
		return substatus;
	}

	public void setSubstatus(Substatus substatus) {
		this.substatus = substatus;
	}

	public String getSubstatusDetail() {
		return substatusDetail;
	}

	public void setSubstatusDetail(String substatusDetail) {
		this.substatusDetail = substatusDetail;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
