package main;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class App {

	private static Logger logger = Logger.getLogger(App.class);

	public static void main(String[] args) {

		log4jInit();

		logger.debug("debug");
		logger.info("info");
		logger.warn("warn");
		logger.error("error");

	}

	private static void log4jInit() {

		LogManager.resetConfiguration();
		String connectdir = "E:\\workplaceMe\\repository\\galaxy\\src\\main\\resource\\log4j.properties";
		PropertyConfigurator.configure(connectdir);

		logger.debug(connectdir);
	}
}
