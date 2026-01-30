package allRestApi.utils;

import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
	

	private static final String DEFAULT_PROPERTIES = "config.properties";
	private static Properties properties;
	
	
	public static void initialize(){
		
		properties = loadProperties();		
		
        for(String key: properties.stringPropertyNames()){
            if(System.getProperties().containsKey(key)){
                properties.setProperty(key, System.getProperty(key));
            }
        }        

	}

	
	private static Properties loadProperties() {
		
		Properties properties = new Properties();
		
		try(InputStream stream = ConfigLoader.getResource(DEFAULT_PROPERTIES)){
			if (stream == null) {
                throw new RuntimeException("Property file not found: " + DEFAULT_PROPERTIES);
            }
			 properties.load(stream);
		}catch (Exception e) {
            throw new RuntimeException("Failed to load: " + DEFAULT_PROPERTIES, e);
        }
		
		return properties;
	}
	
	public static String get(String key) {
		 
        return properties.getProperty(key);
		 
	}

}
