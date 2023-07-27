package TestCase;

import base.TestBase;
import config.ConfigReader;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import org.apache.log4j.BasicConfigurator;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.qameta.allure.SeverityLevel.NORMAL;

public class Test3 extends TestBase {
    protected final String productURL = ConfigReader.readDataInfo().getProperty("productURL");
    public Test3() throws IOException {
    }

    @Test(description = "Urun fiyat ve Kart numarasi dogrulama.")
    @Severity(NORMAL)
    @Description("3D Secure ekranındaki ürünün fiyatı ile sepetteki fiyatının ve girilen kredi kart numarası ile 3D Secure ekranındaki kredi kartının son 4 hanesini karşılaştırma.")
    @Story("Ürünü sepete ekle, alıcı, adres ve kart bilgilerini gir.  3D Secure ekranındaki kart numara bilgisi ile girdiğin kart bilgilerinin son 4 hanesini karşılaştır ve ödeme ekranındaki fiyat ile sepetteki fiyatı karşılaştır.")
    public void test3() {
        BasicConfigurator.configure();
        homePage().navigate(productURL);
        String productPagePrice = productPage().getProductPrice();
        productPage().addToBasket();
        productPage().continueToBasket();
        basketPage().popUpIfExist(basketPage().advertPopUpInBasket);
        basketPage().userAgreement();
        deliveryPage().customerInfo();
        deliveryPage().deliveryInfo();
        deliveryPage().paymentInfo();
        String deliveryPageNumber = deliveryPage().getCreditCardNumber();
        orderSummaryPage().orderSummary();
        String paymentPagePrice = paymentPage().getProductPrice();
        String paymentPageCcNumber = paymentPage().getCreditCardNumber();
        paymentPage().getCreditCardNumber();
        paymentPage().verifyPrice(productPagePrice, paymentPagePrice);
        paymentPage().verifyNumber(deliveryPageNumber,paymentPageCcNumber);
    }
}
