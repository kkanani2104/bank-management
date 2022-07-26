package com.humber.bank.entity;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CurrentAccount extends Account{

	@JsonIgnore
	private double minimumBalance;
	private String companyName;
	
	
	
	
}
