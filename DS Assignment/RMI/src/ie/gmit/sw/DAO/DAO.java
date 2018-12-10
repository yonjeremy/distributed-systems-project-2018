package ie.gmit.sw.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.JAXBException;

import ie.gmit.sw.model.Customer;
import ie.gmit.sw.model.RentalOrder;
import ie.gmit.sw.model.Vehicle;

public class DAO implements BookingCRUD {

public DAO(CreateRentalOrder createOp, DeleteRentalOrder deleteOp, UpdateRentalOrder updateOp,
			GetAllRentalOrders getAllOp, 
			Connection conn) {
		super();
		this.createOp = createOp;
		this.deleteOp = deleteOp;
		this.updateOp = updateOp;
		this.getAllOp = getAllOp;
		
		this.conn = conn;
	}

	//	// DB Operations
//	// compose & delegate
	private CreateRentalOrder createOp;
	private DeleteRentalOrder deleteOp;
	private UpdateRentalOrder updateOp;
	private GetAllRentalOrders getAllOp;
	
	/** The name of the MySQL account to use (or empty for anonymous) */
	private final String userName = "root";

	/** The password for the MySQL account (or empty for anonymous) */
	private final String password = "";

	/** The name of the computer running MySQL */
	private final String serverName = "localhost";

	/** The port of the MySQL server (default is 3306) */
	private final int portNumber = 3306;

	/**
	 * The name of the database we are testing with (this default is installed with
	 * MySQL)
	 */
	private final String dbName = "carrental";

	private Connection conn;


	public DAO() throws SQLException {
		this.createOp = new CreateRentalOrder();
		this.deleteOp = new DeleteRentalOrder();
		this.updateOp = new UpdateRentalOrder();
		this.getAllOp = new GetAllRentalOrders();
		

		// get connection
		this.conn = getConnection();
	}

	public Connection getConnection() {
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);

		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://" + this.serverName + ":" + this.portNumber + "/" + this.dbName, connectionProps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;
	}

	@Override
	public boolean deleteOrder(String bookingID) throws SQLException {
		try {
			return deleteOp.deleteOrder(this.conn, bookingID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean createOrder(RentalOrder ro) throws SQLException {
		System.out.println(this.conn);
		try {
			return this.createOp.createOrder(this.conn, ro);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<RentalOrder> getAll() throws JAXBException, IOException {
		return this.getAllOp.getAll(this.conn);
	}

	@Override
	public boolean updateOrder(RentalOrder ro) throws SQLException {
		try {
			return updateOp.updateOrder(this.conn, ro);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}



}
