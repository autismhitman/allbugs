package allRestApi.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigLoader {
	
	public static InputStream getResource(String path) throws IOException {
	     
	    InputStream stream = ConfigLoader.class.getClassLoader().getResourceAsStream(path);
	    if (stream != null) return stream;

	    
	    Path filePath = Path.of(path);
	    if (Files.exists(filePath)) {
	        return Files.newInputStream(filePath);
	    }

	  
	    return null; 
	}
}