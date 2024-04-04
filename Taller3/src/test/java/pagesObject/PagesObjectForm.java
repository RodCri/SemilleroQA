package pagesObject;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import mapsObject.MapsObjectForm;
import utilitiesExcel.ReadExcelFile;

public class PagesObjectForm extends MapsObjectForm {
	public String nomTest;
	public File routerFolder;
	public Properties properties;

	public PagesObjectForm(WebDriver driver) {
		super(driver);
	}

	// METHOD FIRST TEST
	public void enterAPractice(Properties properties) throws Exception {
		nomTest = Thread.currentThread().getStackTrace()[1].getMethodName();
		routerFolder = createFolder(properties, nomTest);

		click(linkPractice, routerFolder);
		standByTime(2000);
		scrollWeb(400, 1);
		standByTime(2000);
	}

	public void registerForm(ReadExcelFile read, Properties properties, String path)
			throws InterruptedException, Exception {
		nomTest = Thread.currentThread().getStackTrace()[1].getMethodName();
		routerFolder = createFolder(properties, nomTest);

		try {
			sendKey(read.getCellValue(path, "users", 1, 0), inputName, routerFolder);
			sendKey(read.getCellValue(path, "users", 1, 1), inputLastName, routerFolder);
			sendKey(read.getCellValue(path, "users", 1, 2), inputEmail, routerFolder);
			click(radioGender, routerFolder);
			sendKey(read.getCellValue(path, "users", 1, 3), inputMobile, routerFolder);
			sendKey(read.getCellValue(path, "users", 1, 4), inputSubjects, routerFolder);
			sendKeyBoard(Keys.ENTER, inputSubjects);
			sendKey(read.getCellValue(path, "users", 1, 5), inputSubjects, routerFolder);
			sendKeyBoard(Keys.ENTER, inputSubjects);
			sendKey(read.getCellValue(path, "users", 1, 6), inputAddress, routerFolder);
			click(checkboxHobbies, routerFolder);
			click(dateCalendar, routerFolder);
			newDateCalendar(monthCalendar, nextMonth, newDate, routerFolder);
			uploadFile(btnFile, routerFolder);
			standByTime(2000);
			sendKey(read.getCellValue(path, "users", 1, 7), selectState, routerFolder);
			sendKeyBoard(Keys.ARROW_DOWN, selectState);
			sendKeyBoard(Keys.ENTER, selectState);
			standByTime(2000);
			sendKey(read.getCellValue(path, "users", 1, 8), selectCity, routerFolder);
			sendKeyBoard(Keys.ARROW_DOWN, selectCity);
			sendKeyBoard(Keys.ENTER, selectCity);
			standByTime(2000);
			click(inputAddress, routerFolder);
			click(btnSubmit, routerFolder);

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	public void screenShot() throws InterruptedException {
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
