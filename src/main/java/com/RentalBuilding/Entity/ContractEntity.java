package com.RentalBuilding.Entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

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
@Table(name = "Contract")
public class ContractEntity extends AbstractClass {
	@Column(name = "contract_code")
	private String contractCode;
	
	@Column(name = "contract_period")
	private Integer contractPeriod;
	
	@Column(name = "contract_date_start")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date contractDateStart;
	
	@Column(name = "contract_date_end")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date contractDateEnd;

	@Column(name = "contract_deposit")
	private Integer contractDeposit;
	
	@Column(name = "contract_tax_code")
	private String contractTaxCode;
	
	@Column(name = "contract_content", columnDefinition = "TEXT")
	private String contractContent;
	
	@Column(name = "contract_compensation")
	private Integer contractCompensation;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "customer_id")
	private CustomerEntity customer;
	
	@OneToOne(fetch = FetchType.LAZY)
	@LazyToOne(value = LazyToOneOption.NO_PROXY)
    @JoinColumn(name = "space_id")
	private SpaceEntity space;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;
	
	@Override
	public String toString() {
		return "ContractEntity [contractCode=" + contractCode + ", contractPeriod=" + contractPeriod
				+ ", contractDateStart=" + contractDateStart + ", contractDateEnd=" + contractDateEnd
				+ ", contractDeposit=" + contractDeposit + ", contractTaxCode=" + contractTaxCode + ", contractContent="
				+ contractContent + ", contractCompensation=" + contractCompensation + ", getId()=" + getId()
				+ ", getCreateDate()=" + getCreateDate() + ", getCreateBy()=" + getCreateBy() + ", getUpdateDate()="
				+ getUpdateDate() + ", getUpdateBy()=" + getUpdateBy() + "]";
	}
}
