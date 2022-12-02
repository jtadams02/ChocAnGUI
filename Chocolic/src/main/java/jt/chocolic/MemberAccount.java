package jt.chocolic;

import java.lang.reflect.Member;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * TODO: Add class desription here
 * 
 * @author JT Adams
 */
public class MemberAccount extends Account 
{
    float feesOwed;
    boolean isSuspended;

	/**
	 * Default constructor, should not be called
	 */
    public MemberAccount()
    {
        this.accountType = 2;
    }

	/**
	 * This constructor will build a MemberAccount given the memberID
	 * @param memberId
	 */
	public MemberAccount(int memberId)
	{
		this.accountType = 2;
		this.idNumber = memberId;
		this.getMemberInfo(); // Get info from database
	}

	/**
	 * This private function retrieves all the member's info from the database based on the ID number
	 */
	private void getMemberInfo()
	{
		int idLookup = this.idNumber;
		List<MemberAccount> lookup = getMemberAccounts();
		for(int i=0; i < lookup.size(); i++)
		{
			if (lookup.get(i).idNumber == idLookup)
			{
				MemberAccount temp = lookup.get(i);
				this.name = temp.name;
				this.address = temp.address;
				this.city = temp.city;
				this.state = temp.state;
				this.zip = temp.zip;
				this.feesOwed = temp.feesOwed;
				this.isSuspended = temp.isSuspended;
				this.password = temp.password;
			}
		}
	}

	/**
	 * This function will take input via textfields from the GUI
	 * and send an SQL statement which will add a Member to the DB
	 * with the the details provided
	 */
	public void addMemberAccount(String inputName, String inputPassword, String inputAddress, String inputCity, String inputState, int inputZip, float inputFee)
	{
		this.accountType=2;
		this.name = inputName;
		this.password = inputPassword;
		this.address = inputAddress;
		this.city = inputCity;
		this.state = inputState;
		this.zip = inputZip;
		this.feesOwed = inputFee;
		
		Database db = new Database();
		
		try {
			PreparedStatement stm = db.connection.prepareStatement("INSERT INTO `freedb_ChocAn`.`account` (`accountType`, `password`, `name`, `address`, `city`, `state`, `zip`,`feesOwed`) VALUES (?,?,?,?,?,?,?,?)");
			stm.setInt(1, this.accountType);
			stm.setString(2, this.password);
			stm.setString(3, this.name);
			stm.setString(4, this.address);
			stm.setString(5,  this.city);
			stm.setString(6, this.state);
			stm.setInt(7, this.zip);
			stm.setFloat(8, this.feesOwed);
			
			stm.executeUpdate();
		} catch (Exception exception)
		{
			System.out.println(exception);
		}
	}

	/**
	 * Updates the member's account information with the parameter inputs
	 * 
	 * @param inputName
	 * @param inputAddress
	 * @param inputCity
	 * @param inputState
	 * @param inputZip
	 */
	public void updateMember(String inputName, String inputPassword, String inputAddress, String inputCity, String inputState, int inputZip, float inputFees)
	{
		// This function will take user input and then update the corresponding member based on member ID
		this.name = inputName;
		this.password = inputPassword;
		this.address = inputAddress;
		this.city = inputCity;
		this.state = inputState;
		this.zip = inputZip;
		this.feesOwed = inputFees;

		// Then we send this object to the database, updating the matching ID number in the database
		// ID NUMBER DOES NOT GET UPDATED
		// Print out "Successfully updated member" if it works
		Database db = new Database();
		
		try {
			PreparedStatement stm = db.connection.prepareStatement("UPDATE `freedb_ChocAn`.`account` SET `name`=?, `password`=?, `address`=?, `city`=?, `state`=?, `zip`=?, `feesOwed`=? WHERE (`idNumber`='"+this.idNumber+"');");
			//stm.setInt(1, this.accountType);
			stm.setString(1, this.name);
			stm.setString(2,this.password);
			stm.setString(3, this.address);
			stm.setString(4, this.city);
			stm.setString(5, this.state);
			stm.setInt(6, this.zip);
			stm.setFloat(7, this.feesOwed);

			stm.executeUpdate();
		} catch (Exception exception)
		{
			System.out.println(exception);
		}
			
	}

	/**
	 * Suspends a member's account
	 */
	public void suspendAccount(int id) {
		this.idNumber = id;
		Database db = new Database();
		
		try {
			PreparedStatement stm = db.connection.prepareStatement("UPDATE `freedb_ChocAn`.`account` SET `isSuspended`=? WHERE (`idNumber`=?);");
			stm.setBoolean(1, true);
			stm.setInt(2, this.idNumber);
			
			stm.executeUpdate();
		}
		catch (Exception exception) {
			System.out.println(exception);
		}
	}

	/**
	 * Reinstates a user's account
	 */
	public void reinstateAccount(int id)
	{
		this.idNumber = id;
		Database db = new Database();
		
		try {
			PreparedStatement stm = db.connection.prepareStatement("UPDATE `freedb_ChocAn`.`account` SET `isSuspended`='0' WHERE (`idNumber`=?);");
			stm.setInt(1, this.idNumber);
			
			stm.executeUpdate();
		}
		catch (Exception exception) {
			System.out.println(exception);
		}
	}

	public void deleteMember(int id) 
    {
    	this.idNumber = id;
    	
    	Database db = new Database();
    	
    	try {
    		PreparedStatement stm = db.connection.prepareStatement("DELETE FROM `freedb_ChocAn`.`account` WHERE (`idNumber` = ?);");
    		stm.setInt(1,this.idNumber);
    		
    		stm.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e);
		}
    	
    }
	// These are just cute little helper functions :)
	public int getId()
	{
		return this.idNumber;
	}
	public String getName()
	{
		return this.name;
	}
	public String toString()
	{
		String out = ("Member ID: "+this.idNumber+"\nName: "+this.name+"\nAddress: "+this.address+"\nCity: "+this.city+"\nState: "+this.state+"\nZip: "+this.zip+"\nFees owed: "+this.feesOwed+"\nSuspended: "+this.isSuspended);
		return out;
	}

    // First attempt at an override function, its not really an override lol
    // This just gets the information from the DB that a Member would need.
    public static List<MemberAccount> getMemberAccounts()
    {
        List<MemberAccount> accounts = new ArrayList<MemberAccount>();
	    Database db = new Database();
	    try {
		    ResultSet rlt = db.GetData("account");
		    while (rlt.next()) 
            {
			    MemberAccount a = new MemberAccount();
			    a.idNumber = rlt.getInt("idNumber");
                a.password = rlt.getString("password");
			    a.accountType = rlt.getInt("accountType");
			    a.name = rlt.getString("name");
			    a.address = rlt.getString("address");
			    a.city = rlt.getString("city");
			    a.state = rlt.getString("state");
			    a.zip = rlt.getInt("zip");
			    a.isSuspended = rlt.getBoolean("isSuspended");
                a.feesOwed = rlt.getFloat("feesOwed");
			
			     // if (!a.isSuspended) accounts.add(a);

                 // This will only add members to return
                 if(a.accountType==2){accounts.add(a);}
		    }
	}
	catch (Exception exception) {
		System.out.println(exception);
	}
	
	return accounts;
    }


}

