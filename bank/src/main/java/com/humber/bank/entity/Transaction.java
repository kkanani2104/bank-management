package com.humber.bank.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import io.micrometer.core.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction{
	
	@Id
	@GeneratedValue
	private long transactionId;
	
	@NonNull
	@CreationTimestamp
	private LocalDate transactionDate;
	
	@NonNull
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;
	
	@NonNull
	private double transactionAmount;
	
	@NonNull
	private String transactionDescription;
	
	@NonNull
	@OneToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
	@JoinColumn(name = "fromAccount")
	//@JsonIdentityReference(alwaysAsId = true)
	private Account fromAccount;
	
	@NonNull
	@OneToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
	@JoinColumn(name = "toAccount")
	//@JsonIdentityReference(alwaysAsId = true)
	private Account toAccount;
	
	
	

	
}
