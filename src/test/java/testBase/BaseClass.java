package testBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager; //Log4j

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.Logger;//Log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;





public class BaseClass {
	//Loading all config properties file
	public Properties p;
	 public static WebDriver driver;
	 public Logger logger; //Log4j
	  //(One time instance)
		@BeforeClass(groups = {"sanity","Regression","Master","DataDriven"})
		@Parameters({"os","browser"})
		public void setup(String os,String br) throws IOException{
			
			FileReader file= new FileReader("./src//test//resources/config.properties");
			p= new Properties();
			p.load(file);
			
			logger=LogManager.getLogger(this.getClass()); //this will call dynamically for each classes
			
			if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
				
				//os
				DesiredCapabilities cap= new DesiredCapabilities();
				
				if(os.equals("Windows")) {
					cap.setPlatform(Platform.WIN11);
				}else if(os.equals("mac")){
					cap.setPlatform(Platform.MAC);
				}else {
					System.out.println("No matching OS");
					return;
				}
				
				//browser
				
				switch(br.toLowerCase()){
				case "chrome":
				   
				    driver = new ChromeDriver();
				    break;
				case "edge":
				    
				    driver = new EdgeDriver();
				    break;
				case "firefox":
				    
				    driver = new FirefoxDriver();
				    break;

				default:System.out.println("No matching browser");return;
				}
				
				
			}
			if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
				switch(br.toLowerCase()) {
				case "chrome":driver= new ChromeDriver();break;
				case "edge":driver= new EdgeDriver();break;
				case "firefox":driver= new FirefoxDriver();break;
				default: System.out.println("Invalid Browser name..");return;
				}
			}
			
			driver= new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			//driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");
			driver.get(p.getProperty("appURL")); //reading url from properties file
		}
		@AfterClass(groups = {"sanity","Regression","Master","DataDriven"})
		public void teardown() {
			driver.quit();
		}
		 
		//(Re-usable methods)
		//It's a java method (User Defined Method)
		public String randomString() {
			String generatedstring=RandomStringUtils.randomAlphabetic(5);
			return generatedstring;
		}
		
		public String randomNumber() {
			String generatednumber=RandomStringUtils.randomNumeric(10);
			return generatednumber;
		}
		
		public String randomAlphanumeric() {
			String generatedalphanumeric=RandomStringUtils.randomAlphanumeric(5);
			return (generatedalphanumeric+"@");
		}
		
		public String captureScreen(String tname) throws IOException{
			String timestamp= new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			
			TakesScreenshot takesScreenshot= (TakesScreenshot) driver;
			File sourcefile= takesScreenshot.getScreenshotAs(OutputType.FILE);
			
			String targetFilepath= System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timestamp +".png";
			File targetFile= new File(targetFilepath);
			
			sourcefile.renameTo(targetFile);
			return targetFilepath;
			
		}
}
