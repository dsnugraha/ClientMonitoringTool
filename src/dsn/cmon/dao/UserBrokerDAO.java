package dsn.cmon.dao;

import dsn.cmon.model.UserBroker;

public interface UserBrokerDAO {
	public UserBroker getUserBroker();
	public int getCountRec();
	public void upsertUserBroker(UserBroker userBroker);
}
