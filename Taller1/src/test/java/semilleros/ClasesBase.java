package semilleros;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ClasesBase {

	protected WebDriver driver;
	
	//CLASS BUILDER
	public ClasesBase(WebDriver driver) {
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
	
	//METHOD DELETE
	public void delete(By locator) {
		try {
			driver.findElement(locator).clear();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	// METHOD SEND TEXT
	public void sendKey(String inputText, By locator) {
		try {
			driver.findElement(locator).sendKeys(inputText);
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
}
