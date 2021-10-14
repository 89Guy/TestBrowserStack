package Pages;

import Context.ThreadContextForScenarios;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class TestPage{
    WebDriver driver;

    public TestPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//script/following-sibling::div//div/div/button")
    private WebElement popupClose;

    @FindBy(how = How.XPATH, using = "//input[@name='q']")
    private WebElement searchBar;

    @FindBy(how = How.XPATH, using = "//button[@type='submit']")
    private WebElement submitButton;

    @FindBy(how = How.XPATH, using = "//section/label/input")
    private WebElement flipkartAssured;

    @FindBy(how = How.XPATH, using = "//div[text()='Price -- High to Low']")
    private WebElement highToLow;

    @FindBy(how = How.XPATH, using = "//div[contains(@style,'flex-grow')]/div[contains(@style,'color')]/following-sibling::div//div[@data-id]//img[@alt]")
    private List<WebElement> listOfMobiles;

    public void searchMobile(String value){
        driver.get("https://www.flipkart.com/");
        popupClose.click();
        searchBar.sendKeys(value);
        submitButton.click();
    }

    public void clickFlipKartAssured(){
      //  flipkartAssured.click();
    }

    public void mobileSort(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        highToLow.click();
    }

    public List<String>listOfMobilesOnFirstPage(){
        List<String>mobiles=new ArrayList<>();
        for(WebElement mobile:listOfMobiles){
            mobiles.add(mobile.getAttribute("alt"));
        }
        return mobiles;
    }
}
