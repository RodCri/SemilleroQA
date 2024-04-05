package pagesObject;

import java.io.File;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import mapsObject.MapsObjectAlerts;
import utilities.ReadExcelFile;

public class PagesObjectAlerts extends MapsObjectAlerts{
	public Properties properties;
	public String nomTest;
	public File routerFolder;
	public String evidencia;

	/**
	 * Constructor de la clase
	 * @param driver
	 */
	public PagesObjectAlerts(WebDriver driver) {
		super(driver);
	}
		
	/**
	 * @param url
	 */
	public void urlAccess(String url) {
		driver.get(url);
	}
		
	/** Metodo para interactuar con las alert
	 * @param read
	 * @param properties
	 * @param path ruta del excel
	 * @param pathOuput ruta de la carpeta output
	 * @throws Exception
	 */
	public void testAlerts(ReadExcelFile read, Properties properties, String path, String pathOuput) throws Exception{
		// OBTENER EL NOMBRE DEL TEST
		nomTest = Thread.currentThread().getStackTrace()[1].getMethodName();
		// CREAR LA CARPETA
		routerFolder = createFolder(properties, nomTest, pathOuput);
		evidencia = "Si";
		
		try {
			scrollWeb(100, 1);
			standByTime(2000);
			click(linkAlert,routerFolder,evidencia);
			standByTime(2000);
			
			scrollWeb(200, 1);
			click(btnAlert, routerFolder,evidencia);
			standByTime(4000);
			driver.switchTo().alert().accept();
			standByTime(4000);
			
			click(btnTimerAlert, routerFolder,evidencia);
			standByTime(8000);
			driver.switchTo().alert().accept();
			standByTime(4000);
			
			click(btnConfirmAlert, routerFolder,evidencia);
			standByTime(4000);
			driver.switchTo().alert().accept();
			standByTime(4000);
			
			click(btnConfirmAlert, routerFolder,evidencia);
			standByTime(4000);
			driver.switchTo().alert().dismiss();
			standByTime(4000);
			
			String nameInput = read.getCellValue(path, "users", 1, 0);
			click(btnPromtAlert, routerFolder,evidencia);
			standByTime(2000);
			driver.switchTo().alert().sendKeys(nameInput);
			standByTime(4000);
			driver.switchTo().alert().accept();
			standByTime(4000);
		} catch (Exception e) {
			
		}
	}
	
}
