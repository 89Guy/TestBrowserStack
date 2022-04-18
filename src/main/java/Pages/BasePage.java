package Pages;

import Context.ThreadContextForScenarios;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BasePage {
    WebDriver driver;

    public WebDriver initDriver() {
        if (System.getProperty("os.name").contains("Windows")) {
          //  System.setProperty("webdriver.chrome.driver", "C:\\Users\\work\\chromedriver.exe");
          //  WebDriverManager.chromedriver()/*.driverVersion("94.0.4606.81")*/.setup();
            System.setProperty("webdriver.chrome.driver","C:\\Freelance\\BrowserStack\\chromedriver.exe");
            this.driver = new ChromeDriver();
            this.driver.manage().window().maximize();
            this.driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            ThreadContextForScenarios.getInstance();
            ThreadContextForScenarios.setScenarioContext("driver",driver);
        }
        else{
            WebDriverManager.firefoxdriver().setup();
            DesiredCapabilities desiredCapabilities=new DesiredCapabilities();
            desiredCapabilities.setCapability("browserName","firefox");
            try {
                driver=new RemoteWebDriver(new URL("http://65.0.91.175:4444/wd/hub"),desiredCapabilities);
                this.driver.manage().window().maximize();
                this.driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
                ThreadContextForScenarios.getInstance();
                ThreadContextForScenarios.setScenarioContext("driver",driver);
            }
            catch(Exception e){
                System.out.println("Issue with instantiating driver on AWS Unix system");
            }
        }
        return driver;
    }
}
