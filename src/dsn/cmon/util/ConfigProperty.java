package dsn.cmon.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConfigProperty {
	static Logger log = Logger.getLogger(ConfigProperty.class);
	
	public ConfigProperty() {
	}
	
	public String getConfigProperty(String property) {
		Properties prop = new Properties();
		InputStream input = null;
		String CMON_HOME = System.getenv("CMON_HOME");

		try {
			input = new FileInputStream(CMON_HOME + "/cmonconfig/config.properties");
			prop.load(input);
			property = prop.getProperty(property);

		} catch (IOException ie) {
			log.error("Message: " + ie.getMessage());
			ie.printStackTrace();
		}

		return property;
	}
	
}
