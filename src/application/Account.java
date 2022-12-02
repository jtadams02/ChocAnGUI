package application;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This class is largely useless
 * No constructors will be used
 * This is just a template for MemberAccount, ManagerAccount, and OperatorAccount
 * I guess it verifies things as well 
 * 
 * @author JT Adams
 */
public class Account 
{
    int accountType; // This will determine which type of account it is 1=Operator ,2=Member ,3=Manager ,4=Provider. 
    int idNumber;  // This number will either determine which database to look at, if we have one for each account type
    String name; // Or to ignore those without this ID if we have one large database. 3 different ones might be easier 
    String address;
    String city;
    String state;
    int zip;
    String password;
    // boolean isSuspended;

    /**
     * Basic constructor function **SHOULD NOT BE CALLED** Just doing this for testing JIC idk
     */
    public Account() {
        idNumber = -1;
        name = "Placeholder";
        address = "Placeholder";
        city = "Placeholder";
        state = "Placeholder";
        zip = -1;
    }

    /*
    * This verify function determines 
    */
    public boolean verify()
    { 
        // Verify Operator
        if(this.accountType == 1)
        {
            //Grabbing all possible operators
        	List<OperatorAccount> list = OperatorAccount.getOperatorAccounts();
        	
        	// Now we verify, no need to get information yet
        	for(int i=0;i<list.size();i++) 
        	{
        		if(this.idNumber==list.get(i).idNumber&&this.password.compareTo(list.get(i).password)==0) 
        		{
        			return true;
        		}
        	}
        	return false;
        }
        // Verify Member
        if(this.accountType==2)
        {
            /*List<Account> accounts = GetAccounts();
            List<Account> accountsSorted;*/

        
        }
        // Verify Manager
        if(this.accountType==3)
        {
           //Grabbing all possible managers
        	List<ManagerAccount> list = ManagerAccount.getManagerAccounts();
        	
        	// Now we verify, no need to get information yet
        	for(int i=0;i<list.size();i++) 
        	{
        		if(this.idNumber==list.get(i).idNumber&&this.password.compareTo(list.get(i).password)==0) 
        		{
        			return true;
        		}
        	}
        	return false;
        }

        // Verify Provider
        if(this.accountType==4)
        {
            // Just realized, all of these verify functions are detailed for no reason lol, all the constructors grab the password oops
            // security in here is really bad
        }
        return true;
    }

    public List<Account> getAccounts() {
        List<Account> accounts = new ArrayList<Account>();
        Database db = new Database();
        try {
            ResultSet rlt = db.GetData("account");
            while (rlt.next()) {
                Account a = new Account();
                a.idNumber = rlt.getInt("idNumber");
                a.accountType = rlt.getInt("accountType");
                a.name = rlt.getString("name");
                a.address = rlt.getString("address");
                a.city = rlt.getString("city");
                a.state = rlt.getString("state");
                a.zip = rlt.getInt("zip");
                //  a.isSuspended = rlt.getBoolean("isSuspended");
                a.password = rlt.getString("password");
                
                accounts.add(a);
                // if (!a.isSuspended) accounts.add(a);
            }
        }
        catch (Exception exception) {
            System.out.println(exception);
        }
        
        return accounts;
    }

    public void AddAccount() {
        Database db = new Database();
        
        try {
            PreparedStatement stm = db.connection.prepareStatement("INSERT INTO account (accountType, name, address, city, state, zip) VALUES (?,?,?,?,?,?)");
            stm.setInt(1, this.accountType);
            stm.setString(2, this.name);
            stm.setString(3, this.address);
            stm.setString(4,  this.city);
            stm.setString(5, this.state);
            stm.setInt(6, this.zip);
            
            stm.executeUpdate();
        }
        catch (Exception exception) {
            System.out.println(exception);
        }
    }
}

