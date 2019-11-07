package dsn.cmon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import dsn.cmon.model.User;
import dsn.cmon.util.DBUtil;

public class UserDAOImpl implements UserDAO {
	static Logger log = Logger.getLogger("APP");
	private Connection conn;
	private PreparedStatement preparedStatement;

	public UserDAOImpl() {
		// TODO Auto-generated constructor stub
		conn = DBUtil.getConnection();
	}

	@Override
	public boolean changePassword(User user) {
		// TODO Auto-generated method stub
		boolean result = false;
		String md5Password = DigestUtils.md5Hex(user.getPassword());

		try {
			String query = "update bman_users set password=? where username=?";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, md5Password);
			preparedStatement.setString(2, user.getUsername());
			preparedStatement.executeUpdate();
			result=true;
			
		} catch (SQLException se) {
			log.error("SQLState: " + se.getSQLState());
			log.error("ErrorCode: " + se.getErrorCode());
			log.error("Message: " + se.getMessage());
			se.printStackTrace();
			result=false;
			
		} finally{
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {	}
		}
		return result;
	}
}
