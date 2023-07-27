package pages;

import driver.PageGenarator;
import io.qameta.allure.Step;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasketPage extends PageGenarator {
    private static final Logger LOG = LogManager.getLogger(HomePage.class);

    @FindBy(xpath = "//*[name()='path' and contains(@d,'M284.286,2')]")
    public WebElement advertPopUpInBasket;

    @FindBy(xpath = "//span[contains(text(),'okudum, onaylıyorum.')]")
    public WebElement userAgree;

    @FindBy(xpath = "//button[contains(text(),'Siparişe Devam Et')]")
    public WebElement contiuneOrder;

    @FindBy(xpath = "//div[@id='modal-user-agreement']//a[@class='a-btn a-btn--full'][normalize-space()='Onayla']")
    public WebElement agree;
    @Step("Sözlesmeyi kabul edip siparise devam ediliyor.")
    public void userAgreement() {
        WaitUntilTheElementAppears(userAgree);
        userAgree.click();
        WaitUntilTheElementAppears(agree);
        agree.click();
        WaitUntilTheElementAppears(contiuneOrder);
        contiuneOrder.click();
    }


}
