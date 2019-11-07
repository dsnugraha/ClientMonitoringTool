package dsn.cmon.test;

import java.math.BigDecimal;

public class TipeData {
	
	private BigDecimal openbal;

	public TipeData() {
		// TODO Auto-generated constructor stub
	}

	public BigDecimal getOpenbal() {
		return openbal;
	}

	public void setOpenbal(BigDecimal openbal) {
		this.openbal = openbal;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TipeData tipeData = new TipeData();
		String nil = "90000000000000000";
		BigDecimal bd = new BigDecimal(nil);
		tipeData.setOpenbal(bd);
		System.out.println("tipeData.getOpenbal() = " + tipeData.getOpenbal());

	}

}
