package pagesObject;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import mapsObject.MapsObjectForm;

public class PagesObjectForm extends MapsObjectForm{

	public PagesObjectForm(WebDriver driver) {
		super(driver);
	}
		
	//METHOD FIRST TEST
	public void enterAPractice() throws Exception{
		click(linkPractice);
		standByTime(2000);
		scrollWeb(100, 1);
		standByTime(2000);
	}
	
	public void registerForm(String name, String lastName,String address, String phone, String email, String state, String city) throws InterruptedException{
		sendKey(name, inputName);
		sendKey(lastName, inputLastName);
		sendKey(email,inputEmail);
		click(radioGender);
		sendKey(phone,inputMobile);
		sendKey("Administracion de recursos",inputSubjects);
		sendKey(address,inputAddress);
		click(checkboxHobbies);
		click(dateCalendar);
		newDateCalendar(monthCalendar,nextMonth,newDate);
		uploadFile(btnFile);
		standByTime(2000);
		sendKey(state,selectState);
		sendKeyBoard(Keys.ARROW_DOWN,selectState);
		sendKeyBoard(Keys.ENTER,selectState);
		standByTime(2000);
		sendKey(city, selectCity);
		sendKeyBoard(Keys.ARROW_DOWN,selectCity);
		sendKeyBoard(Keys.ENTER,selectCity);
		standByTime(2000);
		click(btnSubmit);
	}
	
	public void screenShot() throws InterruptedException{
		try {			
			standByTime(2000);
			scrollWeb(100, 1);
			screenShot("screen_1.png");
			standByTime(2000);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
