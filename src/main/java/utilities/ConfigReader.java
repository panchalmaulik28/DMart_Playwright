package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	static Properties prop;

	static {
		try {
			prop = new Properties();
			FileInputStream file = new FileInputStream("./src/test/resources/config/config.properties");
			prop.load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String properties(String value) {
		return prop.getProperty(value);
	}
}
