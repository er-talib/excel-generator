package com.excel.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.excel.config.ExcelGeneratorConfig;
import com.excel.entity.Proposal;
import com.excel.service.ProposalServiceImpl;

@RestController
public class ExcelController {

	@Autowired
	private ProposalServiceImpl proposalServiceImpl;

	@Autowired
	private ExcelGeneratorConfig excelGeneratorConfig;

	@GetMapping("/excel")
	public ResponseEntity<InputStreamResource> generateExcel(@RequestParam("sectors") String[] sectors)
			throws IOException {
		List<Proposal> findProposalsBySector = this.proposalServiceImpl.getProposal(sectors);
		List<Proposal> listCSWProposal = findProposalsBySector.stream().filter(p -> p.getSector().equals("CSW"))
				.collect(Collectors.toList());
		List<Proposal> listCSTProposal = findProposalsBySector.stream().filter(p -> p.getSector().equals("CST"))
				.collect(Collectors.toList());

		if (!listCSWProposal.isEmpty()) {
			ByteArrayInputStream in = excelGeneratorConfig.excelGenerator(listCSWProposal);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "attachment; filename=City Sanitation Action Plan.xlsx");

			return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
		}
		if (!listCSTProposal.isEmpty()) {
			ByteArrayInputStream in = excelGeneratorConfig.excelGenerator(listCSTProposal);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "attachment; filename=Toilet.xlsx");

			return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
		}
		return null;

	}

}
