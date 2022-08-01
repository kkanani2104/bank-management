package com.humber.bank.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.micrometer.core.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
@Inheritance(strategy = InheritanceType.JOINED)
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property ="accNo")
public class Account{

	@Id
	private long accNumber;
	
	@NonNull
	private String accHolderName;
	
	@NonNull
	@Enumerated(EnumType.STRING)
	private AccountType accountType;
	
	@NonNull
	private LocalDate openDate;
	
	@NonNull
	private boolean accStatus;
	
	@NonNull
	private double balance;

	@OneToMany
	private List<Transaction> transactions;


	@ManyToOne(cascade = 
            CascadeType.REFRESH,fetch = FetchType.EAGER)
	@JoinColumn(name="customerId")
	@JsonIgnore
	private Customer customer;

	
}
