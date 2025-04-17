package SauceDemoPackage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.WebDriverManagerException;

public class ReportGeneration {
	WebDriver driver ;


	ExtentReports extent = new ExtentReports();
	ExtentSparkReporter spark = new ExtentSparkReporter("ExtendReport.html");// the spark report access the location of the etxendreport

	@BeforeTest
	public void setup() 
	{
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.navigate().to("https://www.google.co.in/");
		extent.attachReporter(spark);
		spark.config().setTheme(Theme.DARK);
		extent.setSystemInfo("PC name:", "Anish");
		extent.setSystemInfo("OS", "Ubuntu 20.04.5 LTS");

	}

	@Test
	public void f1() 
	{
		// ExtentTest test =extent.createTest("Verify login");// working


		ExtentTest test =extent.createTest("Verify login").assignDevice("chrome").assignCategory("Sanity Test");
		test.log(Status.PASS, "logged in");
		test.pass("User loggedin successfully");
	}

	@Test
	public void f2() 
	{
		ExtentTest test=extent.createTest("Verify homepage");
		test.info("Logged in alert is displaying");
		test.pass("Successfully launched homescreen");
		test.warning("Update password for better security");
	}

	@Test
	public void f3() 
	{
		ExtentTest test = extent.createTest("Verify Dashboard");
		test.skip("Dashboard details are not verified");
	}

	@Test
	public void f4() 
	{
		ExtentTest test = extent.createTest("verify firearm count");
		test.log(Status.FAIL, "Firearm count is displaying incorrectly");
	}

	@Test
	public void OpenGoogle() 
	{


		ExtentTest test = extent.createTest("OpenGoogle");
		test.pass("Google got opend");

	}

	@AfterTest
	public void teardown()
	{
		extent.flush();// it will write every report from different test cases to the spark
	}

}