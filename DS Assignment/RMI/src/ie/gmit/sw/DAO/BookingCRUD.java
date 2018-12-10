package ie.gmit.sw.DAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.xml.bind.JAXBException;

import ie.gmit.sw.model.RentalOrder;

public interface BookingCRUD {
	public boolean deleteOrder( String bookingID) throws SQLException ;
	public boolean createOrder(RentalOrder ro) throws SQLException;
	public List<RentalOrder> getAll() throws JAXBException, IOException ;
	public boolean updateOrder(RentalOrder ro) throws SQLException ;
}
