package com.excel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excel.entity.Proposal;
import com.excel.repository.ProposalRepository;

@Service
public class ProposalServiceImpl {
	
	@Autowired
	private ProposalRepository proposalRepository ;
	
	public List<Proposal> getProposal(String[] sectors) {
		
		List<Proposal> allProposal = this.proposalRepository.findBySector(sectors);
		
		return  allProposal;
	}

}