package pages;

import base.TestBase;
import io.qameta.allure.Step;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderSummaryPage extends TestBase {

    private static final Logger LOG = LogManager.getLogger(OrderSummaryPage.class);

    @FindBy(xpath = "//button[normalize-space()='Ödeme Yap']")
    private WebElement pay;

    @FindBy(xpath = "//span[contains(text(),'okudum ve onaylıyorum.')]")
    private WebElement infoContract;
    @Step("Satis sözlesmesini kabul edip 3D Secure ekranına yönleniyor.")
    public void orderSummary () {
        WaitUntilTheElementAppears(infoContract);
        clickElement(infoContract);
        findScrollElementTop(pay);
        wait(2);
        WaitUntilTheElementAppears(pay);
        clickElement(pay);
    }
}
