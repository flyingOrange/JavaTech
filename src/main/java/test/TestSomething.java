package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import org.junit.Test;

public class TestSomething {

	@Test
	public void readFileNames() throws ClassNotFoundException, SQLException{
		String URL = "jdbc:mysql://127.0.0.1:3306/shadowsocks?useUnicode=true&amp;characterEncoding=utf-8";
		String USER = "root";
		String PASSWORD = "000orange";
		Class.forName("com.mysql.jdbc.Driver");

		Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
		PreparedStatement ps = conn.prepareStatement("insert into user(passwd,port)values(?,?)");
		
		for(int i=9081;i<9131;i++){
			String id = UUID.randomUUID().toString();
			System.out.println("\""+i+"\":\""+ id +"\",");
			ps.setString(1, id);
			ps.setInt(2, i);
			ps.executeUpdate();
			
		}
		
	}

}
