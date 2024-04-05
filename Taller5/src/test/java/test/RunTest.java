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

import base.ClaseBase;
import pagesObject.PagesObjectAlerts;
import pagesObject.PagesObjectHome;
import utilities.GenerarReportePdf;
import utilities.MyScreenRecorder;
import utilities.ReadExcelFile;
import utilities.WriteExcelFile;

public class RunTest {
	
	private WebDriver driver;
	PagesObjectHome pages;
	PagesObjectAlerts pagesAlert;
	Properties properties;
	ReadExcelFile read;
	WriteExcelFile write;
	ClaseBase claseBase;
	public String nomTest;
	public File routerFolder;

	public String path;
	public String pathOuput;
	GenerarReportePdf generarReportePdf;

	@Before
	public void setUp() {
		// INSTANCIAR CLASE PAGEOBJECTHOME
		pages = new PagesObjectHome(driver);
		// CARGAR PROPIEDADES DEL NAVEGADOR
		driver = pages.chromeDriverConnection();
		// INSTANCIAR CLASE PAGEOBJECTFORM
		pagesAlert = new PagesObjectAlerts(driver);
		// INSTANCIAR CLASE PROPIEDADES DE JAVA UTIL
		properties = new Properties();
		// INSTANCIAR CLASE BASE
		claseBase = new ClaseBase(driver);
		//CREAR VARIABLE TIPO INPUTSTREAM
		InputStream entrada = null;
		// INSTANCIAR DE GENERAR REPORTE
		generarReportePdf = new GenerarReportePdf();
		
		// INSTANCIAR CLASE DE EXCEL
		read = new ReadExcelFile();
		write = new WriteExcelFile();
		
		//VALIDAR SI GENERA EEROR AL ENCONTRAR EL ARCHIVO
		try {
			entrada = new FileInputStream("./Properties/properties");
			// CARGAR LAS PROPIEDADES
			properties.load(entrada);
							
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		// ACCEDER AL METODO DE ABRIR PAGINA
		pages.urlAccess(properties.getProperty("url"));
		
		// PROPERTIES
		path = properties.getProperty("filePathExcel");
		pathOuput = properties.getProperty("outputFile");
		generarReportePdf.setRutaImagen(properties.getProperty("rutaImagenReporte"));
	}
	
	@Test
	public void test() throws Exception{
		// OBTENER EL NOMBRE DEL TEST
		nomTest = Thread.currentThread().getStackTrace()[1].getMethodName();
		// CREAR LA RUTA DEL FOLDER OUTPUT
		routerFolder = claseBase.createFolder(properties, nomTest, pathOuput);
		// INICIAR LA GRABACION
		MyScreenRecorder.startRecording(nomTest, routerFolder);
		// GENERAR REPORTE
		generarReportePdf.crearPlantilla(nomTest, routerFolder);
		// EJECUTAR LOS TEST DEL PAGES
		pages.enterAlerts(properties, pathOuput);
		pagesAlert.testAlerts(read,properties, path,pathOuput);
		// CERRAMOS LA PLANTILLA DEL REPORTE
		generarReportePdf.cerrarPlantilla();
		// PARAR LA GRABACION
		MyScreenRecorder.stopRecording();
	}
	
	@After
	public void close() {
		driver.quit();
	}
	
	@Rule
	public TestRule watcher = new TestWatcher() {
		@Override
		protected void failed(Throwable throwable, Description description) {
			// CREAR LA CAPTURA
			File screenShotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try {
				// COPIAR LA CAPTURA Y ASIGNAR EL NOMBRE DE LA CAPTURA
				FileUtils.copyFile(screenShotFile, new File("screen"+pages.getDate()+".png"));
			} catch (Exception e) {
				// CAPTURAR LA EXCEPTION
				e.printStackTrace();
			}
		}
		@Override
		protected void finished(Description description) {
			
		}
	};

}
