package com.excel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excel.entity.StateActionPlan;
import com.excel.repository.StateActionPlanRepository;

@Service
public class StateActionPlanServiceImpl {
	
	@Autowired
	private StateActionPlanRepository actionPlanRepository ;
	
	public List<StateActionPlan> getAllActionPlanDetails() {
	List<StateActionPlan> findAll = this.actionPlanRepository.findAll();
		return findAll ;
	}
	
	
	public Optional<StateActionPlan> getSAPById(Long stateActionPlanId){
		Optional<StateActionPlan> stateActionPlan = this.actionPlanRepository.findById(stateActionPlanId);
	
		 return stateActionPlan ;
	}

}
