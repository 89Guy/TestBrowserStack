package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class TestPage {
    WebDriver driver;

    public TestPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//script/following-sibling::div//div/div/button")
    private WebElement popupClose;

    @FindBy(how = How.XPATH, using = "//input[@name='q']")
    private WebElement searchBar;

    @FindBy(how = How.XPATH, using = "//button[@type='submit']")
    private WebElement submitButton;

    @FindBy(how = How.XPATH, using = "//div[@id='container']//section[3]//input")
    private WebElement flipkartAssured;

    @FindBy(how = How.XPATH, using = "//div[@id='container']//section[6]//input")
    private WebElement brand;

    @FindBy(how = How.XPATH, using = "//div[text()='Price -- High to Low']")
    private WebElement highToLow;

    @FindBy(how = How.XPATH, using = "//div[contains(@style,'flex-grow')]/div[contains(@style,'color')]/following-sibling::div//div[@data-id]//img[@alt]")
    private List<WebElement> listOfMobilesProductName;

    @FindBy(how = How.XPATH, using = "//div[@data-id]//a")
    private List<WebElement> listOfMobilesDetailLink;

    @FindBy(how = How.XPATH, using = "//div[@class='_30jeq3 _1_WHN1']")
    private List<WebElement> price;

    public void searchMobile(String value) {
        driver.get("https://www.flipkart.com/");
        popupClose.click();
        searchBar.sendKeys(value);
        submitButton.click();
    }

    public void clickFlipKartAssured() {
        Actions actions=new Actions(driver);
        actions.moveToElement(flipkartAssured).perform();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", flipkartAssured);
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('readonly','readonly')",flipkartAssured);
        actions.moveToElement(flipkartAssured).doubleClick();
        //flipkartAssured.click();
    }

    public void brandSelect() {
        Actions actions=new Actions(driver);
        actions.moveToElement(brand).perform();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", brand);
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('readonly','readonly')",brand);
        actions.moveToElement(brand).doubleClick();
       // brand.click();
    }

    public void mobileSort() {
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        highToLow.click();
    }

    public List<String> listOfMobilesOnFirstPage() {
        int counter = 0;
        List<List<String>> listOfProducts = new ArrayList<>();
        List<String> mobiles = new ArrayList<>();
        for (int i = 0; i < listOfMobilesProductName.size(); i++) {
            counter += 1;
            mobiles.add("Product--" + String.valueOf(counter));
            mobiles.add(listOfMobilesProductName.get(i).getAttribute("alt"));
            mobiles.add("https://www.flipkart.com/" + listOfMobilesDetailLink.get(i).getAttribute("href"));
            mobiles.add(price.get(i).getText());
        }
        listOfProducts.add(mobiles);
        return mobiles;
    }
}
