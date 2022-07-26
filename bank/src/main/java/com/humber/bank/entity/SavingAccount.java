package com.humber.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SavingAccount extends Account{

	private double dailyLimitAmount;
	private int dailyLimitNoOfTransaction;
	
	
}
