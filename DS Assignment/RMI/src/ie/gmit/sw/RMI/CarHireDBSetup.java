package ie.gmit.sw.RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;


// Register the RMI Car Hire DB service with registry so that RMI clients can lookup and access 

public class CarHireDBSetup {

public static void main(String[] args) throws MalformedURLException {
	
		// create an instance of car hire service
		CarHireDB cs = null;
		
		// assign it to concrete object 
		try {
			cs = new CarHireDBImpl();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Start the RMI regstry on port 1099
		// give access to the object via the registry
		try {
			LocateRegistry.createRegistry(1099);
			//Bind our remote object to the registry with the human-readable name "fileService"
			Naming.rebind("carHireDB", cs);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		//Print a nice message to standard output
		System.out.println("Server ready.");
	}
}