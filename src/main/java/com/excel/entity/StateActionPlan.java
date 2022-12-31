package com.excel.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table
@DynamicUpdate
//@Where(clause = "isDeleted = false")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StateActionPlan {

	@Id
	@Column(nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
//    @Column(unique = true)
	private String uniqueId;
	private Integer stateId;
	private String stateName;
	private String sectorGroup;
	private String component;
	private String sectors;
	private Double totalCost;
	private Double centralShare;
//	private boolean isSubmitted;
	private Double stateShare;
//	private boolean isDeleted;
	private String status;
	private String createdBy;
	private String updatedBy;
//	@Column
//	@Temporal(TemporalType.TIMESTAMP)
////	private Date dateCreated;
//	@Column
//	@Temporal(TemporalType.TIMESTAMP)
////	private Date lastUpdated;
	
//	private boolean taApproved;
//	private boolean sectionApproved;
	
//  @OneToOne(fetch = FetchType.EAGER, mappedBy = "stateActionPlan", cascade = CascadeType.ALL)
//	private Attachment sltcAttachment;
    
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "stateActionPlanId", cascade = CascadeType.ALL)
	private Set<Proposal> proposals;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "stateActionPlan", cascade = CascadeType.ALL)
	private Set<SAPAmount> sapAmount;

//	@OneToMany(fetch = FetchType.EAGER, mappedBy = "stateActionPlan", cascade = CascadeType.ALL)
//	private Set<SAPUlbsCovered> sapUlbsCovered;

//	@OneToOne(fetch = FetchType.EAGER, mappedBy = "stateActionPlant", cascade = CascadeType.ALL)
//	private SAPDRImplementation sapDrImplementation;
//
//	@OneToOne(fetch = FetchType.EAGER, mappedBy = "stateActionPlant", cascade = CascadeType.ALL)
//	private SAPCDProcessingPlant processingPlant;
}
