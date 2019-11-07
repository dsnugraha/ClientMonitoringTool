package dsn.cmon.dao;

import java.util.List;

import dsn.cmon.model.DataStatic;

public interface DataStaticDAO {
	public void addDataStatic(List<DataStatic> datastatics);
    public int getCountRec();
}
