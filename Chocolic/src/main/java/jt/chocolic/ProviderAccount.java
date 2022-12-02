	package jt.chocolic;


import java.util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The provider's account class
 * 
 * @author JT Adams
 */
public class ProviderAccount extends Account
{
    static Scanner scan = new Scanner(System.in);
    float feesPayable;
    int numConsultations;

    /**
     * Default Constructor, just defines account type
     */
    public ProviderAccount()
    {
        this.accountType = 4;
    }
    
    // This contructor is used for adding via GUI
    public ProviderAccount(int provID) {
    	this.idNumber=provID;
    	this.accountType=4;
    }

    /**
     * The constructor that will actually be used, this will create a ProviderAccount object that matches ID with databse and retrieves data
     * @param providerID
     */
    public ProviderAccount(int providerID, String pass)
    {
        this.accountType = 4;
        this.idNumber = providerID;
        this.password = pass;
        this.getProviderAccount(); // this will retrive info from databse and set the values of the object to them
    }

    private void getProviderAccount()
    {
    	// Very very crude
        int idLookup = this.idNumber;
        // Use this id to match it to the corresponding row/column in database (idk how the data is setup)
        List<ProviderAccount> list = getProviderAccounts();
        boolean foundAccount = false;
        for (int i = 0; i < list.size(); i++)
        {
            if (list.get(i).idNumber == idLookup)
            {
                this.name = list.get(i).name;
                this.address = list.get(i).address;
                this.city = list.get(i).city;
                this.state = list.get(i).state;
                this.zip = list.get(i).zip;
                this.feesPayable = list.get(i).feesPayable;
                this.numConsultations = list.get(i).numConsultations;
                this.password = list.get(i).password;
                foundAccount = true;
            }
        } 
        if (!foundAccount)
        {
            this.idNumber = -1;
            this.name = "null";
            this.address = "null";
            this.city = "null";
            this.state = "null";
            this.zip = 0;
            this.feesPayable = 0;
            this.numConsultations = 0;
            this.password = "1110"; // 14 is the greatest number btw
        }
    }
    
    

    public void updateProvider(String inputName,String inputPassword,String inputAddress,String inputCity,String inputState,int inputZip)
    {
        // This will take input, edit the values of the object, and then push it to the databse
        this.name = inputName;
        this.password = inputPassword;
        this.address = inputAddress;
        this.city = inputCity;
        this.state = inputState;
        this.zip = inputZip;
        
        Database db = new Database();
        
        try {
    		PreparedStatement stm = db.connection.prepareStatement("UPDATE `freedb_ChocAn`.`account` SET `name`=?, `address`=?, `city`=?, `state`=?, `zip`=? WHERE (`idNumber`='"+this.idNumber+"');");
    		stm.setString(1, this.name);
    		stm.setString(2, this.address);
    		stm.setString(3, this.city);
    		stm.setString(4, this.state);
    		stm.setInt(5, this.zip);
    		stm.executeUpdate();
    	}
    	catch (Exception exception) {
    		System.out.println(exception);
    	}
    }
    
    public void addProviderAccount(String inputName,String inputPassword, String inputAddress,String inputCity,String inputState,int inputZip) {
    	Database db = new Database();
    	this.name = inputName;
    	this.password = inputPassword;
        this.address = inputAddress;
        this.city = inputCity;
        this.state = inputState;
        this.zip = inputZip;
    	
    	try {
    		PreparedStatement stm = db.connection.prepareStatement("INSERT INTO `freedb_ChocAn`.`account` (`accountType`, `password`, `name`, `address`, `city`, `state`, `zip`) VALUES (?,?,?,?,?,?,?)");
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
    
    // This function will also remove all correlating services from Service database
    public void deleteProvider(int id) 
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
    	
    	try {
    		PreparedStatement stm = db.connection.prepareStatement("DELETE FROM `freedb_ChocAn`.`service` WHERE (`providerId` = ?);");
    		stm.setInt(1,this.idNumber);
    		
    		stm.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e);
		}
    	
    }
    

    // These two functions just get the ID and Name
    public int getId(){
        return this.idNumber;
    }
    
    public int getConsultations() 
    {
    	int numConsultations = 0;
    	List<Service> services = Service.getServices();
    	for(int i=0;i<services.size();i++) 
    	{
    		if(services.get(i).providerId==this.idNumber) 
    		{
    			numConsultations++;
    		}
    	}
		return numConsultations;
    }
    
    public float getFeesPayable() 
    {
    	float feesPayable =0;
    	List<Service> services = Service.getServices();
    	for(int i=0;i<services.size();i++) 
    	{
    		if(services.get(i).providerId==this.idNumber) 
    		{
    			feesPayable+=services.get(i).fee;
    		}
    	}
    	
		return feesPayable;
    	
    }
    public String getName()
    {
        return this.name;
    }

    public static List<ProviderAccount> getProviderAccounts() {
        List<ProviderAccount> accounts = new ArrayList<ProviderAccount>();
        Database db = new Database();
        try {
            ResultSet rlt = db.GetData("account");
            while (rlt.next()) {
                if(rlt.getInt("accountType")==4){
                    ProviderAccount a = new ProviderAccount();
                    a.idNumber = rlt.getInt("idNumber");
                    a.accountType = rlt.getInt("accountType");
                    a.name = rlt.getString("name");
                    a.address = rlt.getString("address");
                    a.city = rlt.getString("city");
                    a.state = rlt.getString("state");
                    a.zip = rlt.getInt("zip");
                    a.password = rlt.getString("password");
                    a.numConsultations = rlt.getInt("numConsultations");
                    a.feesPayable = rlt.getFloat("feesPayable");

                    if (a.accountType == 4) accounts.add(a);
                }
            }
        }
        catch (Exception exception) {
            System.out.println(exception);
        }
        
        return accounts;
    }
}
