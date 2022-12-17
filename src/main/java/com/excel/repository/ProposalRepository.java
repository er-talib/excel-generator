package com.excel.repository;

import java.io.Serializable;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.excel.entity.Proposal;

public interface ProposalRepository extends JpaRepository<Proposal, Serializable> {

    @Query(value="select * FROM proposal WHERE  sector IN (:sectors)", nativeQuery=true)
	public List<Proposal> findBySector(String[] sectors);
//    public List<Proposal> findBySector(String sector);

}
