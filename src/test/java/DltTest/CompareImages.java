package DltTest;

import java.awt.image.BufferedImage;
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
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class CompareImages {

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
	public void compareImages() throws IOException {
		BufferedImage ExpectedImage = ImageIO.read(new File("E://DltTest/Logo.jpg"));
		
		WebElement logoImage = driver.findElement(By.xpath("(//div[@class='bgoffsetbg']//div)[1]"));
		Screenshot logoImageScreenshot = new AShot().takeScreenshot(driver, logoImage);
		BufferedImage actualImage = logoImageScreenshot.getImage();
		
		ImageDiffer imgdiff = new ImageDiffer();
		ImageDiff diff = imgdiff.makeDiff(ExpectedImage, actualImage);
		
		if (diff.hasDiff()==true) {
			System.out.println("Image are same");
		}else {
			System.out.println("Image are same");
		}
	}
	
	@AfterMethod
	public void tearDown() throws Throwable {
		Thread.sleep(2000);
		driver.manage().window().minimize();
		driver.quit();
	}
}
