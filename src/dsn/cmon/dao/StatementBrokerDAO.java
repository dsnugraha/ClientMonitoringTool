package dsn.cmon.dao;

import java.util.List;

import dsn.cmon.model.StatementBroker;

public interface StatementBrokerDAO {
    public void addStatementBroker(List<StatementBroker> statementbrokers);
    public int getCountRec();
}
