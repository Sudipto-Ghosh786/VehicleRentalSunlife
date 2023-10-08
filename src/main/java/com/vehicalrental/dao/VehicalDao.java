package com.vehicalrental.dao;

import java.time.Month;
import java.util.Date;
import java.util.List;

import com.vehicalrental.entity.Customers;
import com.vehicalrental.entity.Vehical;
import com.vehicalrental.exception.DataAccessException;
import com.vehicalrental.vo.VehicalRentDetailVO;

public interface VehicalDao {
	public abstract int addVehical(Vehical vehical) throws DataAccessException;
	public abstract List<VehicalRentDetailVO> getVehicalReport(Month month) throws DataAccessException;
	public abstract int addBookingDetails(Customers customer) throws DataAccessException;
	public abstract Vehical getVehicalDetailFromId(int registerationNumber) throws DataAccessException;
	public abstract List<Customers> getCustomersInSpecificDateRange(Date fromDate, Date toDate) throws DataAccessException;
}
