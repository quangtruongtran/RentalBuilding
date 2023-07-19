package com.RentalBuilding.Entity;

import java.util.ArrayList;
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
@Table(name = "SpaceType")
public class SpaceType extends AbstractClass {
	@Column(name = "space_type_name")
	private String spaceTypeName;

	@OneToMany(mappedBy = "spaceType",cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
	private List<SpaceEntity> spaces = new ArrayList<>();

	@Override
	public String toString() {
		return "SpaceType [spaceTypeName=" + spaceTypeName + ", getId()=" + getId() + ", getCreateDate()="
				+ getCreateDate() + ", getCreateBy()=" + getCreateBy() + ", getUpdateDate()=" + getUpdateDate()
				+ ", getUpdateBy()=" + getUpdateBy() + "]";
	}
}
