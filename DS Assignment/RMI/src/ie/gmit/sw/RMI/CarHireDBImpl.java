package ie.gmit.sw.RMI;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import ie.gmit.sw.DAO.CreateRentalOrder;
import ie.gmit.sw.DAO.DAO;
import ie.gmit.sw.DAO.DeleteRentalOrder;
import ie.gmit.sw.DAO.GetAllRentalOrders;
import ie.gmit.sw.model.Customer;
import ie.gmit.sw.model.RentalOrder;
import ie.gmit.sw.model.Vehicle;

// this is my actual RMI object performing operations on the DB
public class CarHireDBImpl extends UnicastRemoteObject implements CarHireDB, Serializable {

	private static final long serialVersionUID = 1L;
	private DAO db;

	public CarHireDBImpl() throws RemoteException {
		try {
			this.db = new DAO();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<RentalOrder> getAllBookings() throws RemoteException {
		List<RentalOrder> ro = null;
		try {
			System.out.println("about to getall booking..");
			ro = db.getAll();
			return ro;
		} catch (JAXBException | IOException e) {
			e.printStackTrace();
		}
		return ro;
	}

	@Override
	public boolean addBooking(RentalOrder booking) throws RemoteException, SQLException {
		System.out.println("about to create booking..");
		boolean success = db.createOrder(booking);
		return success;
	}

	@Override
	public boolean deleteBooking(String bookingID) throws RemoteException, SQLException {
		System.out.println("about to delete booking..");
		return db.deleteOrder(bookingID);
	}

	@Override
	public boolean updateBooking(RentalOrder booking) throws RemoteException {
		System.out.println("about to update booking..");
		try {
			return db.updateOrder(booking);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}



}