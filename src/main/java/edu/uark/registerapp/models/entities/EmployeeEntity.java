package edu.uark.registerapp.models.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import edu.uark.registerapp.commands.employees.helpers.EmployeeHelper;
import edu.uark.registerapp.models.api.Employee;

@Entity
@Table(name = "employee")
public class EmployeeEntity {
	@Id
	@Column(name = "id", updatable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
		private final UUID id;

			public UUID getId() {
				return this.id;
			}

	@Column(name = "employeeid")
		private int employeeId;

			public int getEmployeeId() {
				return this.employeeId;
			}
	
			public EmployeeEntity setEmployeeId(final int employeeId) {
				this.employeeId = employeeId;
				return this;
			}

	@Column(name = "firstname")
		private String firstName;

			public String getFirstName() {
				return this.firstName;
			}
	
			public EmployeeEntity setFirstName(final String firstName) {
				this.firstName = firstName;
				return this;
			}

	@Column(name = "lastname")
		private String lastName;

			public String getLastName() {
				return this.lastName;
			}

			public EmployeeEntity setLastName(final String lastName) {
				this.lastName = lastName;
				return this;
			}

	@Column(name = "password")
		private byte[] password;
	
			public byte[] getPassword() {
				return this.password;
			}
			
			public EmployeeEntity setPassword(final byte[] password) {
				this.password = password;
				return this;
			}

	@Column(name = "active")
		private boolean isActive;
	
			public boolean isActive() {
				return this.isActive;
			}
			
			public EmployeeEntity setActive(final boolean active) {
				this.isActive = active;
				return this;
			}

	@Column(name = "classification")
		private int classification;
			
			public int getClassification() {
				return this.classification;
			}

			public EmployeeEntity setClassification(final int classification) {
				this.classification = classification;
				return this;
			}
			
	@Column(name = "managerid")
		private UUID managerId;
	
			public UUID getManagerId() {
				return this.managerId;
			}
			
			public EmployeeEntity setManagerId(final UUID managerId) {
				this.managerId = managerId;
				return this;
			}
			
	@Column(name = "createdon", insertable = false, updatable = false)
	@Generated(GenerationTime.INSERT)
		private LocalDateTime createdOn;

			public LocalDateTime getCreatedOn() {
				return this.createdOn;
			}

	@Column(name = "quantitysold")
			private int quantitySold;
			public int getQuantitySold(){
				return this.quantitySold;
			}
			public EmployeeEntity setQuantitySold(final int quantitySold){
				this.quantitySold = quantitySold;
				return this;
			}

	@Column(name = "employeesales")
			private double employeeSales;
			public double getEmployeeSales(){
				return this.employeeSales;
			}
			public EmployeeEntity setEmployeeSales(final double employeeSales){
				this.employeeSales = employeeSales;
				return this;
			}
			
	public Employee synchronize(final Employee apiEmployee) {
		this.setFirstName(apiEmployee.getFirstName());
		this.setLastName(apiEmployee.getLastName());
		this.setActive(apiEmployee.getIsActive());
		this.setClassification(apiEmployee.getClassification());
		
		if (apiEmployee.getManagerId() != null) {
			this.setManagerId(apiEmployee.getManagerId());
		}
		if (!StringUtils.isBlank(new String(apiEmployee.getPassword()))) {
			this.setPassword(
				EmployeeHelper.hashPassword(
					new String(apiEmployee.getPassword())));
		}
		
		apiEmployee.setId(this.getId());
		apiEmployee.setEmployeeId(this.getEmployeeId());
		apiEmployee.setCreatedOn(this.getCreatedOn());
		
		return apiEmployee;
	}
			
	public EmployeeEntity() {
		this.id = new UUID(0, 0);
		this.employeeId = -1;
		this.firstName = StringUtils.EMPTY;
		this.lastName = StringUtils.EMPTY;
		this.password = new byte[0];
		this.isActive = false;
		this.classification = -1;
		this.managerId = new UUID(0, 0);
	}
			
	public EmployeeEntity(int employeeId, String firstName, String lastName, byte[] password, boolean active, int classification, UUID managerId) {
		this.id = new UUID(0, 0);
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.isActive = active;
		this.classification = classification;
		this.managerId = managerId;
	}
			
	public EmployeeEntity(final Employee apiEmployee) {
		this.id = new UUID(0, 0);
		this.firstName = apiEmployee.getFirstName();
		this.lastName = apiEmployee.getLastName();
		this.password = EmployeeHelper.hashPassword(new String(apiEmployee.getPassword()));
		this.isActive = apiEmployee.getIsActive();
		this.classification = apiEmployee.getClassification();
		this.managerId = (
				(apiEmployee.getManagerId() != null)
					? apiEmployee.getManagerId()
							: new UUID(0, 0));
	}
}