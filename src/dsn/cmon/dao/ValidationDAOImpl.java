package dsn.cmon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import dsn.cmon.util.DBUtil;

public class ValidationDAOImpl implements ValidationDAO {
	static Logger log = Logger.getLogger("APP");
	private Connection conn;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
    
    public ValidationDAOImpl() {
        conn = DBUtil.getConnection();
    }

	@Override
	public boolean checkUser(String username, String password) {
		// TODO Auto-generated method stub
		boolean result = false;
		String md5Password = DigestUtils.md5Hex(username.concat(password));
		
		try{
			String query = "select * from bman_users where username=? and password=?";
            
            preparedStatement = conn.prepareStatement( query );
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, md5Password);
            resultSet = preparedStatement.executeQuery();
			result = resultSet.next();
                        
		}catch(SQLException se){
			log.error("SQLState: " + se.getSQLState());
			log.error("ErrorCode: " + se.getErrorCode());
			log.error("Message: " + se.getMessage());
			se.printStackTrace();
			
		} finally{
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {	}
		}
		return result;
	}
	
	public static void main(String[] args){
		ValidationDAOImpl validation = new ValidationDAOImpl();
		boolean result=validation.checkUser("admin", "welcome1");
		System.out.println(result);
	}

}
