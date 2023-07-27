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

public class Test4 extends TestBase {
    protected final int topSellingProductCount = Integer.parseInt(ConfigReader.readDataInfo().getProperty("topSellingProductCount"));
    protected final String topSellingProductRGBA = (ConfigReader.readDataInfo().getProperty("topSellingProductRGBA"));
    protected final String topSellingProductFontWeight = (ConfigReader.readDataInfo().getProperty("topSellingProductFontWeight"));

    public Test4() throws IOException {
    }

    @Test(description = "Urun sayisi ve yazi özelligi kontrol etme.")
    @Severity(NORMAL)
    @Description("Ana Sayfada en çok satanlar alanındaki ürün sayısını ve en çok satanlar yazısının özelliklerini kontrol etme.")
    @Story("Ana Sayfada en çok satanlar alanına scroll edip ürün saayısını kontrol ediyoruz ve en çok satanlar yazısının rengini ve kalınlığının kontrolünü sağlıyoruz.")
    public void test4() {
        BasicConfigurator.configure();
        homePage().bestSellersCount();
        homePage().verifybestSellersCount(homePage().bestSellersCount(),topSellingProductCount);
        homePage().verifyColorAndFontWeight(homePage().colorAndFontWeight(),topSellingProductRGBA,topSellingProductFontWeight);
    }
}
