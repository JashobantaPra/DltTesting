package DltTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SchoolApp {

	public String baseUrl = "https://dltschool70.dolittletech.co.in/site/login";
	public WebDriver driver;
	
	@BeforeMethod
	public  void setUp() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	}
	
	@Test
	public void loginTest() {
		driver.findElement(By.id("form-username")).sendKeys("superadmin@gmail.com");
		driver.findElement(By.id("form-password")).sendKeys("123456");
		driver.findElement(By.xpath("//button[normalize-space()='Sign In']")).submit();
		
		String pageTitle = driver.getTitle();
	/*	if (pageTitle.equals("Do Little Technologies")) {
			System.out.println("Login Successful!");
		}else {
			System.out.println("Login failed!");
		}	*/
		//System.out.println(pageTitle);
		Assert.assertEquals("Login : Your School Name", pageTitle);
	}
	
	public void logout() {
		driver.findElement(By.xpath("//img[@class='topuser-image']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
	}
	
	@AfterMethod
	public void tearDown() throws Throwable {
		Thread.sleep(2000);
		logout();
		driver.manage().window().minimize();
		driver.quit();
	}
	
}
