package mapsObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.ClaseBase;

public class MapsObjectForm extends ClaseBase{

	public MapsObjectForm(WebDriver driver) {
		super(driver);
	}

	//MAPPING PAGE ELEMENTS
	protected By linkForms = By.xpath("//*[(text()='Forms')]");
	protected By linkPractice = By.xpath("//*[(text()='Practice Form')]");
	
	//MAPPING INPUT FORMS
	protected By inputName = By.id("firstName");
	protected By inputLastName = By.id("lastName");
	protected By inputEmail = By.id("userEmail");
	protected By radioGender = By.id("gender-radio-1");
	protected By inputMobile = By.id("userNumber");
	protected By inputSubjects = By.id("subjectsInput");
	protected By checkboxHobbies = By.id("hobbies-checkbox-1");
	protected By inputAddress = By.id("Current Address");
	protected By btnSubmit = By.id("submit");
}
