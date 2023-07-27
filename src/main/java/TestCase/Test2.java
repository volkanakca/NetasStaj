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

public class Test2 extends TestBase {
    protected final String productURL = ConfigReader.readDataInfo().getProperty("productURL");
    public Test2() throws IOException {
    }

    @Test(description = "Urun dogrulama.")
    @Severity(NORMAL)
    @Description("Sepetteki urunun dogruluğunu kontrol etme")
    @Story("Herhangi bir ürünü sepete ekliyoruz ve giriş yapmadan devm ederek sepete gidiyoruz. Sepetteki ürün ile eklediğimiz ürünün doğruluğunu karşılaştırıyoruz.")
    public void test2() {
        BasicConfigurator.configure();
        homePage().navigate(productURL);
        productPage().addToBasket();
        productPage().continueToBasket();
        basketPage().popUpIfExist(basketPage().advertPopUpInBasket);
        productPage().productVerify();
    }
}
