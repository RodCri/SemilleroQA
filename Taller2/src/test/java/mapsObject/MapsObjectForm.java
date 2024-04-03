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
	protected By radioGender = By.xpath("//label[normalize-space()=\"Male\"]");
	protected By inputMobile = By.xpath("//input[@id='userNumber']");
	protected By inputSubjects = By.xpath("//input[@id='subjectsInput']");
	protected By checkboxHobbies = By.xpath("//label[normalize-space()='Sports']");
	protected By inputAddress = By.xpath("//textarea[@id='currentAddress']");
	
	protected By dateCalendar = By.xpath("//input[@id='dateOfBirthInput']");
	protected By monthCalendar = By.xpath("//div[@class='react-datepicker__current-month react-datepicker__current-month--hasYearDropdown react-datepicker__current-month--hasMonthDropdown']");
	protected By nextMonth = By.xpath("//button[normalize-space()='Next Month']");
	protected By newDate = By.xpath("//div[@aria-label='Choose Friday, May 24th, 2024']");
	
	protected By btnFile = By.xpath("//input[@id='uploadPicture']");
	
	protected By selectState = By.xpath("//input[@id='react-select-3-input']");
	protected By newState = By.xpath("//div[contains(@id,'react-select')]");
	protected By selectCity = By.xpath("//input[@id='react-select-4-input']");
	protected By newCity = By.xpath("//div[contains(@id,'react-select')]");
	
	
	protected By btnSubmit = By.xpath("//button[@id='submit']");
}
