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
import org.openqa.selenium.Alert;
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

	// METHOD CLICK
	public void click(By locator, File routeFolder) throws InterruptedException, Exception {
		try {
			snapShootScreen(routeFolder);
			driver.findElement(locator).click();
			standByTime(2000);
			// snapShootScreen(routeFolder);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// METHOD CLICK
	public void acceptAlert(File routeFolder) throws InterruptedException, Exception {
		try {
			snapShootScreen(routeFolder);
			Alert alert = driver.switchTo().alert();
			alert.accept();
			// snapShootScreen(routeFolder);
			standByTime(2000);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void cancelAlert(File routeFolder) throws InterruptedException, Exception {
		try {
			snapShootScreen(routeFolder);
			driver.switchTo().alert().dismiss();
			standByTime(2000);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void sendKeyAlert(String inputText, File routeFolder) throws Exception {
		try {
			snapShootScreen(routeFolder);
			driver.switchTo().alert().sendKeys(inputText);
			standByTime(2000);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// METHOD SELECT DROP OPTION
	public void selectDrop(By locator, int index) {
		try {
			Select objSelect = new Select((WebElement) locator);
			objSelect.selectByIndex(2);
			standByTime(2000);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// METHOD DELETE
	public void delete(By locator, File routeFolder) throws Exception {
		try {
			driver.findElement(locator).clear();
			snapShootScreen(routeFolder);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// METHOD SEND TEXT
	public static void sendKey(String inputText, By locator, File routeFolder) throws Exception {
		try {
			driver.findElement(locator).sendKeys(inputText);
			snapShootScreen(routeFolder);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// METHOD KEYBOARD
	public static void sendKeyBoard(Keys key, By locator) {
		try {
			driver.findElement(locator).sendKeys(key);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// METHOD SUBMIT
	public void submit(By locator, File routeFolder) throws Exception {
		try {
			driver.findElement(locator).submit();
			snapShootScreen(routeFolder);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// METHOD STAND BY TIME
	public void standByTime(long time) throws InterruptedException {
		Thread.sleep(time);
	}

	// METHOD GET DATE FOR SCREENSHOT
	public String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
		Date date = new Date();
		return dateFormat.format(date);
	}

	// METHOD SELECT NEW DATE IN CALENDAR
	public void newDateCalendar(By locator, By nextLocator, By newDateLocator, File routeFolder)
			throws InterruptedException {
		try {
			String monthDate = locator.findElement(driver).getText();
			System.out.println(monthDate);
			String month = monthDate.split(" ")[0].trim();
			String year = monthDate.split(" ")[1].trim();

			while ((month.equals("April") && year.equals("2024"))) {
				click(nextLocator, routeFolder);
				monthDate = locator.findElement(driver).getText();
				month = monthDate.split(" ")[0].trim();
				year = monthDate.split(" ")[1].trim();
			}
			click(newDateLocator, routeFolder);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// METHOD SCROLLWEB
	public static void scrollWeb(int y, int numMovimiento) throws Exception {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			for (int i = 0; i <= numMovimiento; i++) {
				js.executeScript("window.scrollBy(0," + y + ")");
				// standByTime(i);
				// ScreenShot(rutaCarpeta, generarEvidencia, steps);
			}
		} catch (Exception e) {
			throw new InterruptedException();
		}
	}

	// METHOD UPLOAD FILE
	public static void uploadFile(By locator, File routeFolder) {
		try {
			File file = new File("./data/usersData.xlsx");
			String path = file.getAbsolutePath();
			sendKey(path, locator, routeFolder);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void snapShootScreen(File folderPath) throws Exception {
		try {
			String hour = hourSystem();
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(folderPath + "\\" + hour + ".png"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void screenShot(String path) {
		try {
			File screenShotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenShotFile, new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public File createFolder(Properties properties, String nomTest) {
		String date = getDateNow();
		String nameFolder = nomTest + "-" + date;
		// String path = properties.getProperty("outputFile");
		File directory = new File("./output/" + nameFolder);
		directory.mkdir();
		return directory;
	}

	public static String getDateNow() {
		LocalDateTime dateSystem = LocalDateTime.now();
		DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
		String formatDate = date.format(dateSystem);
		return formatDate;
	}

	public static String hourSystem() {
		LocalTime hourSystem = LocalTime.now();
		DateTimeFormatter date = DateTimeFormatter.ofPattern("HHmmss");
		String hour = date.format(hourSystem);
		return hour;
	}

}
