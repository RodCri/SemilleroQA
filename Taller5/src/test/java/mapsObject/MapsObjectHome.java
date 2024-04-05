package mapsObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.ClaseBase;

public class MapsObjectHome extends ClaseBase{

	// CONSTRUCTOR OF THE CLASS
	public MapsObjectHome(WebDriver driver) {
		super(driver);
	}
	
	//MAPPING PAGE ELEMENTS
	protected By linkAlerts = By.xpath("//h5[normalize-space()='Alerts, Frame & Windows']");
}
