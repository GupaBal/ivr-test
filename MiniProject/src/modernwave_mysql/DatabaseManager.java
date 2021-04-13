package modernwave_mysql;
import java.sql.*;
import java.util.Properties;
import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
public class DatabaseManager {
	
	private String fUser;
	private String fPassword;
	private BasicDataSource ds;
	private String fConnectionString;
	
	public DatabaseManager(Properties p, String sectionName) throws ClassNotFoundException {
		System.out.println("[DatabaseManager] Log.info "+ p.toString());
		fConnectionString = "jdbc:mysql://192.168.0.143:13308/"+p.getProperty(sectionName + ".Database");
		
		fUser = p.getProperty(sectionName + ".User");
		fPassword = p.getProperty(sectionName + ".Password");
	
		this.ds = new BasicDataSource();
		ds.setUsername(this.fUser);
		ds.setPassword(this.fPassword);
		ds.setUrl(fConnectionString);
		ds.setTestWhileIdle(true);
		ds.setValidationQuery("select 1 from dual;");
		ds.setTimeBetweenEvictionRunsMillis(1000 * 10);
		ds.setDriverClassName("com.mysql.jdbc.Driver");
	}
	
	@FunctionalInterface
	public static interface IGetQuery {
		String getQuery();
	}
	
	@FunctionalInterface
	public static interface IExecuteQuery {
		void executeQuery(PreparedStatement st) throws Exception;
	}
	
	@FunctionalInterface
	private static interface IImplPreparedExecute {
		void execute(PreparedStatement st) throws Exception;
	}
	
	public void execute(IGetQuery queryFunc, IImplPreparedExecute executeFun) throws SQLException, Exception {
		
		Connection con = null;
		String query = null;
		PreparedStatement stmt = null;
//		Statement state = null;
//		
//		con = DriverManager.getConnection(fConnectionString, fUser, fPassword);
//		state = con.createStatement();
//		
		
		con = ds.getConnection();
		query = queryFunc.getQuery();
		System.out.println("[DatabaseManager] Log.info "+ query);
		stmt = con.prepareStatement(query);
		executeFun.execute(stmt);
		stmt.close();
		stmt = null;
		con.close();
		con = null;
		
	}
	
	
	
	public void Execute(IGetQuery queryFunc, IExecuteQuery executeFunc) throws SQLException, Exception {
		execute(queryFunc, (PreparedStatement st) -> {
			executeFunc.executeQuery(st);
		});
	}

	

}
