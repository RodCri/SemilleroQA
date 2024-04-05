package pagesObject;

import java.io.File;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import mapsObject.MapsObjectHome;

public class PagesObjectHome extends MapsObjectHome{
	public Properties properties;
	public String nomTest;
	public File routerFolder;
	public String evidencia;

	/**
	 * Constructor de la clase
	 * @param driver
	 */
	public PagesObjectHome(WebDriver driver) {
		super(driver);
	}
		
	
	/**
	 * @param url
	 */
	public void urlAccess(String url) {
		driver.get(url);
	}
		
	
	/**
	 * Metodo para ingresar a alerts
	 * @param properties
	 * @param path ruta carpeta output
	 * @throws Exception
	 */
	public void enterAlerts(Properties properties, String path) throws Exception{
		// OBTENER EL NOMBRE DEL METODO
		nomTest = Thread.currentThread().getStackTrace()[1].getMethodName();
		// CREAMOS EL FOLDER
		routerFolder = createFolder(properties, nomTest, path);
		evidencia = "Si";
		scrollWeb(100, 1);
		standByTime(2000);
		click(linkAlerts,routerFolder,evidencia);
		standByTime(2000);
	}
}
