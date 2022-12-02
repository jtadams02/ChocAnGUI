package jt.chocolic;

import java.sql.*;

public class Database {
	protected Connection connection;
	public Database() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://sql.freedb.tech/freedb_ChocAn",
					"freedb_Team1", "!*MshZZ$3V6$*q@");
			this.connection = con;
		}
		catch (Exception exception) {
			System.out.println(exception);
		}
	}
	public void CloseConnection() {
		try {
			this.connection.close();
		}
		catch (Exception exception) {
			System.out.println(exception);
		}
		return;
	}
	public ResultSet GetData(String table) {
		ResultSet rlt = null;
		try {
		Statement stm = connection.createStatement();
		rlt = stm.executeQuery("select * from " + table);
		}
		catch (Exception exception) {
			System.out.println(exception);
		}
		
		return rlt;
	}
}
