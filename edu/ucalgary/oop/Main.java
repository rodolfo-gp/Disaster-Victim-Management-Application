package edu.ucalgary.oop;
import java.util.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class Main {

	public static String DBURL;
	public static String USERNAME;
	public static String PASSWORD;    
	
	public static Connection dbConnect;
	public static ResultSet results;

	public static boolean central;
	public static boolean local;
	public static Location location;

	public static DisasterVictim currentDisasterVictim = null;
	public static Inquirer currenInquirer; 
	public static int inquirerID;
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	public static Date sqlDate = null;

    public static void set_DB_info(String url, String user, String pw){
		DBURL = url;
		USERNAME = user;
		PASSWORD = pw;
	}
    public static void initializeConnection() {
		try {
			dbConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
			System.out.println("Database connection established successfully.");
		} catch (SQLException e) {
			System.out.println("Could not connect to: " + DBURL);
			System.out.println("Username: " + USERNAME);
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
	public static int insertInquirer(int id, String fName, String lName, String phoneNum) {
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
			statement.setString(4, phoneNum);
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
	public static int insertInquiry_Log(int inquiryLogID, int inquirerID, Date callDate, String details){
		try {
			// Prepare and execute the SQL query
			String query = "INSERT INTO INQUIRY_LOG (id, inquirer, callDate, details) VALUES (?, ?, ?, ?)";
			PreparedStatement statement = dbConnect.prepareStatement(query);
			statement.setInt(1, inquiryLogID);
			statement.setInt(2, inquirerID);
			statement.setDate(3, callDate);
			statement.setString(4, details);
			statement.executeUpdate();
			statement.close();
			System.out.println("\nInquirer Log Succesfully Created");
			return 1;
		} 
		catch(SQLException ex){
			ex.printStackTrace();
		}
		return -1;
		
	}
	//////////////////////////////////Other Functions/////////////////////////////////////////////////////
	public static void showMainMenu(){
		System.out.println("What do you want to do?");
		System.out.println("1. add Disaster Victms");
		System.out.println("2. Show disaster victims");
		System.out.println("3. Search Disaster Victim");
		// System.out.println("4. add belonings to victim");
		System.out.println("5. Show Inquirers");
		System.out.println("6. Create Inquire");
		System.out.println("7. Show Inquiry_logs");
		System.out.println("8. Create Inquiry_log");
		System.err.println("0. Exit Program");
	}
	public static String makeSelection(){
		Scanner scanner = new Scanner(System.in);
		// System.out.print("Selection: ");
        String input = scanner.nextLine();
		return input;

	}
	public static void showReliefeWorkerLocations(){
		System.out.println("1. central worker");
		System.out.println("2. local worker");
	}
	public static void showData(String data){
		System.out.println(data);
		System.out.print("Press Enter to continue: ");
		makeSelection();

	}
	////////////////////////////////////////////////////////MAIN////////////////////////////////////////////////////////
	    public static void main(String[] args) {
        set_DB_info("jdbc:postgresql://localhost/ensf380project","oop","ucalgary");
        initializeConnection();
		
        String input;
		System.out.println("-------Welcom Relief worker. What do you want to do?-------");
		showReliefeWorkerLocations();
		System.out.print("selection: ");
		input = makeSelection();

		while (!input.equals("1") && !input.equals("2")) {
			System.out.println("Invalid choice. Please enter a valid option.");
			input = makeSelection();
		}
		if (input.equals("1")) {
			central = true;
			local = false;
			location = null;
			System.err.println("what location do you wish to work with?");
			System.err.print("Location name: ");
			String locName = makeSelection();
			System.out.print("Location Address: ");
			String locAddress = makeSelection();
			location = new Location(locName, locAddress);

		}else if (input.equals("2")) {
			central = false;
			local = true;
			System.err.println("what location are you from?");
			System.err.print("Location name: ");
			String locName = makeSelection();
			System.out.print("Location Address: ");
			String locAddress = makeSelection();
			location = new Location(locName, locAddress);
		}
		while (!input.equals("0")) {
			showMainMenu();
			System.out.println("selection: ");
			input = makeSelection();

			switch (input) {
				case "1":
				try {
					System.out.print("Enter First Name: ");
					String fname = makeSelection();
					System.out.print("Enter Last Name: ");
					String lName = makeSelection();
					System.out.print("Enter Date(YYYY-MM-DD): ");
					String date = makeSelection();

					DisasterVictim  victim = new DisasterVictim(fname, lName, date);

					System.out.print("Victims age(years, enter int): ");
					input = makeSelection();
					int age = Integer.parseInt(input);
					victim.setAge(age);

					System.out.print("Enter Victims gender: ");
					String gender = makeSelection();
					victim.setGender(gender);

					System.out.print("Enter Victims details/notes: ");
					String notes = makeSelection();
					victim.setNotes(notes);

					System.out.print("Enter Victims DiataryRestrictions(List all as String): ");
					String dr = makeSelection();
					victim.setDietaryRestrictions(dr);

					System.out.print("Want to family Relations(Y/N): ");
					input = makeSelection().toLowerCase();

					if(input.equals("y")){//adding Family Relations
						Set<FamilyRelation> familyRelations = new HashSet<>();
						FamilyRelation fR;
						String relation;
						DisasterVictim person;
						while (true) {

							System.err.println("Enter Other persons Info");
							System.out.print("Enter First Name(-1 to exit): ");
							fname = makeSelection();
							if (fname.equals("-1")) {
								break;
							}
							System.out.print("Enter Last Name: ");
							lName = makeSelection();
							System.out.print("Enter relation to this person: ");
							relation = makeSelection();

							person = new DisasterVictim(fname, lName, date);
							fR = new FamilyRelation(relation, person);
							familyRelations.add(fR);
						}
						victim.setFamilyRelations(familyRelations);
					}
					System.out.print("Want to Medical Records(Y/N): ");
					input = makeSelection().toLowerCase();

					if(input.equals("y")){//adding Medical Records
						Set<MedicalRecord> medicalRecords = new HashSet<>();
						MedicalRecord mR;
						String cN;
						String tD;
						String dOT;
						while (true) {

							System.err.println("Enter Medical Record Info");
							System.out.print("Enter Condition Name(-1 to exit): ");
							cN = makeSelection();
							if (cN.equals("-1")) {
								break;
							}
							System.out.print("Treatment Details: ");
							tD = makeSelection();
							System.out.print("Date of Treatment(YYYY-MM-DD): ");
							dOT = makeSelection();

							mR = new MedicalRecord(cN, tD, dOT);
							medicalRecords.add(mR);
						}
						victim.setMedicalRecords(medicalRecords);
					}

					System.out.print("Want to add personal Belonings(Y/N): ");
					input = makeSelection().toLowerCase();

					if(input.equals("y")){//adding belonings
						Set<Supply> personalBelongings = new HashSet<>();
						Supply pB;
						String itemName;
						int quantity;
						while (true) {

							System.out.print("Item Name(Enter '-1' To stop): ");
							itemName = makeSelection();
							if (itemName.equals("-1")) {
								break;
							}
							System.out.print("Quiantity of Item: ");
							input = makeSelection();
							quantity = Integer.parseInt(input);

							pB = new Supply(itemName, quantity);
							personalBelongings.add(pB);
							System.out.println("Added personal belonong to person");		
						}
						victim.setPersonalBelongings(personalBelongings);
					}
					location.addOccupant(victim);
					currentDisasterVictim = victim;


				} catch (Exception e) {
					System.err.println(e);
				}
					
					break;
					
				case "2":
					location.printOccupantsInfo();
					break;
					
				case "3":
					System.out.print("Enter Victim id(int): ");
					input = makeSelection();
					location.printOccupantDataById(Integer.parseInt(input));
					input = "qq";
					break;
					
				case "4":
					
					break;
					
				case "5":
					showData(selectAllNames("INQUIRER"));
					break;
					
				case "6":
					try {
						System.out.print("Inquirer_ID: ");
						input = makeSelection();
						inquirerID = Integer.parseInt(input);
						System.out.print("Inquirer First name: ");
						String fName = makeSelection();
						System.out.print("Inquirer Last name: ");
						String lName = makeSelection();
						System.out.print("Inquirer Phone number (XXX-XXX-XXXX): ");
						String phoneNum = makeSelection();
						// System.out.print("Inquirer info: ");
						// String info = makeSelection();
						currenInquirer = new Inquirer(fName, lName, phoneNum);
						
						insertInquirer(inquirerID, currenInquirer.getFirstName(), currenInquirer.getLastName(), currenInquirer.getServicesPhoneNum());
						
					} catch (Exception e) {
						System.err.println(e);
					}
					break;
					
				case "7":
					try {
						showData(selectAllNames("INQUIRYLOG"));
					} catch (Exception e) {
						System.err.println(e);
					}
					
					break;
				case "8":
					try {
						System.out.println("do you want to use last entered Inquirer with ID: "+ inquirerID);
						if (currenInquirer != null) {
							System.out.println("Name: " + currenInquirer.getFirstName() +" " + currenInquirer.getLastName());
						}
						
						System.out.print("Y/N: ");
						input = makeSelection().toLowerCase();

						if(input.equals("n")){
							System.out.print("Inquirer_ID: ");
							input = makeSelection();
							inquirerID = Integer.parseInt(input);
							currenInquirer = null;
						}

						System.out.print("InquiryLog_ID: ");
						input = makeSelection();
						int inquiryLog_ID = Integer.parseInt(input);

						System.out.print("Enter Date(YYYY-MM-DD): ");
						String dateString = makeSelection();
						java.util.Date utilDate = dateFormat.parse(dateString);
						sqlDate = new Date(utilDate.getTime());

						System.out.print("Enter Inquiry details: ");
						String details = makeSelection();
						
						insertInquiry_Log(inquiryLog_ID, inquirerID, sqlDate, details);

					} catch (Exception e) {
						System.err.println(e);
					}	
					break;
				case("0"):

					break;
				default:
					System.out.println("Invalid choice. Please enter a valid option.");
					break;
			}
			
		}
		System.out.println("program ended :)");
    }  
}
