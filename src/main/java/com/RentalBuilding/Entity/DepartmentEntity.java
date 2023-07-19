package com.RentalBuilding.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Department")
public class DepartmentEntity extends AbstractClass{
	
	@Column(name = "department_name")
	private String departmentName;
	
	@Column(name = "department_code")
	private String departmentCode;
	
	@OneToMany(mappedBy="department", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<UserEntity> users;

	@Override
	public String toString() {
		return "DepartmentEntity [departmentName=" + departmentName + ", departmentCode=" + departmentCode
				+ ", getId()=" + getId() + ", getCreateDate()=" + getCreateDate() + ", getCreateBy()=" + getCreateBy()
				+ ", getUpdateDate()=" + getUpdateDate() + ", getUpdateBy()=" + getUpdateBy() + "]";
	}
}
