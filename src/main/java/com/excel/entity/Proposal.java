package com.excel.entity;

import com.fasterxml.jackson.annotation.*;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * The type Proposal.
 */
@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Proposal{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("proposalId")
    private Long proposalId;
    @JsonProperty("uniqueProposalId")
    private String uniqueProposalId;
    @JsonProperty("ulbCode")
    private Long ulbCode;
    @JsonProperty("sector")
    private String sector;
    @JsonProperty("sectorName")
    private String sectorName;
    @JsonProperty("state")
    private String state;
    @JsonProperty("stateId")
    private Long stateId;
    @JsonProperty("districtId")
    private Long districtId;
    @JsonProperty("ulbName")
    private String ulbName;
    @JsonProperty("districtName")
    private String districtName;
    @JsonProperty("stateName")
    private String stateName;
    @JsonProperty("cityOrUlb")
    private String cityOrUlb;
  
    private String presentPopulation ;
    private String populationOf2011 ;
    private String presentHouseHold ;
    private String projectedPopulation2025 ;
    private String projectedHousehold2025;
    private String privateTotalFSTP ;
    private String amountInLakh ;
    private String length75CM ;
    private String diaAndMaterialOfPipe ;
    private String lengthOfPipe ;
    private String numberOfPumpingStations ;
    private String capacity ;
    private String numberOfSTPProposed ;
    private String centralShare ;
    private String stateShare ;

}
