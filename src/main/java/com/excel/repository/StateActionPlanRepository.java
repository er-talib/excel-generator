package com.excel.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.excel.entity.StateActionPlan;

public interface StateActionPlanRepository extends JpaRepository<StateActionPlan, Serializable> {

}
