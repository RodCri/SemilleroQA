package mapsObject;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.ClaseBase;

public class MapsObjectAlerts extends ClaseBase{

	public MapsObjectAlerts(WebDriver driver) {
		super(driver);
	}
	
	protected By linkAlert = By.xpath("//span[normalize-space()='Alerts']");
	
	protected By btnAlert = By.xpath("//button[@id='alertButton']");
	protected By btnTimerAlert = By.xpath("//button[@id='timerAlertButton']");
	protected By btnConfirmAlert = By.xpath("//button[@id='confirmButton']");
	protected By btnPromtAlert = By.xpath("//button[@id='promtButton']");

}
