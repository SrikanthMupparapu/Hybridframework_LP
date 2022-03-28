package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyFileUtil {
	
	public static String getValueForKey(String key) throws Throwable
	{
	Properties config = new Properties();
	config.load(new FileInputStream("C:\\Users\\Srikanth\\git\\Hybridframework_LP\\HybridFramework_LP\\PropertyFiles\\Environment.properties"));
	return config.getProperty(key);
	}

}
