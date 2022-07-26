package com.humber.bank.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import io.micrometer.core.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "branch")
public class Branch {

	@Id
	@GeneratedValue
	private int branchId;
	
	@NonNull
	private String branchName;
	
	@NonNull
	private String address;
	
	@NonNull
	private String ifscCode;
	
	@NonNull
	private long micrNo;
	
	
	
}
