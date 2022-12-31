package com.excel.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.excel.entity.SAPAmount;

public interface SAPAmountRepository  extends JpaRepository<SAPAmount, Serializable>{

}
