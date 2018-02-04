package mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Execute {

	private SqlSession session;
	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void init() throws IOException {
		String resource = "mybatis/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		session = sqlSessionFactory.openSession();
	}

	@Test
	public void test() {
		//User user = (User) session.selectOne("myTest.selectById", 1);
		//System.out.println(user);
		
		//session.delete("myTest.");
		
		List<User> ul = new ArrayList<User>(); 
		User u1 = new User("11");
		User u2 = new User("12");
		User u3 = new User("13");
		User u4 = new User("14");
		ul.add(u1);
		ul.add(u2);
		ul.add(u3);
		ul.add(u4);
		session.insert("myTest.insertMany",ul);
		
	}
	
	
	
	@After
	public void after(){
		session.close();
	}

}
