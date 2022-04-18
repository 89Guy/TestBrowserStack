package Modules;

import Pages.BrowserStackPage;
import Pages.TestPage;
import cucumber.api.java.gl.E;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class TestModule /*extends BrowserStackPage*/ {
    WebDriver driver;
    public static final String USERNAME = "atulupadhyay_F0s8GR";
    public static final String AUTOMATE_KEY = "k4tZXKSvEBsj1pL56V18";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";

    @BeforeTest
    @Parameters("browser")
    public void initDrivers(String browser) throws Exception {
        if (browser.equalsIgnoreCase("Edge") && System.getProperty("mode").equals("browserstack")) {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("os", "Windows");
            caps.setCapability("os_version", "10");
            caps.setCapability("browser", "Edge");
            caps.setCapability("browser_version", "latest");
            caps.setCapability("browserstack.local", "false");
            caps.setCapability("browserstack.selenium_version", "3.5.2");
            java.net.URL browserStackUrl = new URL(URL);
            EdgeOptions options=new EdgeOptions();
            options.merge(caps);
            WebDriverManager.edgedriver().setup();
            driver = new RemoteWebDriver(browserStackUrl,options);
        }
        else if (browser.equalsIgnoreCase("Edge") && System.getProperty("mode").equals("local")) {
            WebDriverManager.edgedriver().setup();
            driver=new EdgeDriver();
        }
        else if (browser.equalsIgnoreCase("Chrome")&& System.getProperty("mode").equals("browserstack")) {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("os", "Windows");
            caps.setCapability("os_version", "10");
            caps.setCapability("browser", "Chrome");
            caps.setCapability("browser_version", "latest");
            caps.setCapability("browserstack.local", "false");
            caps.setCapability("browserstack.selenium_version", "3.14.0");
            java.net.URL browserStackUrl = new URL(URL);
            ChromeOptions options = new ChromeOptions();
            options.merge(caps);
            WebDriverManager.chromedriver().setup();
            driver = new RemoteWebDriver(browserStackUrl,options);
        }
        else if (browser.equalsIgnoreCase("Chrome")&& System.getProperty("mode").equals("local")) {
            WebDriverManager.chromedriver().setup();
            driver=new ChromeDriver();
        }
        else if (browser.equalsIgnoreCase("firefox")&& System.getProperty("mode").equals("browserstack")) {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("os", "Windows");
            caps.setCapability("os_version", "10");
            caps.setCapability("browser", "Firefox");
            caps.setCapability("browser_version", "latest");
            caps.setCapability("browserstack.local", "false");
            caps.setCapability("browserstack.selenium_version", "3.10.0");
            java.net.URL browserStackUrl = new URL(URL);
            FirefoxOptions options=new FirefoxOptions();
            options.merge(caps);
            WebDriverManager.firefoxdriver().setup();
            driver = new RemoteWebDriver(browserStackUrl,options);
        }
        else if (browser.equalsIgnoreCase("firefox")&& System.getProperty("mode").equals("local")) {
            WebDriverManager.firefoxdriver().setup();
            driver=new FirefoxDriver();
        }

    }

    @Test
    public void filpkartMobileSearchListing() {
        TestPage testPage = new TestPage(driver);
        testPage.searchMobile("Samsung Galaxy S10");
        // testPage.clickFlipKartAssured();
        //testPage.brandSelect();
        testPage.mobileSort();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(testPage.listOfMobilesOnFirstPage());
        return;
    }

    @AfterTest
    public void afterTest() {
        System.out.println("Closing the browser ");
        driver.quit();
    }
}
