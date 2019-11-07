package dsn.cmon.test;

import java.util.ArrayList;
import java.util.List;

import dsn.cmon.model.Balance;

public class BalanceObj {

	public BalanceObj() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Balance> listbalance = new ArrayList<Balance>();
		
		Balance balance = new Balance();
		balance.setAcctno("1");
		balance.setInvestorname("Deddy");
		listbalance.add(balance);
		
		Balance balance1 = new Balance();
		balance1.setAcctno("2");
		balance1.setInvestorname("Sadmaka");
		listbalance.add(balance1);
		
		for (Balance bal:listbalance){
			System.out.println(bal.getAcctno());
			System.out.println(bal.getInvestorname());
		}
	}

}
