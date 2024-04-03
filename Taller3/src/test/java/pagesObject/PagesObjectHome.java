package pagesObject;

import org.openqa.selenium.WebDriver;

import mapsObject.MapsObjectHome;

public class PagesObjectHome extends MapsObjectHome {

	public PagesObjectHome(WebDriver driver) {
		super(driver);
	}
		
	//METHOD SEARCH URL
	public void urlAccess(String url) {
		driver.get(url);
	}
		
	//METHOD FIRST TEST
	public void enterAForms() throws Exception{
		scrollWeb(100, 1);
		standByTime(2000);
		click(linkForms);
		standByTime(2000);
	}

}
