package hrTool.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the task database table.
 * 
 */
@Entity
public class Task implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int taskId;

	private String code;

	private int companyId;

	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	private int teamId;
	
	private int totalHours;

	public Task() {
	}

	public int getTaskId() {
		return this.taskId;
	}

	public void setTaskId(int id) {
		this.taskId = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getTeamId() {
		return this.teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public int getTotalHours() {
		return this.totalHours;
	}

	public void setTotalHours(int totalHours) {
		this.totalHours = totalHours;
	}

}