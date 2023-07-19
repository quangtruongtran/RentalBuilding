package com.RentalBuilding.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Space")
public class SpaceEntity extends AbstractClass {
	
	@Column(name = "space_code")
	private String spaceCode;
	
	@Column(name = "space_area")
	private Integer spaceArea;
	
	@Column(name = "space_price")
	private Integer spacePrice;
	
	@Column(name = "space_service_fee")
	private Integer spaceServiceFee;
	
	@Column(name = "space_description", columnDefinition = "TEXT")
	private String spaceDescription;
	
	@Column(name = "space_image")
	private String spaceImage;

    @OneToOne(mappedBy = "space", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @LazyToOne(value = LazyToOneOption.NO_PROXY)
    private ContractEntity contract;

	@ManyToOne
	@JoinColumn(name = "floor_id")
	private FloorEntity floor;

	@ManyToOne
	@JoinColumn(name = "space_status_id")
	private SpaceStatus spaceStatus;

	@ManyToOne
	@JoinColumn(name = "space_type_id")
	private SpaceType spaceType;

	@Override
	public String toString() {
		return "SpaceEntity [spaceCode=" + spaceCode + ", spaceArea=" + spaceArea + ", spacePrice=" + spacePrice
				+ ", spaceServiceFee=" + spaceServiceFee + ", spaceDescription=" + spaceDescription + ", spaceImage="
				+ spaceImage + ", getId()=" + getId() + ", getCreateDate()=" + getCreateDate() + ", getCreateBy()="
				+ getCreateBy() + ", getUpdateDate()=" + getUpdateDate() + ", getUpdateBy()=" + getUpdateBy() + "]";
	}
}
