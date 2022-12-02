package application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Stores the data and functions relating to the manager accounts.
 * 
 * @author JT Adams
 */
public class ManagerAccount extends Account 
{

	public ManagerAccount() {
		accountType = 3;
	}

    public ManagerAccount(int id, String pass)
    {
        this.accountType = 3;
        this.idNumber = id;
        this.password = pass;
    }
    
    
    // This requests everything from the DB that is a manager account.
    public static List<ManagerAccount> getManagerAccounts()
    {
        List<ManagerAccount> accounts = new ArrayList<ManagerAccount>();
	    Database db = new Database();
	    try {
		    ResultSet rlt = db.GetData("account");
		    while (rlt.next()) 
            {
			    ManagerAccount a = new ManagerAccount();
			    a.idNumber = rlt.getInt("idNumber");
			    a.password = rlt.getString("password");
			    a.accountType = rlt.getInt("accountType");
			    a.name = rlt.getString("name");
			    a.address = rlt.getString("address");
			    a.city = rlt.getString("city");
			    a.state = rlt.getString("state");
			    a.zip = rlt.getInt("zip");
                 if(a.accountType==3){accounts.add(a);}
		    }
		}
		catch (Exception exception) {
			System.out.println(exception);
		}
	
		return accounts;
    }
    
    public void AddManagerAccount() {
    	Database db = new Database();
    	
    	try {
    		PreparedStatement stm = db.connection.prepareStatement("INSERT INTO account (accountType, password, name, address, city, state, zip) VALUES (?,?,?,?,?,?,?)");
    		stm.setInt(1, this.accountType);
    		stm.setString(2, this.password);
    		stm.setString(3, this.name);
    		stm.setString(4, this.address);
    		stm.setString(5,  this.city);
    		stm.setString(6, this.state);
    		stm.setInt(7, this.zip);
    		
    		stm.executeUpdate();
    	}
    	catch (Exception exception) {
    		System.out.println(exception);
    	}
    }
    
    public void updateManager(String inputName,String inputAddress,String inputCity,String inputState,int inputZip)
    {
        // This function will take user input and then update the corresponding member based on member ID
        this.name = inputName;
        this.address = inputAddress;
        this.city = inputCity;
        this.state = inputState;
        this.zip = inputZip;

        // Then we send this object to the database, updating the matching ID number in the database
        // ID NUMBER DOES NOT GET UPDATED
        // Print out "Successfully updated member" if it works
        Database db = new Database();
    	
    	try {
    		PreparedStatement stm = db.connection.prepareStatement("UPDATE `freedb_ChocAn`.`account` SET `name`=?, `address`=?, `city`=?, `state`=?, `zip`=? WHERE (`idNumber`='"+this.idNumber+"');");
    		//stm.setInt(1, this.accountType);
    		stm.setString(1, this.name);
    		stm.setString(2, this.address);
    		stm.setString(3, this.city);
    		stm.setString(4, this.state);
    		stm.setInt(5, this.zip);
    		// stm.setBoolean(7, this. isSuspended);
    		// stm.setInt(6, this.idNumber);
    		stm.executeUpdate();
    	}
    	catch (Exception exception) {
    		System.out.println(exception);
    	}
            
    }
}