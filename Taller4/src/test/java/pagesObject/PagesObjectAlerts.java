package pagesObject;

import java.io.File;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import mapsObject.MapsObjectAlerts;
import utilitiesExcel.ReadExcelFile;

public class PagesObjectAlerts extends MapsObjectAlerts {
	public Properties properties;
	public String nomTest;
	public File routerFolder;

	public PagesObjectAlerts(WebDriver driver) {
		super(driver);
		nomTest = Thread.currentThread().getStackTrace()[1].getMethodName();
		routerFolder = createFolder(properties, nomTest);
	}

	// METHOD SEARCH URL
	public void urlAccess(String url) {
		driver.get(url);
	}

	// METHOD FIRST TEST
	public void enterAlert(Properties properties) throws Exception {
		nomTest = Thread.currentThread().getStackTrace()[1].getMethodName();
		routerFolder = createFolder(properties, nomTest);
		scrollWeb(100, 1);
		standByTime(2000);
		click(linkAlert, routerFolder);
		standByTime(2000);
		scrollWeb(100, 1);
	}

	public void protAlert(ReadExcelFile read, Properties properties, String path) throws InterruptedException, Exception {
		nomTest = Thread.currentThread().getStackTrace()[1].getMethodName();
		routerFolder = createFolder(properties, nomTest);
		try {
			scrollWeb(100, 1);
			standByTime(4000);
			click(btnPromtAlert, routerFolder);
			// sendKeyAlert("assesses",routerFolder);
			standByTime(2000);
			sendKeyAlert(read.getCellValue(path, "users", 1, 0), routerFolder);
			standByTime(2000);
			acceptAlert(routerFolder);
			standByTime(5000);

		} catch (Exception e) {

		}

	}

	public void secondAlert() throws InterruptedException, Exception {
		nomTest = Thread.currentThread().getStackTrace()[1].getMethodName();
		routerFolder = createFolder(properties, nomTest);
		try {
			click(btnAlert, routerFolder);
			standByTime(2000);
			acceptAlert(routerFolder);
			standByTime(2000);
		} catch (Exception e) {

		}
	}

	public void timerAlert() throws InterruptedException, Exception {
		nomTest = Thread.currentThread().getStackTrace()[1].getMethodName();
		routerFolder = createFolder(properties, nomTest);
		try {
			click(btnTimerAlert, routerFolder);
			standByTime(8000);
			acceptAlert(routerFolder);
			standByTime(4000);
		} catch (Exception e) {
		}
	}

	public void confirmAlert() throws InterruptedException, Exception {
		nomTest = Thread.currentThread().getStackTrace()[1].getMethodName();
		routerFolder = createFolder(properties, nomTest);
		try {
			click(btnConfirmAlert, routerFolder);
			standByTime(2000);
			acceptAlert(routerFolder);
			standByTime(6000);
		} catch (Exception e) {

		}
	}

	public void cancelAlert() throws InterruptedException, Exception {
		try {
			click(btnConfirmAlert, routerFolder);
			standByTime(2000);
			cancelAlert(routerFolder);
			standByTime(6000);
		} catch (Exception e) {

		}
	}

}
