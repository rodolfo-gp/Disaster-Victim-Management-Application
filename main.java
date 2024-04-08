
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
    public static String selectAllNames(String tableName) {
		String data = "";

        try {
            // Create a statement using the existing connection
            Statement statement = dbConnect.createStatement();

			// Execute the SQL query
			String query = "SELECT firstName, lastName FROM " + tableName;
			ResultSet resultSet = statement.executeQuery(query);

			// Process the result set
			while (resultSet.next()) {
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				data += lastName + ", " + firstName + "\n";
			}

			// Close the result set
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Return the result
		return data;
	}
    public static void main(String[] args) {
        set_DB_info("jdbc:postgresql://localhost/ensf380project","oop","ucalgary");
        initializeConnection();
        String data = selectAllNames("inquirer");
        System.out.println(data);
    }
    
}
