package pages;

import base.TestBase;
import io.qameta.allure.Step;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import static driver.LocalDriverContext.getDriver;

public class PaymentPage extends TestBase {
    private static final Logger LOG = LogManager.getLogger(PaymentPage.class);

    protected final String secureFrameId = "threeDSIframe";

    @FindBy(xpath = "//div[@class='col-xs-6 col-sm-4 amount']/span")
    private WebElement productPrice;

    @FindBy(xpath = "//span[@class='lastDigits']")
    private WebElement creditCardLastNumbers;

    @Step("3D Secure ekraninda urun fiyatinini kayit altina aliyor.")
    public String getProductPrice() {
        try {
            wait(1);
            getDriver().switchTo().frame(secureFrameId);
            wait(1);
            WaitUntilTheElementAppears(productPrice);
            String priceInPaymentPage = productPrice.getText().substring(0, 5);
            return priceInPaymentPage;
        } catch (Exception e) {
            throw new AssertionError("Ödeme Fiyatı Basılırken Hata Oluştu", e);
        }
    }
    @Step("3D Secure ekraninda kart numarasinin son 4 hanesini kayit altina aliyor.")
    public String getCreditCardNumber() {
        try {
            String ccNumberPaymentPage = creditCardLastNumbers.getText();
            return ccNumberPaymentPage;
        } catch (Exception e) {
            throw new AssertionError("Kredi kartı numarası basılırken hata olustu", e);
        }
    }
    @Step("Urun ekraninda ve odeme ekranindaki fiyati karsilastiriyor.")
    public void verifyPrice(String productPrice,String paymentPrice) {
        try {
            Assert.assertEquals(productPrice, paymentPrice, "Fiyatlar eslesmiyor.");
            return;
        }
        catch (Exception e) {
            Assert.fail("Karşılaştırma yapılırken hata verdi.");
        }
    }
    @Step("Kart bilgileri ekraninda ve odeme ekranindaki kart numarasinin son 4 hanesini karsilastiriyor.")
    public void verifyNumber(String deliveryNumber,String paymentNumber) {
        try {
            Assert.assertEquals(deliveryNumber, paymentNumber, "Kart Numaraları eslesmiyor.");
            return;
        }
        catch (Exception e) {
            Assert.fail("Karşılaştırma yapılırken hata verdi.");
        }
    }
}
