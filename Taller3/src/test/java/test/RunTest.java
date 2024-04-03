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
import utilitiesExcel.ReadExcelFile;
import utilitiesExcel.WriteExcelFile;

public class RunTest {

	private WebDriver driver;
	PagesObjectHome pages;
	PagesObjectForm pagesForm;
	Properties properties;
	ReadExcelFile read;
	WriteExcelFile write;

	public String path;
	
	@Before
	public void setUp() {
		// INSTANCIAR CLASE PAGEOBJECTHOME
		pages = new PagesObjectHome(driver);
		// CARGAR PROPIEDADES DEL NAVEGADOR
		driver = pages.chromeDriverConnection();
		// INSTANCIAR CLASE PAGEOBJECTFORM
		pagesForm = new PagesObjectForm(driver);
		// INSTANCIAR CLASE PROPIEDADES DE JAVA UTIL
		properties = new Properties();
		//CREAR VARIABLE TIPO INPUTSTREAM
		InputStream entrada = null;
		
		// INSTANCIAR CLASE DE EXCEL
		read = new ReadExcelFile();
		write = new WriteExcelFile();
		
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
		path = properties.getProperty("filePathExcel");
	}
	
	@Test
	public void test() throws Exception{
		pages.enterAForms();
		pagesForm.enterAPractice();
		pagesForm.registerForm(read,properties,path);
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
