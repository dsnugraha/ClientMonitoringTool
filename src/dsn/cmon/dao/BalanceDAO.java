package dsn.cmon.dao;

import java.util.List;

import dsn.cmon.model.Balance;

public interface BalanceDAO {
    public void addBalance(Balance balance);
    public void updateBalance(Balance balance);
    public void upsertBalance(List<Balance> balances);
    public boolean checkAccountNumber(String acctno);
    public int getCountRec();
}
