package com.vehicalrental.entity;

import java.util.Date;

import com.vehicalrental.enums.VehicalTypes;

public class Customers {
	private String customerName;
	private int vehicalRegisterationNumber;
	private Date fromDate;
	private Date toDate;
	private long totalRent; // daily rent
	public Customers(String customerName, int vehicalRegisterationNumber, Date fromDate,
			Date toDate, long totalRent) {
		super();
		this.customerName = customerName;
		this.vehicalRegisterationNumber = vehicalRegisterationNumber;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.totalRent = totalRent;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public int getVehicalRegisterationNumber() {
		return vehicalRegisterationNumber;
	}
	public void setVehicalRegisterationNumber(int vehicalRegisterationNumber) {
		this.vehicalRegisterationNumber = vehicalRegisterationNumber;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public long getTotalRent() {
		return totalRent;
	}
	public void setTotalRent(long totalRent) {
		this.totalRent = totalRent;
	}
	
	@Override
	public String toString() {
		return "Customers [customerName=" + customerName
				+ ", vehicalRegisterationNumber=" + vehicalRegisterationNumber + ", fromDate=" + fromDate + ", toDate="
				+ toDate + ", totalRent=" + totalRent + "]";
	}
}
