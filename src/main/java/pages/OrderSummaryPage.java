package pages;

import base.TestBase;
import io.qameta.allure.Step;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static driver.LocalDriverContext.getDriver;

public class OrderSummaryPage extends TestBase {

    private static final Logger LOG = LogManager.getLogger(OrderSummaryPage.class);

    @FindBy(xpath = "//button[normalize-space()='Ödeme Yap']")
    private WebElement pay;

    @FindBy( css = "label.a-checkbox span")
    private WebElement infoContract;
    @Step("Satis sözlesmesini kabul edip 3D Secure ekranına yönleniyor.")
    public void orderSummary () {
        WaitUntilTheElementAppears(infoContract);
        clickElement(infoContract);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("document.querySelector('label.a-checkbox span','::after').click();");
        findScrollElementTop(pay);
        wait(2);
        WaitUntilTheElementAppears(pay);
        clickElement(pay);
    }
}
