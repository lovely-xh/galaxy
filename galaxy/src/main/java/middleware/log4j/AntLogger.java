package middleware.log4j;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class AntLogger {
	private Logger logger = Logger.getLogger(AntLogger.class);
	
	private Level level;
	
	public AntLogger(Level level) {
		this.level = level;
	}
}
