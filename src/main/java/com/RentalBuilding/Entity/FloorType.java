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
@Table(name = "FloorType")
public class FloorType extends AbstractClass {
	@Column(name = "floor_type_name")
	private String floorTypeName;

	@OneToMany(mappedBy = "floorType",cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
	private List<FloorEntity> floors = new ArrayList<>();

	@Override
	public String toString() {
		return "FloorType [floorTypeName=" + floorTypeName + ", getId()=" + getId() + ", getCreateDate()="
				+ getCreateDate() + ", getCreateBy()=" + getCreateBy() + ", getUpdateDate()=" + getUpdateDate()
				+ ", getUpdateBy()=" + getUpdateBy() + "]";
	}
}
