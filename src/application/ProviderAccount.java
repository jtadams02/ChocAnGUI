package application;


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

    /**
     * The constructor that will actually be used, this will create a ProviderAccount object that matches ID with databse and retrieves data
     * @param providerID
     */
    public ProviderAccount(int providerID)
    {
        this.accountType = 4;
        this.idNumber = providerID;
        this.getProviderAccount(); // this will retrive info from databse and set the values of the object to them
    }

    private void getProviderAccount()
    {
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
            this.password = "you will never guess this password muwahahahahahaha";
        }
    }
    public static boolean startBilling()
    {
        // Not sure how billing will work yet, will this push to a billingDB?
        System.out.println("----------------\nPlease enter the amount to be billed: ");
        float fee = scan.nextFloat();
        return true;
    }

    public void updateProvider(String inputName,String inputAddress,String inputCity,String inputState,int inputZip,float inputFees, int inputConsul)
    {
        // This will take input, edit the values of the object, and then push it to the databse
        this.name = inputName;
        this.address = inputAddress;
        this.city = inputCity;
        this.state = inputState;
        this.zip = inputZip;
        this.feesPayable = inputFees;
        this.numConsultations = inputConsul;
        
        // Now send this object to the DB
        System.out.println("Updated Provider!");
    }

    // These two functions just get the ID and Name
    public int getId(){
        return this.idNumber;
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
