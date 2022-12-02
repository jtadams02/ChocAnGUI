package application;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * The operator's account class
 * 
 * @author JT Adams
 */
public class OperatorAccount extends Account 
{
	public OperatorAccount() {
		this.accountType = 1;
	}
	
    public OperatorAccount(int id, String pass)
    {
        this.accountType = 1;
        this.idNumber = id;
        this.password = pass;
    }
    
    public static List<OperatorAccount> getOperatorAccounts()
    {
        List<OperatorAccount> accounts = new ArrayList<OperatorAccount>();
	    Database db = new Database();
	    try {
		    ResultSet rlt = db.GetData("account");
		    while (rlt.next()) 
            {
			    OperatorAccount a = new OperatorAccount();
			    a.idNumber = rlt.getInt("idNumber");
			    a.password = rlt.getString("password");
			    a.accountType = rlt.getInt("accountType");
			    a.name = rlt.getString("name");
			    a.address = rlt.getString("address");
			    a.city = rlt.getString("city");
			    a.state = rlt.getString("state");
			    a.zip = rlt.getInt("zip");
                 if(a.accountType==1){accounts.add(a);}
		    }
	}
	catch (Exception exception) {
		System.out.println(exception);
	}
	
	return accounts;
    }
}
