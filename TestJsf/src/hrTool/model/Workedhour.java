package hrTool.model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the workedhours database table.
 * 
 */
@Entity
@Table(name="workedhours")
public class Workedhour implements Serializable {
	private static final long serialVersionUID = 1L;

	private int companyId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private int employeeId;

	private int hours;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;

	private int taskId;

	public Workedhour() {
	}

	public int getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getHours() {
		return this.hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTaskId() {
		return this.taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

}