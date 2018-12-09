package ie.gmit.sw.RMI;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import ie.gmit.sw.RMI.CarHireDB;

public class test implements Serializable{

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		CarHireDB  cs;
		cs = (CarHireDB) Naming.lookup("rmi://localhost:1099/carHireDB");
		
		cs.getBooking("4444");

	}
}
