package semilleros;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class RunTest {
	
	private WebDriver driver;
	PagesObject pages;

	@Before
	public void setUp() {
		pages = new PagesObject(driver);
		driver = pages.chromeDriverConnection();
		pages.urlAccess("https://google.com");
		
	}
	
	@Test
	public void test() throws Exception{
		pages.searchInitial();
	}
	
	@After
	public void close() {
		driver.quit();
	}
	
	public RunTest() {
		// TODO Auto-generated constructor stub
	}

}
