package modernwave_mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class QueryDatabase_Dars {

	private DatabaseManager dbManager;
	
	public QueryDatabase_Dars(DatabaseManager dbManager) {
		this.dbManager = dbManager;
	}
	
	public String getParameter(int index) throws SQLException, Exception {
		String query = String.format("SELECT * FROM dars_db.t_parameter where index_no = %d", index);
		ArrayList<String> numbers = new ArrayList<String>();
		dbManager.Execute(() -> query, (PreparedStatement st) -> {
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String data = rs.getString("data");
				numbers.add(data);
			}
		});

		if (numbers.isEmpty()) {
			System.out.println("[QueryDatabase_Dars] Log.info getParameter Query : " + query);
			throw new Exception();
			
		} else {
			System.out.println("[QueryDatabase_Dars] Log.info Query : " + query+ numbers.get(0));
		}
		return numbers.get(0);
	}
	
	public void setOrderInfo(Map<String, String> param) throws SQLException, Exception {
		System.out.println("[QueryDatabase_Dars] Log.info setOrderInfo");
		System.out.println(param.toString());
		String query = String.format("INSERT INTO order_data (index_no, EndTime, StartTime, CardNum, ResultMessage, OrderDate, CardPwd, AuthNum, ExpDate, Moid, CardQuota, CallCount, testFlag) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
				param.get("EndTime"),param.get("StartTime"),param.get("MID"),param.get("CardNum"),param.get("ResultMessage"),param.get("OrderDate"),param.get("CardPwd"),
				param.get("AuthNum"),param.get("ExpDate"),param.get("Moid"),param.get("CardQuota"),param.get("CallCount"),param.get("ResultCode"),param.get("testFlag"));
		dbManager.Execute(() -> query, (PreparedStatement st) -> {
			int upCnt = st.executeUpdate();
			if(upCnt > 0) {
				System.out.println("upCnt :"+ upCnt);
			}
		});
		
	}

}
