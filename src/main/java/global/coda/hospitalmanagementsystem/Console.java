package global.coda.hospitalmanagementsystem;

import global.coda.hospitalmanagementsystem.db.DatabaseDriver;

public class Console {
//Entry point for the application
	public static void main(String[] args) {
		DatabaseDriver driver = new DatabaseDriver();
		driver.beginApplication();
	}

}
