package base;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import utilities.GenerarReportePdf;

/**
 * @author Cristian Camilo Rodriguez
 *
 */
public class ClaseBase {
	protected static WebDriver driver;
	public Properties properties;

	// CLASS BUILDER
	public ClaseBase(WebDriver driver) {
		super();
		properties = new Properties();
	}

	// METHOD OF THE BROWSER
	public WebDriver chromeDriverConnection() {

		// SET OPTIONS BROWSER
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);

		// SET BROWSER PROPERTIES
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();

		// MAXIMIZE BROWSER
		driver.manage().window().maximize();
		return driver;
	}

	
	/**
	 * Metodo click
	 * @param locator elemento de la pagina
	 * @param routeFolder ruta carpeta output
	 * @param evidencia si necesitamos generar evidencia
	 * @throws InterruptedException
	 * @throws Exception
	 */
	public void click(By locator, File routeFolder, String evidencia) throws InterruptedException, Exception {
		try {
			// CREAR LA CAPTURA CON LA EVIDENCIA RESPECTIVA
			snapShootScreen(routeFolder, locator, evidencia);
			// ESPERAR 2 SEGUNDOS
			standByTime(2000);
			// IDENTIFICAR EL ELEMENTO Y DAMOS EL CLICK
			driver.findElement(locator).click();
			// ESPERAR 2 SEGUNDOS
			standByTime(2000);
		} catch (Exception e) {
			// CAPTURAR LA EXCEPTION
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Metodo select drop option
	 * @param locator elemento de la pagina
	 * @param index indice del elemento
	 */
	public void selectDrop(By locator, int index) {
		try {
			// INSTANCIAR UN ELEMENTO SELECT
			Select objSelect = new Select((WebElement) locator);
			// SELECIONAR EL INDICE DEL SELECT
			objSelect.selectByIndex(2);
			// EFECTUAR UNA ESPERA DE 2 SEGUNDOS
			standByTime(2000);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Metodo delete
	 * @param locator elemento de la pagina
	 * @param routeFolder ruta carpeta output
	 * @param evidencia si necesitamos generar evidencia
	 * @throws Exception
	 */
	public void delete(By locator, File routeFolder, String evidencia) throws Exception {
		try {
			// IDENTIFICAR EL ELEMENTO PARA PODER LIMPIAR EL CAMPO
			driver.findElement(locator).clear();
			// CREAR LA CAPTURA CON LA EVIDENCIA RESPECTIVA
			snapShootScreen(routeFolder, locator, evidencia);
		} catch (Exception e) {
			// CAPTURAR LA EXCEPTION
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Metodo send text
	 * @param inputText Texto que enviamos
	 * @param locator elemento de la pagina
	 * @param routeFolder ruta carpeta output
	 * @param evidencia si necesitamos generar evidencia
	 * @throws Exception
	 */
	public static void sendKey(String inputText, By locator, File routeFolder, String evidencia) throws Exception {
		try {
			// IDENTIFICAR EL ELEMENTO DE LA PAGINA Y ENVIAMOS EL TEXTO
			driver.findElement(locator).sendKeys(inputText);
			// CREAR LA CAPTURA CON LA EVIDENCIA RESPECTIVA
			snapShootScreen(routeFolder, locator, evidencia);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Metodo sendKey Board
	 * @param key Tecla
	 * @param locator elemento de la pagina
	 */
	public static void sendKeyBoard(Keys key, By locator) {
		try {
			// IDENTIFICAR EL ELEMENTO Y ENVIAMOS LOS KEY
			driver.findElement(locator).sendKeys(key);
		} catch (Exception e) {
			// CAPTURAR LA EXCEPTION
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Metodo submit
	 * @param locator elemento de la pagina
	 * @param routeFolder ruta carpeta output
	 * @param evidencia si necesitamos generar evidencia
	 * @throws Exception
	 */
	public void submit(By locator, File routeFolder, String evidencia) throws Exception {
		try {
			// IDENTIFICAR EL ELMENTO DE LA PAGINA PARA DAR SUBMIT
			driver.findElement(locator).submit();
			// CREAR LA CAPTURA CON SU RESPECTIVA EVIDENCIA
			snapShootScreen(routeFolder, locator, evidencia);
		} catch (Exception e) {
			// CAPTURAR LA EXCEPTION
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Metodo de espera
	 * @param time tiempo de espera
	 * @throws InterruptedException
	 */
	public void standByTime(long time) throws InterruptedException {
		// DEFINIR EL TIEMPO DE ESPERA
		Thread.sleep(time);
	}

	/**
	 * Metodo para traer la fecha actual
	 * @return fecha actual del sistema
	 */
	public String getDate() {
		// OBTENER LA FECHA CON SU FORMATO
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
		// INSTANCIAR UNA NUEVA FECHA
		Date date = new Date();
		// RETORNAR LA FECHA FORMATEADA
		return dateFormat.format(date);
	}

	/**
	 * Metodo para interactual con el calendar
	 * @param locator Elemento de la pagina
	 * @param nextLocator elemento para seleccionar el mes
	 * @param newDateLocator nueva fecha
	 * @param routeFolder ruta carpeta output
	 * @param evidencia si necesitamos generar evidencia
	 * @throws InterruptedException
	 */
	public void newDateCalendar(By locator, By nextLocator, By newDateLocator, File routeFolder, String evidencia)
			throws InterruptedException {
		try {
			// OBTENEMR EL TEXTO DEL CALENDARIO
			String monthDate = locator.findElement(driver).getText();
			// SEPARAR EL MES Y AÑO DEL TEXTO
			String month = monthDate.split(" ")[0].trim();
			String year = monthDate.split(" ")[1].trim();

			while ((month.equals("April") && year.equals("2024"))) {
				// DAR CLICK AL MES QUE DESEAMOS
				click(nextLocator, routeFolder, evidencia);
				// OBTENER EL TEXTO DEL CALENDARIO DESPUES DEL CAMBIO
				monthDate = locator.findElement(driver).getText();
				// SEPARAR EL MES Y AÑO DEL TEXTO
				month = monthDate.split(" ")[0].trim();
				year = monthDate.split(" ")[1].trim();
			}
			// SELECCIONAR LA NUEVA FECHA PARA EL CALENDARIO
			click(newDateLocator, routeFolder, evidencia);
		} catch (Exception e) {
			// CAPTURAR LA EXCEPTION
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Metodo para generar un scroll en la web
	 * @param y espacio en el eje y
	 * @param numMovimiento cantidad de movimientos
	 * @throws Exception
	 */
	public static void scrollWeb(int y, int numMovimiento) throws Exception {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			for (int i = 0; i <= numMovimiento; i++) {
				js.executeScript("window.scrollBy(0," + y + ")");
				// standByTime(i);
				// ScreenShot(rutaCarpeta, generarEvidencia, steps);
			}
		} catch (Exception e) {
			// CAPTURAR LA EXCEPTION
			throw new InterruptedException();
		}
	}

	/**
	 * Metodo para cargar archivos
	 * @param locator elemento de la pagina
	 * @param routeFolder ruta carpeta output
	 * @param evidencia si necitamos generar la evidencia
	 */
	public static void uploadFile(By locator, File routeFolder, String evidencia) {
		try {
			// CREAR EL ARCHIVO
			File file = new File("./data/usersData.xlsx");
			// OBTENER SU RUTA ABSOLUTA
			String path = file.getAbsolutePath();
			// ENVIAR EL ARCHIVO MEDIANTE EL SEND KEY
			sendKey(path, locator, routeFolder, evidencia);
		} catch (Exception e) {
			// CAPTURAR LA EXCEPTION
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Metodo para crear el screenShoot
	 * @param folderPath ruta carpeta output
	 * @param locator elemento de la pagina
	 * @param generarEvidencia si necesitamos la evidencia
	 * @throws Exception
	 */
	public static void snapShootScreen(File folderPath, By locator, String generarEvidencia) throws Exception {
		if(generarEvidencia.equals("Si")) {
			// OBTENER LA HORA DEL SISTEMA
			String hour = hourSystem();
			// CREAR EL ARCHIVO CON LA CAPTURA
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			// COPIAR LA CAPTURA
			FileUtils.copyFile(scrFile, new File(folderPath+"\\"+hour+".png"));
			// ASIGNAR LA RUTA
			String rutaImagen = new File(folderPath+"\\"+hour+".png").toString();
			// INSTANCIAR DE GENERAR REPPORTE
			GenerarReportePdf informePdf = new GenerarReportePdf();
			// CREAR EL CUERPO DEL REPORTE CON LA IMAGEN CAPTURADA
			informePdf.crearBody(locator, rutaImagen);
			// ELIMINAR EL ARCHIVO DE LA CAPTURA
			eliminarArchivo(rutaImagen);
			
		}
	}

	/**
	 * Metodo para hacer la copia de la captura
	 * @param path ruta de la carpeta output
	 */
	public static void screenShot(String path) {
		try {
			// CREAR EL ARCHIVO DE LA CAPTURA
			File screenShotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			// COPIAR EL ARCHIVO
			FileUtils.copyFile(screenShotFile, new File(path));
		} catch (IOException e) {
			// CAPTURAR EVENTUALMENTE LA EXCEPTION
			e.printStackTrace();
		}
	}

	/**
	 * Metodo para la creacion de la carpeta
	 * @param properties
	 * @param nomTest nombre del test
	 * @param path ruta de la carpeta output
	 * @return
	 */
	public File createFolder(Properties properties, String nomTest, String path) {
		// OBTENER LA FECHA ACTUAL
		String date = getDateNow();
		// CREAR EL NOMBRE PARA EL FOLDER CON LA FECHA
		String nameFolder = nomTest + "-" + date;
		// CREAR EL DIRECTORIO CON EL NOMBRE DEL FOLDER
		File directory = new File(path + nameFolder);
		// CREAR LA CARPETA
		directory.mkdir();
		// RETORNAR LA CARPETA CREADA
		return directory;
	}

	/**
	 * @return la fecha y hora actual del sistema
	 */
	public static String getDateNow() {
		//OBTENER LA FECHA DEL SISTEMA
		LocalDateTime dateSystem = LocalDateTime.now();
		//FORMATEAR LA FECHA
		DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
		//GUARDAR LA FECHA FORMATEADA
		String formatDate = date.format(dateSystem);
		// RETORNAR LA FECHA
		return formatDate;
	}

	/**
	 * @return la hora del sistema actual
	 */
	public static String hourSystem() {
		//OBTENER LA HORA DEL SISTEMA
		LocalTime hourSystem = LocalTime.now();
		// FORMATEAR LA HORA
		DateTimeFormatter date = DateTimeFormatter.ofPattern("HHmmss");
		// GUARDAR LA HORA FORMATEADA
		String hour = date.format(hourSystem);
		// RETORNAR LA FECHA
		return hour;
	}
	
	/**
	 * @return la fecha y hora del siste
	 */
	public static String fechaHora2() {
		// OBTENER LA FECHA DEL SISTEMA
		LocalDateTime fechaSistema = LocalDateTime.now();
		// FORMATEAR LA FECHA
		DateTimeFormatter fecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		// GUARDAR LA FECHA FORMATEADA
		String formatFecha = fecha.format(fechaSistema);
		// RETORNAR LA FECHA
		return formatFecha;
	}
	
	/**
	 * Metodo para eliminar el archivo de la imagen
	 * @param rutaImagen 
	 */
	public static void eliminarArchivo(String rutaImagen) {
		// CREAR EL FICHERO CON LA RUTA DE LA IMAGEN
		File fichero = new File(rutaImagen);
		// ELIMINAR EL FICHERO CREADO
		fichero.delete();
	}

}
