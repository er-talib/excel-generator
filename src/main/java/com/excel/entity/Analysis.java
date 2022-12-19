package com.excel.entity;

import com.excel.converter.JsonNodeConverter;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonAutoDetect
@DynamicUpdate
public class Analysis {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("analysisId")
	private Long analysisId;
	@JsonProperty("stateId")
	private Long stateId;
	@JsonProperty("districtId")
	private Long districtId;
	@JsonProperty("ulbId")
	private Long ulbId;
	@JsonProperty("stateName")
	private String stateName;
	@JsonProperty("districtName")
	private String districtName;
	@JsonProperty("ulbName")
	private String ulbName;
	@JsonProperty("sector")
	private Long sector;
	@JsonProperty("sectorAbbr")
	private String sectorAbbr;
	@JsonProperty("sectorName")
	private String sectorName;
	@JsonProperty("analysisJSON")
	@Convert(converter = JsonNodeConverter.class)
	@Column(columnDefinition = "json")
	private JsonNode analysisJSON;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_proposal")
	@ToString.Exclude
	@JsonBackReference(value = "proposal-gap-analysis")
	private Proposal proposal;
}
