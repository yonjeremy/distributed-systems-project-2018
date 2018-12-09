package ie.gmit.sw.RMI;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import javax.xml.bind.JAXBException;

import ie.gmit.sw.DAO.GetRentalOrder;
import ie.gmit.sw.model.RentalOrder;

// this is my actual RMI object performing operations on the DB
public class CarHireDBImpl  extends UnicastRemoteObject implements CarHireDB {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CarHireDBImpl() throws RemoteException  {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public RentalOrder getBooking(String bookingID) throws RemoteException{
		GetRentalOrder gro = new GetRentalOrder();
		RentalOrder ro = null;

		try {
			ro = gro.get(bookingID);
		} catch (JAXBException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}


	@Override
	public ArrayList<RentalOrder> getAllBookings() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addBooking(RentalOrder booking) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBooking(RentalOrder booking) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBooking(RentalOrder booking) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	

		
}