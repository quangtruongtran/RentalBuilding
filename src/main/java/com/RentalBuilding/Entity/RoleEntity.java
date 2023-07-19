package com.RentalBuilding.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
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
@Table(name = "Role")
public class RoleEntity extends AbstractClass {
	
	@Column(name = "role_name")
	private String roleName;
	
	@Column(name = "role_code")
	private String roleCode;

	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<UserEntity> users = new ArrayList<>();

	@Override
	public String toString() {
		return "RoleEntity [roleName=" + roleName + ", roleCode=" + roleCode + ", getId()=" + getId()
				+ ", getCreateDate()=" + getCreateDate() + ", getCreateBy()=" + getCreateBy() + ", getUpdateDate()="
				+ getUpdateDate() + ", getUpdateBy()=" + getUpdateBy() + "]";
	}
}
