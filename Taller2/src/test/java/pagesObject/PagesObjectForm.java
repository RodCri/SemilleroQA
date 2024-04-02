package pagesObject;

import org.openqa.selenium.WebDriver;

import mapsObject.MapsObjectForm;

public class PagesObjectForm extends MapsObjectForm{

	public PagesObjectForm(WebDriver driver) {
		super(driver);
	}
		
	//METHOD FIRST TEST
	public void enterAPractice() throws Exception{
		//click(linkForms);
		//standByTime(2000);
		click(linkPractice);
		standByTime(2000);
		scrollWeb(100, 1);
		standByTime(2000);
	}
	
	public void registerForm() throws InterruptedException{
		sendKey("Cristian Camilo", inputName);
		sendKey("Rodriguez Contreras", inputLastName);
		sendKey("cris@gmail.com",inputEmail);
		click(radioGender);
		
	}

}
