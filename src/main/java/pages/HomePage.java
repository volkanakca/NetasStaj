package pages;

import driver.PageGenarator;
import io.qameta.allure.Step;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class HomePage extends PageGenarator {

    private static final Logger LOG = LogManager.getLogger(HomePage.class);

    @FindBy(xpath = "//div[@id=\"close-button-1454703513202\"]")
    public WebElement whatsappPopup;

    @FindBy(xpath = "//span[normalize-space()='Cep Telefonu-Aksesuar']")
    private WebElement phoneAndAccessories;

    @FindBy(xpath = "//div[@class='m-p-ts__tab-content__item active']")
    private WebElement bestFirstDiv;

    @Step("En çok satanlar alanina scrol ediliyor.")
    public int bestSellersCount() {
        findScrollElementTop(phoneAndAccessories);
        wait(2);

        // bestFirstDiv öğesinin altındaki a öğelerinin sayısını elde etmek için:
        List<WebElement> aElement = bestFirstDiv.findElements(By.tagName("a"));
        int bestSellersCount = aElement.size();
        return bestSellersCount;
    }
    @Step("En çok satanlar alanindaki urun sayisi kontrol ediliyor.")
    public void verifybestSellersCount(int bestSellersCount, int siteInCount) {
        try {
            Assert.assertEquals(bestSellersCount, 8, "Fiyatlar eslesmiyor.");
            return;
        } catch (Exception e) {
            Assert.fail("Karşılaştırma yapılırken hata verdi.");
        }
    }
    @Step("Telefon ve aksesuar yazisinin renk ve font degerleri aliniyor.")
    public String[] colorAndFontWeight() {
        String color = phoneAndAccessories.getCssValue("color");
        String fontWeight = phoneAndAccessories.getCssValue("font-weight");
        return new String[]{color, fontWeight};
    }
    @Step("Telefon ve aksesuar yazisinin renk ve font degerleri kontrol ediliyor.")
    public void verifyColorAndFontWeight(String[] actualValues, String expectedColor, String expectedFontWeight) {
        try {
            String actualColor = actualValues[0];
            String actualFontWeight = actualValues[1];

            Assert.assertEquals(actualColor, expectedColor, "Yazının rengi eşleşmiyor.");
            Assert.assertEquals(actualFontWeight, expectedFontWeight, "Yazının kalınlığı eşleşmiyor.");
        }
        catch (Exception e) {
            throw new AssertionError("Yazı özellikleri doğru değil.", e);
        }
    }
}


