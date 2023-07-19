package com.RentalBuilding.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RentalBuilding.DTO.StatisticsDTO;
import com.RentalBuilding.Entity.ContractEntity;
import com.RentalBuilding.Repository.ContractRepository;
import com.RentalBuilding.Repository.CustomerRepository;
import com.RentalBuilding.Repository.SpaceRepository;
import com.RentalBuilding.Service.StatisticService;

@Service
public class StatisticServiceImpl implements StatisticService {

	@Autowired
	private SpaceRepository spaceRepository;
	@Autowired
	private ContractRepository contractRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Override
	public StatisticsDTO getStatistics() {
		StatisticsDTO statistic = new StatisticsDTO();
		statistic.setSpacesQuantity((int) spaceRepository.count());
		statistic.setContractsQuantity((int) contractRepository.count());
		statistic.setCustomersQuantity((int) customerRepository.count());
		List<ContractEntity> listContracts = contractRepository.findAll();
		long revenue = 0;
		Long a = null;
		for (ContractEntity contract : listContracts) {
			 a = new Long(contract.getSpace().getSpacePrice() + contract.getSpace().getSpaceServiceFee());
			 revenue += a;
		}
		statistic.setRevenueThisMonth(revenue);
		statistic.setEstateContractAboutExpire(spaceRepository.getSpacesThatContractComingToEnd().size());
		return statistic;
	}

}
