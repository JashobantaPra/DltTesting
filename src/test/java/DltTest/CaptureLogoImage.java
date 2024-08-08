package DltTest;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

public class CaptureLogoImage {

	public String baseUrl = "https://dltschool70.dolittletech.co.in/site/login";
	public WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	}
	
	@Test
	public void captureLogo() throws IOException {
		WebElement logoImage = driver.findElement(By.xpath("(//div[@class='bgoffsetbg']//div)[1]"));
		
		Screenshot logoimageScreenshot = new AShot().takeScreenshot(driver, logoImage);
		ImageIO.write(logoimageScreenshot.getImage(),"png",new File("E://DltTest/Logo.jpg"));
		
		File f = new File("E://DltTest/Logo.jpg");
		if (f.exists()) {
			System.out.println("Image file captured");
		} else {
			System.out.println("Image file not exist");
		}
	}
	
	@AfterMethod
	public void tearDown() throws Throwable {
		Thread.sleep(2000);
		//logout();
		driver.manage().window().minimize();
		driver.quit();
	}
}
