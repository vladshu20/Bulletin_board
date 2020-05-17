package by.bntu.fitr.poisit.shumchyk.Bulletin_board;


import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.logging.log4j.LogManager;

@SpringBootApplication
public class BulletinBoardApplication {

	private static Logger logger = LogManager.getLogger(BulletinBoardApplication.class.getName());

	public static void main(String[] args) {
		logger.info("Starting web-app");
		SpringApplication.run(BulletinBoardApplication.class, args);
	}

	public static void setLogger(Logger logger) {
		BulletinBoardApplication.logger = logger;
	}
}
