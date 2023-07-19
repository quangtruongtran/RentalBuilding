package com.RentalBuilding.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "User")
public class UserEntity extends AbstractClass {
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "user_password")
	private String userPassword;
	
	@Column(name = "user_code")
	private String userCode;
	
	
	@Column(name = "user_date_of_birth")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date userDateOfBirth;
	
	
	@Column(name = "user_gender")
	private String userGender;
	
	
	@Column(name = "user_address")
	private String userAddress;
	
	
	@Column(name = "user_phone")
	private String userPhone;
	
	
	@Column(name = "user_email")
	private String userEmail;
	
	
	@Column(name = "user_image")
	private String userImage;

    @OneToMany(mappedBy="user", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<ContractEntity> contracts = new ArrayList<>();

	@ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
	private List<CustomerEntity> customers = new ArrayList<>();

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), 
								  inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleEntity> roles = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "department_id")
	private DepartmentEntity department;

	@OneToMany(mappedBy="user", cascade = CascadeType.REMOVE , fetch = FetchType.LAZY)
    private List<ReportEntity> reports = new ArrayList<>();

	@PreRemove
	void beforeRomve() {
		for (CustomerEntity customer : customers) {
			customer.getUsers().remove(this);
		}
	}
	

	@Override
	public String toString() {
		return "UserEntity [userName=" + userName + ", userPassword=" + userPassword + ", userCode=" + userCode
				+ ", userDateOfBirth=" + userDateOfBirth + ", userGender=" + userGender + ", userAddress=" + userAddress
				+ ", userPhone=" + userPhone + ", userEmail=" + userEmail + ", userImage=" + userImage + ", getId()="
				+ getId() + ", getCreateDate()=" + getCreateDate() + ", getCreateBy()=" + getCreateBy()
				+ ", getUpdateDate()=" + getUpdateDate() + ", getUpdateBy()=" + getUpdateBy() + "]";
	}	
}
