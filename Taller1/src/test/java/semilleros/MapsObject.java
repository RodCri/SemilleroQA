package semilleros;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MapsObject extends ClasesBase{

	//CLASS BUILDER
	public MapsObject(WebDriver driver) {
		super(driver);
	}

	//Mapping page elements
	By txtSearchGoogle = By.name("q");
	By btnSearch = By.name("btnK");
	By linkPage = By.xpath("//a[@href='https://www.mercadolibre.com.co/']");
	By txtSearchProduct = By.id("cb1-edit");
	By btnSerachProducts = By.className("nav-search-btn");
}
