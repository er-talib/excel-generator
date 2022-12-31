package com.excel.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excel.config.ExcelGeneratorConfig;
import com.excel.entity.StateActionPlan;
import com.excel.service.StateActionPlanServiceImpl;

@RestController
@RequestMapping("/state/actionPlan")
public class StateActionPlanController {

	@Autowired
	private StateActionPlanServiceImpl actionPlanServiceImpl;

	@Autowired
	private ExcelGeneratorConfig excelGeneratorConfig;

	@GetMapping("/excel/csp/{stateActionPlanId}")
	public ResponseEntity<InputStreamResource> getAllStateAcitonPlan(@PathVariable Long stateActionPlanId)
			throws IOException {

//		List<StateActionPlan> allDetailsOfStateActionPlan = this.actionPlanServiceImpl.getAllActionPlanDetails();

		Optional<StateActionPlan> stateActionPlan = this.actionPlanServiceImpl.getSAPById(stateActionPlanId);
		StateActionPlan actionPlan = stateActionPlan.get();
		ByteArrayInputStream in = excelGeneratorConfig.excelGeneratorForCSP(actionPlan);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=City Sanitation Action Plan.xlsx");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));

//		return ResponseEntity.ok(actionPlan);

	}

	@GetMapping("/excel/iec/{stateActionPlanId}")
	public ResponseEntity<InputStreamResource> getAllStateAcitonPlanOfIEC(@PathVariable Long stateActionPlanId)
			throws IOException {

//		List<StateActionPlan> allDetailsOfStateActionPlan = this.actionPlanServiceImpl.getAllActionPlanDetails();

		Optional<StateActionPlan> stateActionPlan = this.actionPlanServiceImpl.getSAPById(stateActionPlanId);
		StateActionPlan actionPlan = stateActionPlan.get();
		ByteArrayInputStream in = excelGeneratorConfig.excelGeneratorForIEC(actionPlan);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=IEC Plan.xlsx");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));

//		return ResponseEntity.ok(actionPlan);

	}

	@GetMapping("/excel/cb/{stateActionPlanId}")
	public ResponseEntity<InputStreamResource> getAllStateAcitonPlanOfCB(@PathVariable Long stateActionPlanId)
			throws IOException {

//		List<StateActionPlan> allDetailsOfStateActionPlan = this.actionPlanServiceImpl.getAllActionPlanDetails();

		Optional<StateActionPlan> stateActionPlan = this.actionPlanServiceImpl.getSAPById(stateActionPlanId);
		StateActionPlan actionPlan = stateActionPlan.get();
		ByteArrayInputStream in = excelGeneratorConfig.excelGeneratorForCB(actionPlan);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename= Capacity Building Plan.xlsx");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));

//		return ResponseEntity.ok(actionPlan);

	}

}
