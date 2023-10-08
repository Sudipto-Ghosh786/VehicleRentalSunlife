package com.vehicalrental.vo;

import com.vehicalrental.enums.VehicalTypes;

public class VehicalRentDetailVO {
	private String vehicalType;
	private int totalNumberOfVehical;
	private int totalNumberOfVehicalRented;
	private int totalRentEarned;
	@Override
	public String toString() {
		return "FetchVehicalDetailVO [vehicalType=" + vehicalType + ", totalNumberOfVehical=" + totalNumberOfVehical
				+ ", totalNumberOfVehicalRented=" + totalNumberOfVehicalRented + ", totalRentEarned=" + totalRentEarned
				+ "]";
	}
	public String getVehicalType() {
		return vehicalType;
	}
	public void setVehicalType(String vehicalType) {
		this.vehicalType = vehicalType;
	}
	public int getTotalNumberOfVehical() {
		return totalNumberOfVehical;
	}
	public void setTotalNumberOfVehical(int totalNumberOfVehical) {
		this.totalNumberOfVehical = totalNumberOfVehical;
	}
	public int getTotalNumberOfVehicalRented() {
		return totalNumberOfVehicalRented;
	}
	public void setTotalNumberOfVehicalRented(int totalNumberOfVehicalRented) {
		this.totalNumberOfVehicalRented = totalNumberOfVehicalRented;
	}
	public int getTotalRentEarned() {
		return totalRentEarned;
	}
	public void setTotalRentEarned(int totalRentEarned) {
		this.totalRentEarned = totalRentEarned;
	}
	
	public VehicalRentDetailVO(String vehicalType, int totalNumberOfVehical, int totalNumberOfVehicalRented,
			int totalRentEarned) {
		super();
		this.vehicalType = vehicalType;
		this.totalNumberOfVehical = totalNumberOfVehical;
		this.totalNumberOfVehicalRented = totalNumberOfVehicalRented;
		this.totalRentEarned = totalRentEarned;
	}
}
