package com.RentalBuilding.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RentalBuilding.Entity.ReportEntity;

public interface ReportRepository extends JpaRepository<ReportEntity, Long> {

}
