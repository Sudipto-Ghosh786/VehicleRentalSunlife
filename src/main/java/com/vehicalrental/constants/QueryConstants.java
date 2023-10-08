package com.vehicalrental.constants;

public class QueryConstants {
	public static final String ADD_VEHICAL_QUERY = "INSERT INTO Vehical VALUES (?, ?, ?, ?, ?)";
	public static final String GET_VEHICAL_REPORT_QUERY = "SELECT t1.vehicalCategory, t1.totalVehicalCount, t2.totalVehicalRented, t2.totalMoney AS totalMoneyEarned  FROM (\r\n"
			+ "SELECT v.vehicalCategory, COUNT(DISTINCT v.regNo) AS totalVehicalCount\r\n"
			+ "FROM Vehical v LEFT JOIN Customer c ON v.regNo=c.regNo GROUP BY v.vehicalCategory\r\n"
			+ ") AS t1 LEFT JOIN (\r\n"
			+ "SELECT v.vehicalCategory, SUM(c.totalRent) AS totalMoney, COUNT(DISTINCT c.regNo) \r\n"
			+ "AS totalVehicalRented FROM Vehical v LEFT JOIN Customer c ON v.regNo=c.regNo\r\n"
			+ "WHERE MONTH(c.dateFrom) <= ? AND MONTH(c.dateTo) >= ? GROUP BY v.vehicalCategory\r\n"
			+ ") AS t2 ON t1.vehicalCategory = t2.vehicalCategory";
	public static final String GET_SPECIFIC_VEHICAL_DETAIL_QUERY = "SELECT * FROM Vehical WHERE regNo=?";
	public static final String GET_CUSTOMERS_FOR_DATE_RANGE_QUERY = 
			"SELECT * FROM Customer WHERE (dateFrom >= ? AND dateFrom <= ?) "
			+ "OR (dateTo >= ? AND dateTo <= ?)";
	public static final String SET_CUSTOMER_TO_DB_QUERY = "INSERT INTO Customer(customerName, regNo, "
			+ "dateFrom, dateTo, totalRent) VALUES (?, ?, ?, ?, ?)";
}
