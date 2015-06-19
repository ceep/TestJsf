package hrTool.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the taskassociations database table.
 * 
 */
@Entity
@Table(name="taskassociations")
public class Taskassociation implements Serializable {
	private static final long serialVersionUID = 1L;

	private int companyId;

	private int employeeId;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;

	private int taskId;

	public Taskassociation() {
	}

	public int getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
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