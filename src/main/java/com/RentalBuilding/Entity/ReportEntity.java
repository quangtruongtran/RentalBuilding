package com.RentalBuilding.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "Report")
public class ReportEntity extends AbstractClass {
	
	@Column(name = "report_title")
	private String reportTitle;
	
	@Column(name = "report_content", columnDefinition = "TEXT")
	private String reportContent;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;

	@Override
	public String toString() {
		return "ReportEntity [reportContent=" + reportContent + ", getId()=" + getId() + ", getCreateDate()="
				+ getCreateDate() + ", getCreateBy()=" + getCreateBy() + ", getUpdateDate()=" + getUpdateDate()
				+ ", getUpdateBy()=" + getUpdateBy() + "]";
	}
}
