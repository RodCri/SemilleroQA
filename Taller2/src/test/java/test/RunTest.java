package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import pagesObject.PagesObjectForm;
import pagesObject.PagesObjectHome;


public class RunTest {

	private WebDriver driver;
	PagesObjectHome pages;
	PagesObjectForm pagesForm;

	public String name;
	public String lastName;
	public String phone;
	public String email;
	public String address;
	public String state;
	public String city;
	
	@Before
	public void setUp() {
		// INSTANCIAR CLASE PAGEOBJECTHOME
		pages = new PagesObjectHome(driver);
		// CARGAR PROPIEDADES DEL NAVEGADOR
		driver = pages.chromeDriverConnection();
		// INSTANCIAR CLASE PAGEOBJECTFORM
		pagesForm = new PagesObjectForm(driver);
		// INSTANCIAR CLASE PROPIEDADES DE JAVA UTIL
		Properties properties = new Properties();
		//CREAR VARIABLE TIPO INPUTSTREAM
		InputStream entrada = null;
		//VALIDAR SI GENERA EEROR AL ENCONTRAR EL ARCHIVO
		try {
			entrada = new FileInputStream("./Properties/properties");
			properties.load(entrada);
							
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		// ACCEDER AL METODO DE ABRIR PAGINA
		pages.urlAccess(properties.getProperty("url"));
		
		// PROPERTIES
		name = properties.getProperty("name");
		lastName = properties.getProperty("lastName");
		phone = properties.getProperty("phone");
		address = properties.getProperty("address");
		email = properties.getProperty("email");
		state = properties.getProperty("state");
		city = properties.getProperty("city");
	}
	
	@Test
	public void test() throws Exception{
		pages.enterAForms();
		pagesForm.enterAPractice();
		pagesForm.registerForm(name,lastName,phone,address,email,state,city);
		pagesForm.screenShot();
	}
	
	@After
	public void close() {
		driver.quit();
	}
	
	@Rule
	public TestRule watcher = new TestWatcher() {
		@Override
		protected void failed(Throwable throwable, Description description) {
			File screenShotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(screenShotFile, new File("screen"+pages.getDate()+".png"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		@Override
		protected void finished(Description description) {
			
		}
	};

}
