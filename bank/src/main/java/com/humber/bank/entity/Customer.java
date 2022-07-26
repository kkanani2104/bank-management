package com.humber.bank.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer extends PersonalInfo {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "customer") 
	 private List<Account> accounts;
	 
	@OneToOne(cascade = 
            CascadeType.REFRESH,fetch = FetchType.EAGER
        )
	@JoinColumn(name="branchId")
	private Branch branch;

	@ManyToOne(cascade = 
            CascadeType.REFRESH,fetch = FetchType.EAGER
        )
	@JoinColumn(name="managerId")
	private Manager manager;
	
	@CreationTimestamp
	@Column(updatable = false)
	@JsonIgnore
	private LocalDate createdDate;
	
	@UpdateTimestamp
	@Column(updatable = true,insertable = false)
	@JsonIgnore
	private LocalDate lastUpdated;

	
}
