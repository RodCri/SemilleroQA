package mapsObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.ClaseBase;

public class MapsObjectHome extends ClaseBase {

	public MapsObjectHome(WebDriver driver) {
		super(driver);
	}
	
	//MAPPING PAGE ELEMENTS
	protected By linkForms = By.xpath("//*[@class='card-body']//h5[contains(text(), 'Forms')]");
	protected By popupAlert = By.xpath("//*[@id='cbb']");

}
