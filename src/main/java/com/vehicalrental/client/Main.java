package com.vehicalrental.client;

import java.time.Duration;
import java.time.Month;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import com.vehicalrental.dao.VehicalDao;
import com.vehicalrental.dao.jdbc.VehicalDaoImpl;
import com.vehicalrental.entity.Customers;
import com.vehicalrental.entity.Vehical;
import com.vehicalrental.enums.FuelTypes;
import com.vehicalrental.enums.VehicalTypes;
import com.vehicalrental.exception.DataAccessException;
import com.vehicalrental.keyutil.KeyBoardUtil;
import com.vehicalrental.vo.VehicalRentDetailVO;

public class Main {

	public static void main(String[] args) {
		VehicalDao dao = new VehicalDaoImpl();
		while (true) {
			System.out.println("Main Menu");
			System.out.println("1. Enter new car detail.");
			System.out.println("2. Rent a Car.");
			System.out.println("3. Fetch rent detail for specific month.");
			System.out.println("4.Exit");
			int choice = KeyBoardUtil.getInt("Please choose an option [1, 2, 3]:");
			System.out.println("\n");
			switch(choice) {
				case 1: {
					try {
						Vehical vehical = new Vehical(
								KeyBoardUtil.getInt("Registeration Number: "),
								VehicalTypes.valueOf(KeyBoardUtil.getString("Vehical Type: ")),
								KeyBoardUtil.getString("Manufacturer: "),
								KeyBoardUtil.getInt("Daily Rent: "),
								FuelTypes.valueOf(KeyBoardUtil.getString("Fuel Type: "))
								);
						dao.addVehical(vehical);
						System.out.println("Vehical Detail added successfully !! \n\n");
					} catch(Exception e) {
						System.out.println("Exception occured while adding: " + e);
					}
					break;
				}
				case 2: {
					try {
						String customerName = KeyBoardUtil.getString("Customer Name: ");
						int registerationNumber = KeyBoardUtil.getInt("Car's registeration Number: ");
						Date fromDate = KeyBoardUtil.getDate("Start Date[YYYY-MM-DD]: ");
						Date toDate = KeyBoardUtil.getDate("End Date[YYYY-MM-DD]: ");
						
						if (isCarAvailableAtDesiredDate(dao, registerationNumber, fromDate, toDate)) {
							long totalRent = calculateRent(fromDate, 
									toDate, 
									dao.getVehicalDetailFromId(registerationNumber).getDailyRent());
							String payed = KeyBoardUtil.getString("Pay " + totalRent + " for rent ?[Y/N] ");
							if (payed.toLowerCase().equals("y")) {
								dao.addBookingDetails(new Customers(
										customerName, 
										registerationNumber, fromDate, toDate, totalRent));
								System.out.println("Customer Detail added successfully !! \n\n");
							}
						} else {
							System.out.println("Car is unavailable for the given time range !!\n\n");
						}
					} catch(Exception e) {
						System.out.println("Exception occured while adding customer: " + e);
					}
					break;
				}
				case 3: {
					try {
						printRentDetail(dao, 
								Month.of(KeyBoardUtil.getInt("Enter the month [1 to 12]: ")));
					} catch(Exception e) {
						System.out.println("Exception occured while fetching the data: " + e);
					}
					break;
				}
				case 4: {
					System.out.println("!! Program Exited !!");
					System.exit(0);
					break;
				}
			}
		}
	}
	
	private static void printRentDetail(VehicalDao dao, Month month) throws DataAccessException {
		List<VehicalRentDetailVO> list = dao.getVehicalReport(month);
		System.out.println("-------- Rent Detail for " + Month.JANUARY + " are as follows ---------\n");
		for (VehicalRentDetailVO detail: list) {
			System.out.println(detail.getVehicalType() + "\t"
					+ detail.getTotalNumberOfVehical() + "\t"
					+ detail.getTotalNumberOfVehicalRented() + "\t"
					+ detail.getTotalRentEarned() + "\n\n");
		}
		System.out.println("---------- Fetched THE DATA Successfully !! -----------");
	}
	
	private static boolean isCarAvailableAtDesiredDate(VehicalDao dao, final int registerationNumber, Date fromDate, Date toDate) throws DataAccessException {
		if (fromDate.compareTo(toDate) > 0) {
			return false;
		}
		List<Customers> listOfCustomer = dao.getCustomersInSpecificDateRange(fromDate, toDate);
		return listOfCustomer.stream().filter(new Predicate<Customers>() {
			@Override
			public boolean test(Customers customer) {
				return customer.getVehicalRegisterationNumber() == registerationNumber;
			}
		}).toList().isEmpty();
	}
	
	private static long calculateRent(Date fromDate, Date toDate, int carPerDayCost) {
		return (Duration.between(fromDate.toInstant(), toDate.toInstant()).toDays() + 1) * carPerDayCost;
	}

}
