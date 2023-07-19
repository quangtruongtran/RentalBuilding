package com.RentalBuilding.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
@Table(name = "Customer")
public class CustomerEntity extends AbstractClass {
	@Column(name = "customer_name")
	private String customerName;
	
	@Column(name = "customer_identify_number")
	private String customerIdentifyNumber;
	
	@Column(name = "customer_email")
	private String customerEmail;
	
	@Column(name = "customer_phone")
	private String customerPhone;
	
	@Column(name = "customer_date_of_birth")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date customerDateOfBirth;
	
	@Column(name = "customer_address")
	private String customerAddress;
	
	@Column(name = "customer_status")
	private String customerStatus;
	
	@OneToMany(mappedBy = "customer",cascade = {CascadeType.REMOVE,CascadeType.MERGE}, fetch = FetchType.LAZY)
	private List<ContractEntity> contracts = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "Consultation", joinColumns = @JoinColumn(name = "customer_id"), 
	  inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<UserEntity> users = new ArrayList<>();

	@Override
	public String toString() {
		return "CustomerEntity [customerName=" + customerName + ", customerIdentifyNumber=" + customerIdentifyNumber
				+ ", customerEmail=" + customerEmail + ", customerPhone=" + customerPhone + ", customerDateOfBirth="
				+ customerDateOfBirth + ", customerAddress=" + customerAddress + ", customerStatus=" + customerStatus
				+ ", getId()=" + getId() + ", getCreateDate()=" + getCreateDate() + ", getCreateBy()=" + getCreateBy()
				+ ", getUpdateDate()=" + getUpdateDate() + ", getUpdateBy()=" + getUpdateBy() + "]";
	}
}
