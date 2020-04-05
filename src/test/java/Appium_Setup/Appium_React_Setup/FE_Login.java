package Appium_Setup.Appium_React_Setup;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import io.appium.java_client.TouchAction;

public class FE_Login {

	static AppiumDriver<MobileElement> driver;

	@BeforeMethod
	public void setup() {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "Lenovo PHAB");
		capabilities.setCapability(CapabilityType.PLATFORM, "Android");
		capabilities.setCapability("platformVersion", "6.0.1");
		capabilities.setCapability("noReset", false);
		capabilities.setCapability("autoGrantPermissions", true);

		capabilities.setCapability("appPackage", "com.fareyereact");
		capabilities.setCapability("appActivity", "com.fareyereact.MainActivity");

		// Use below code to lauch app using system apk
		
		// File file = new File("/home/ramit/Downloads","FarEyeReact_v0.79.67.apk");
		// capabilities.setCapability("app",file.getAbsolutePath());

		try {
			driver = new AppiumDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void fareye_react_app() throws InterruptedException {

		System.out.println("FarEye Android App is getting launched, please wait for a moment");

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Login']")));

		MobileElement loginButton = driver.findElement(By.xpath("//android.widget.TextView[@text='Login']"));
		loginButton.click();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		MobileElement scannerButton = driver.findElement(By.xpath("//android.widget.TextView[@text='   Scanner']"));

		TouchAction action = new TouchAction(driver);
		action.longPress(longPressOptions().withElement(element(scannerButton)).withDuration(Duration.ofMillis(10000)))
				.release().perform();

		MobileElement domainSelect = driver.findElement(By.xpath("//android.widget.TextView[@text='QA Test']"));
		domainSelect.click();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		MobileElement username = driver
				.findElement(By.xpath("//android.widget.EditText[@content-desc='username input_LoginV2']"));
		username.sendKeys("fe_raj_01_007");

		MobileElement password = driver
				.findElement(By.xpath("//android.widget.EditText[@content-desc='password input_Loginv2']"));
		password.sendKeys("Fareye!123");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		MobileElement loginNow = driver
				.findElement(By.xpath("//android.view.ViewGroup[@content-desc='login button_LoginV2']"));
		loginNow.click();
		
		System.out.println("FE getting logged in");

	}

	@AfterMethod
	public void teardown() {

		try {
			Thread.sleep(12000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}

}
