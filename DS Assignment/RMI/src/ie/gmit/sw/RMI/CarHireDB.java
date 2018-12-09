package ie.gmit.sw.RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import ie.gmit.sw.model.RentalOrder;

public interface CarHireDB  extends Remote {
	
	//=== CRUD === //
	
	// READ
	public RentalOrder getBooking(String bookingID) throws RemoteException;
	public ArrayList<RentalOrder> getAllBookings() throws RemoteException ;
	//CREATE
	public boolean addBooking(RentalOrder booking) throws RemoteException;
	//REMOVE
	public boolean deleteBooking(RentalOrder booking) throws RemoteException;
	// UPDATE
	public boolean updateBooking(RentalOrder booking) throws RemoteException;

	
}
