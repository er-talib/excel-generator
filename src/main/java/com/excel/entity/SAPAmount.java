package com.excel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class SAPAmount {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
	private String sector;
//	private String fy2021_22;
    private String fy2022_23;
    private String fy2023_24;
    private String fy2024_25;
    private Double total;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "stateActionPlanId",nullable=false)
    private StateActionPlan stateActionPlan;
    
}
