package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import pagesObject.PagesObjectForm;
import pagesObject.PagesObjectHome;


public class RunTest {

	private WebDriver driver;
	PagesObjectHome pages;
	PagesObjectForm pagesForm;

	@Before
	public void setUp() {
		pages = new PagesObjectHome(driver);
		driver = pages.chromeDriverConnection();
		pagesForm = new PagesObjectForm(driver);
		pages.urlAccess("https://demoqa.com/");	
	}
	
	@Test
	public void test() throws Exception{
		pages.enterAForms();
		pagesForm.enterAPractice();
		pagesForm.registerForm();
	}
	
	@After
	public void close() {
		driver.quit();
	}
	
	public RunTest() {
		// TODO Auto-generated constructor stub
	}

}
