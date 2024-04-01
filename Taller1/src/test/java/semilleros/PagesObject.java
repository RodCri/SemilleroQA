package semilleros;

import org.openqa.selenium.WebDriver;

public class PagesObject extends MapsObject{
	
	
	//CLASS BUILDER
	public PagesObject(WebDriver driver) {
		super(driver);
	}
	
	//METHOD SEARCH URL
	public void urlAccess(String url) {
		driver.get(url);
	}
	
	//METHOD FIRST TEST
	public void searchInitial() throws InterruptedException{
		sendKey("mercado libre", txtSearchGoogle);
		submit(txtSearchGoogle);
		standByTime(2000);
		click(linkPage);
		sendKey("Portatil Lenovo", txtSearchProduct);
		standByTime(2000);
		click(btnSerachProducts);
		standByTime(2000);
		driver.close();
	}

}
