package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class TestSomething {

	@Test
	public void readFileNames() throws ClassNotFoundException, SQLException {

		String URL = "jdbc:mysql://127.0.0.1:3306/shadowsocks?useUnicode=true&amp;characterEncoding=utf-8";
		String USER = "root";
		String PASSWORD = "000orange";
		Class.forName("com.mysql.jdbc.Driver");
		String sql = "select * from user";
		Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		
		// account_plugin
		while (rs.next()) {
			String email = rs.getString("email");
			String port = rs.getString("port");
			String deadline = rs.getString("deadline");
			if (StringUtils.isAnyBlank(email,deadline))
				continue;
			
			String str = "{\"create\":1538075008044,\"flow\":100000000000,\"limit\":1}";
			String insertSql = "INSERT INTO account_plugin(type,orderId,userId,server,port,password,data,subscribe,status,autoRemove,autoRemoveDelay,multiServerFlow,active) VALUES(2,0,null,null,?,?,?,null,0,0,0,0,1)";
			PreparedStatement preparedStatement = conn.prepareStatement(insertSql);
			preparedStatement.setString(1, port);
			preparedStatement.setString(2, email);
			preparedStatement.setString(3, str);
			preparedStatement.execute();

			
		}

		ps.execute();
		ps.close();
		conn.close();

	}

}
