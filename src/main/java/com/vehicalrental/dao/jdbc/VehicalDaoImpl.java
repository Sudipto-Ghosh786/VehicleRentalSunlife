package com.vehicalrental.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vehicalrental.constants.QueryConstants;
import com.vehicalrental.dao.VehicalDao;
import com.vehicalrental.entity.Vehical;
import com.vehicalrental.enums.FuelTypes;
import com.vehicalrental.enums.VehicalTypes;
import com.vehicalrental.exception.DataAccessException;
import com.vehicalrental.vo.VehicalRentDetailVO;
import com.vehicalrental.entity.Customers;

public class VehicalDaoImpl extends BaseDAO implements VehicalDao {
	private Connection con = null;
	private PreparedStatement ps = null;
	
	@SuppressWarnings("finally")
	@Override
	public int addVehical(Vehical vehical) throws DataAccessException {
		try {
			con = getConnection();
			ps = con.prepareStatement(QueryConstants.ADD_VEHICAL_QUERY);
			ps.setInt(1, vehical.getRegistrationNumber());
			ps.setString(2, vehical.getVehicalType().toString());
			ps.setString(3, vehical.getManufacturer());
			ps.setInt(4, vehical.getDailyRent());
			ps.setString(5, vehical.getFuelType().toString());
			System.out.println("Added Vehicle details successfully.");
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException("Unable to add Data !");
		} finally {
			return 0;
		}
	}
	
	@SuppressWarnings("finally")
	@Override
	public int addBookingDetails(Customers customer) throws DataAccessException {
		try {
			con = getConnection();
			ps = con.prepareStatement(QueryConstants.SET_CUSTOMER_TO_DB_QUERY);
			ps.setString(1, customer.getCustomerName());
			ps.setInt(2, customer.getVehicalRegisterationNumber());
			ps.setTimestamp(3, new java.sql.Timestamp(customer.getFromDate().getTime()));
			ps.setTimestamp(4, new java.sql.Timestamp(customer.getToDate().getTime()));
			ps.setInt(5, (int) customer.getTotalRent());
			System.out.println("Added Customer details successfully.");
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException("Unable to add Data !");
		} finally {
			return 0;
		}
	}

	@Override
	public List<VehicalRentDetailVO> getVehicalReport(Month month) throws DataAccessException {
		List<VehicalRentDetailVO> list = new ArrayList<>();
		try {
			con = getConnection();
			ps = con.prepareStatement(QueryConstants.GET_VEHICAL_REPORT_QUERY);
			ps.setInt(1, month.getValue());
			ps.setInt(2, month.getValue());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String vehicalType = rs.getString(1);
				int totalNumberOfVehical = rs.getInt(2);
				int totalVehicalRented = rs.getInt(3);
				int totalMoneyCollected = rs.getInt(4);
				list.add(new VehicalRentDetailVO(vehicalType, 
						totalNumberOfVehical, 
						totalVehicalRented, 
						totalMoneyCollected));
			}
		} catch (SQLException e) {
			throw new DataAccessException("Unable to fetch data !!");
		}
		return list;
	}

	@Override
	public Vehical getVehicalDetailFromId(int registerationNumber) throws DataAccessException {
		Vehical vehicalDetail = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(QueryConstants.GET_SPECIFIC_VEHICAL_DETAIL_QUERY);
			ps.setInt(1, registerationNumber);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				vehicalDetail = new Vehical(
						rs.getInt(1),
						VehicalTypes.valueOf(rs.getString(2)),
						rs.getString(3),
						rs.getInt(4),
						FuelTypes.valueOf(rs.getString(5))
						);
			}
		} catch (Exception e) {
			throw new DataAccessException("Unable to fetch specific vehical detail !");
		}
		return vehicalDetail;
	}

	@Override
	public List<Customers> getCustomersInSpecificDateRange(Date fromDate, Date toDate) throws DataAccessException {
		List<Customers> list = new ArrayList<>();
		try {
			con = getConnection();
			ps = con.prepareStatement(QueryConstants.GET_CUSTOMERS_FOR_DATE_RANGE_QUERY);
			java.sql.Timestamp fromTimeStamp = new java.sql.Timestamp(fromDate.getTime());
			java.sql.Timestamp toTimeStamp= new java.sql.Timestamp(toDate.getTime());
			ps.setTimestamp(1, fromTimeStamp);
			ps.setTimestamp(2, toTimeStamp);
			ps.setTimestamp(3, fromTimeStamp);
			ps.setTimestamp(4, toTimeStamp);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Customers(
						rs.getString(2),
						rs.getInt(3),
						rs.getTimestamp(4),
						rs.getTimestamp(5),
						rs.getInt(6)
						));
			}
		} catch (SQLException e) {
			throw new DataAccessException("Unable to fetch data !!");
		}
		return list;
	}

}
