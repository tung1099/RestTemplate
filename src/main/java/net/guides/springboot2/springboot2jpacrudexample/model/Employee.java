package net.guides.springboot2.springboot2jpacrudexample.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private long id;
	private String firstName;
	private String lastName;
	private String email;
	
	public Employee() {
		
	}
	
	public Employee(String firstName, String lastName, String emailId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = emailId;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "first_name", nullable = false)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name = "last_name", nullable = false)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Column(name = "email_address", nullable = false)
	public String getEmail() {
		return email;
	}
	public void setEmail(String emailId) {
		this.email = emailId;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + email
				+ "]";
	}
	
}
