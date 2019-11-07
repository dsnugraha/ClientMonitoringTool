package dsn.cmon.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import dsn.cmon.util.GlobalVariable;

public class PathDir {

	public PathDir() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();

		Date currDate = new Date();
		//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

		String strdate = "2015-07-28T16:05:19+07:00";
		//String strdate1 = "2015-07-28 16:05:19+07:00";
		
		java.sql.Timestamp date = new java.sql.Timestamp(currDate.getTime());
		
			try {
				Date theDate;
				theDate = formater.parse(strdate);
				//Date fdate = df.format(theDate);
				System.out.println(theDate.getTime());
				Date dt = new java.sql.Date(theDate.getTime());
				System.out.println(dt);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	
		System.out.println(date);


		URL baseURL = PathDir.class.getResource("/");
		System.out.println("baseURL " + baseURL);
		String javaHome = System.getenv("JAVA_HOME");
		String CMON_HOME = System.getenv("CMON_HOME");
		String path = System.getenv("PATH");
		System.out.println("javaHome " + javaHome);
		System.out.println("CMON_HOME " + CMON_HOME);
		System.out.println("path " + path);

		String cashvalue = "1.655657009E7";
		BigDecimal cashvaldec = new BigDecimal(cashvalue);

		System.out.println(cashvaldec);

	    Properties prop = new Properties();
	    InputStream input = null;
	    
	    try{
	      
	      input = new FileInputStream("config/config.properties");
	      prop.load(input);

	      System.out.println(prop.getProperty("URL_DB"));
	      System.out.println(prop.getProperty("USERNAME_DB"));
	      System.out.println(prop.getProperty("PASSWORD_DB"));

	    }catch (IOException ie){
	      ie.printStackTrace();
	      }

		for (int i = 0; i < 3; i++) {
			try {
				GlobalVariable.STOP_STATEMENT_LOOP = true;
				System.out.println("From Another Class " + GlobalVariable.STOP_STATEMENT_LOOP);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(GlobalVariable.STOP_STATEMENT_LOOP);
		long end = System.currentTimeMillis();
		long ms = end - start;
		System.out.println("Elapsed time " + ms + " ms");

	}

}
