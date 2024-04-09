
import java.sql.*;

public class main {

	public static String DBURL;
	public static String USERNAME;
	public static String PASSWORD;    
	
	public static Connection dbConnect;
	public static ResultSet results;

    public static void set_DB_info(String url, String user, String pw){
		// Database URL
		DBURL = url;
		//  Database credentials
		USERNAME = user;
		PASSWORD = pw;
	}
    public static void initializeConnection() {
		try {
			dbConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
			System.out.println("Database connection established successfully.");
		} catch (SQLException e) {
			System.err.println("Failed to connect to the database: " + e.getMessage());
		}
	}
	public void close() {
		
		try {
			results.close();
			dbConnect.close();
			System.out.println("Disconnected from database");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	////////////////////////////////////////////////////////QUIRIES////////////////////////////////////////////////////////
    public static String selectAllNames(String tableName) {
		String data = "";
		if (tableName.toLowerCase().equals("inquiry_log") || tableName.toLowerCase().equals("inquirylog")) {
			try {
				// Create a statement using the existing connection
				Statement statement = dbConnect.createStatement();

				// Execute the SQL query
				String query = "SELECT id, inquirer, callDate, details FROM INQUIRY_LOG";
				ResultSet resultSet = statement.executeQuery(query);

				// Process the result set
				data += "id, inquirer_ID, callDate, details \n";
				while (resultSet.next()) {
					String id = resultSet.getString("id");
					String inquirer = resultSet.getString("inquirer");
					String callDate = resultSet.getString("callDate");
					String details = resultSet.getString("details");
					data += id + ", " + inquirer + ", " + callDate + "," + details + "\n";
				}
				// Close the result set
				resultSet.close();
				return data;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			try {
				// Create a statement using the existing connection
				Statement statement = dbConnect.createStatement();

				// Execute the SQL query
				String query = "SELECT id, firstName, lastName FROM " + tableName;
				ResultSet resultSet = statement.executeQuery(query);

				// Process the result set
				data += "id, lastName, firstName \n";
				while (resultSet.next()) {
					String id = resultSet.getString("id");
					String firstName = resultSet.getString("firstName");
					String lastName = resultSet.getString("lastName");
					data += id + ", " + lastName + ", " + firstName + "\n";
				}

				// Close the result set
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		// Return the result
		return data;
	}
	public static boolean validateInquirer(String fName, String lName){//to see if inquirer already exists
		
		boolean validIquirer = false;
	 	try {                    
			Statement myStmt = dbConnect.createStatement();
			// Execute SQL query
			results = myStmt.executeQuery("SELECT firstName, lastName FROM INQUIRER");
			// Process the results set
			while (results.next()){
				//checking names are equal (accounts for lower cases)
				if(results.getString("firstName").toLowerCase().equals(fName.toLowerCase()) && results.getString("lastName").toLowerCase().equals(lName.toLowerCase()))
				validIquirer = true;
			}
			myStmt.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return validIquirer;

	}
	public static int insertInquirer(int id, String fName, String lName, int phoneNum) {
		if (validateInquirer(fName, lName)) {
			System.out.println("Inquirer Already Exists");
			return 0;
		}
		try {
			// Prepare and execute the SQL query
			String query = "INSERT INTO INQUIRER (id, firstName, lastName, phoneNumber) VALUES (?, ?, ?, ?)";
			PreparedStatement statement = dbConnect.prepareStatement(query);
			statement.setInt(1, id);
			statement.setString(2, fName);
			statement.setString(3, lName);
			statement.setInt(4, phoneNum);
			statement.executeUpdate();
			statement.close();
			System.out.println("Inquirer Succesfully Created");
			return 1;
		} 
		catch(SQLException ex){
			ex.printStackTrace();
		}
		return -1;
		
	}
	////////////////////////////////////////////////////////MAIN////////////////////////////////////////////////////////
	    public static void main(String[] args) {
        set_DB_info("jdbc:postgresql://localhost/ensf380project","oop","ucalgary");
        initializeConnection();
        
		int boo = insertInquirer(11, "Rodolfo", "Gil", 0);
		String data = selectAllNames("inquirylog");
        System.out.println(data);
		System.out.println(boo);
		
    }
    
}
