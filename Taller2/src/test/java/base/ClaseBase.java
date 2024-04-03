package base;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

public class ClaseBase {
protected static WebDriver driver;
	
	//CLASS BUILDER
	public ClaseBase(WebDriver driver) {
		super();
	}
	
	//METHOD OF THE BROWSER
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

	//METHOD CLICK
	public void click(By locator) throws InterruptedException{
		try {
			driver.findElement(locator).click();
			standByTime(2000);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	//METHOD SELECT DROP OPTION
	public void selectDrop(By locator, int index) {
		try {
			Select objSelect = new Select((WebElement) locator);
			objSelect.selectByIndex(2);
			standByTime(2000);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	//METHOD DELETE
	public void delete(By locator) {
		try {
			driver.findElement(locator).clear();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	// METHOD SEND TEXT
	public static void sendKey(String inputText, By locator) {
		try {
			driver.findElement(locator).sendKeys(inputText);
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
		
	//METHOD SUBMIT
	public void submit(By locator) {
		try {
			driver.findElement(locator).submit();			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	//METHOD STAND BY TIME
	public void standByTime(long time) throws InterruptedException{
		Thread.sleep(time);
	}
	
	// METHOD GET DATE FOR SCREENSHOT
	public String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	//METHOD SELECT NEW DATE IN CALENDAR
	public void newDateCalendar(By locator, By nextLocator, By newDateLocator) throws InterruptedException {
		try {			
			String monthDate = locator.findElement(driver).getText();
			System.out.println(monthDate);
			String month = monthDate.split(" ")[0].trim();
			String year = monthDate.split(" ")[1].trim();
			
			while((month.equals("April") && year.equals("2024"))) {
				click(nextLocator);
				monthDate = locator.findElement(driver).getText();
				month = monthDate.split(" ")[0].trim();
				year = monthDate.split(" ")[1].trim();
			}
			click(newDateLocator);
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
				//standByTime(i);
				//ScreenShot(rutaCarpeta, generarEvidencia, steps);
			}
		} catch (Exception e) {
			throw new InterruptedException();
		}
	}
	
	//METHOD UPLOAD FILE
	public static void uploadFile(By locator) {
		try {			
			File file = new File("C:\\Users\\Camilo\\Downloads\\987614.jpg");
			String path = file.getAbsolutePath();
			sendKey(path,locator);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public static void screenShot(String path) {
		File screenShotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenShotFile, new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
