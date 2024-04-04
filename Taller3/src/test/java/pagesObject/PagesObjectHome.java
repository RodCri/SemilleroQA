package pagesObject;

import java.io.File;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import mapsObject.MapsObjectHome;

public class PagesObjectHome extends MapsObjectHome {
	public Properties properties;
	public String nomTest;
	public File routeFolder;

	public PagesObjectHome(WebDriver driver) {
		super(driver);
	}

	// METHOD SEARCH URL
	public void urlAccess(String url) {
		driver.get(url);
	}

	// METHOD FIRST TEST
	public void enterAForms(Properties properties) throws Exception {
		nomTest = Thread.currentThread().getStackTrace()[1].getMethodName();
		routeFolder = createFolder(properties, nomTest);

		scrollWeb(100, 1);
		standByTime(2000);
		click(linkForms, routeFolder);
		standByTime(2000);
	}

}
