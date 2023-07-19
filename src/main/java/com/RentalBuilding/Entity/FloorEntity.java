package com.RentalBuilding.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "Floor")
public class FloorEntity extends AbstractClass {
	@Column(name = "floor_name")
	private String floorName;
	
	@Column(name = "floor_area")
	private Integer floorArea;
	
	@OneToMany(mappedBy = "floor",cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
	private List<SpaceEntity> spaces = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "floor_status_id")
	private FloorStatus floorStatus;
	
	@ManyToOne
	@JoinColumn(name = "floor_type_id")
	private FloorType floorType;

	@Override
	public String toString() {
		return "FloorEntity [floorName=" + floorName + ", floorArea=" + floorArea + ", getId()=" + getId()
				+ ", getCreateDate()=" + getCreateDate() + ", getCreateBy()=" + getCreateBy() + ", getUpdateDate()="
				+ getUpdateDate() + ", getUpdateBy()=" + getUpdateBy() + "]";
	}
}
