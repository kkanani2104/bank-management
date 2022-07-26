package com.humber.bank.entity;

import javax.persistence.Embeddable;

import io.micrometer.core.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {

	@NonNull
	private int flatNumber;
	
	@NonNull
	private String flatName;
	
	@NonNull
	private String streetName;
	
	@NonNull
	private String city;
	
	@NonNull
	private String state;
	
	@NonNull
	private int pincode;
	
		
}
