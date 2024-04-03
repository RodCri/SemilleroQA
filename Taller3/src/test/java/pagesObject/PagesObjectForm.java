package pagesObject;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import mapsObject.MapsObjectForm;
import utilitiesExcel.ReadExcelFile;

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
	
	public void registerForm(ReadExcelFile read, Properties properties, String path) throws InterruptedException{
		try {
			sendKey(read.getCellValue(path, "users", 1, 0), inputName);
			sendKey(read.getCellValue(path, "users", 1, 1), inputLastName);
			sendKey(read.getCellValue(path, "users", 1, 2), inputEmail);
			click(radioGender);
			sendKey(read.getCellValue(path, "users", 1, 3),inputMobile);
			sendKey(read.getCellValue(path, "users", 1, 4),inputSubjects);
			sendKeyBoard(Keys.ENTER,inputSubjects);
			sendKey(read.getCellValue(path, "users", 1, 5),inputSubjects);
			sendKeyBoard(Keys.ENTER,inputSubjects);
			sendKey(read.getCellValue(path, "users", 1, 6),inputAddress);
			click(checkboxHobbies);
			click(dateCalendar);
			newDateCalendar(monthCalendar,nextMonth,newDate);
			uploadFile(btnFile);
			standByTime(2000);
			sendKey(read.getCellValue(path, "users", 1, 7),selectState);
			sendKeyBoard(Keys.ARROW_DOWN,selectState);
			sendKeyBoard(Keys.ENTER,selectState);
			standByTime(2000);
			sendKey(read.getCellValue(path, "users", 1, 8), selectCity);
			sendKeyBoard(Keys.ARROW_DOWN,selectCity);
			sendKeyBoard(Keys.ENTER,selectCity);
			standByTime(2000);
			click(inputAddress);
			click(btnSubmit);
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
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

