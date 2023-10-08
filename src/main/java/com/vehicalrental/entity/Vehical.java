package com.vehicalrental.entity;

import com.vehicalrental.enums.FuelTypes;
import com.vehicalrental.enums.VehicalTypes;

public class Vehical {
	private int registrationNumber;
	private VehicalTypes vehicalType;
	private String manufacturer;
	private int dailyRent;
	private FuelTypes fuelType;
	
	public Vehical(int registrationNumber, VehicalTypes vehicalType, String manufacturer, int dailyRent,
			FuelTypes fuelType) {
		super();
		this.registrationNumber = registrationNumber;
		this.vehicalType = vehicalType;
		this.manufacturer = manufacturer;
		this.dailyRent = dailyRent;
		this.fuelType = fuelType;
	}

	@Override
	public String toString() {
		return "Vehical [registrationNumber=" + registrationNumber + ", vehicalType=" + vehicalType + ", manufacturer="
				+ manufacturer + ", dailyRent=" + dailyRent + ", fuelType=" + fuelType + "]";
	}



	public int getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(int registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public VehicalTypes getVehicalType() {
		return vehicalType;
	}

	public void setVehicalType(VehicalTypes vehicalType) {
		this.vehicalType = vehicalType;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public int getDailyRent() {
		return dailyRent;
	}

	public void setDailyRent(int dailyRent) {
		this.dailyRent = dailyRent;
	}

	public FuelTypes getFuelType() {
		return fuelType;
	}

	public void setFuelType(FuelTypes fuelType) {
		this.fuelType = fuelType;
	}
	
	
}
