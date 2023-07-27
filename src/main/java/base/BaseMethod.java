package base;

import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static driver.LocalDriverContext.getDriver;

public class BaseMethod {
    final Logger LOG = LogManager.getLogger(BaseMethod.class);

    @Step("Elementin gorunurlugu kontrol edilir. {WebElement}")
    public boolean checkElementVisible(WebElement webElement) {
        try {
            return webElement.isDisplayed();
        }
        catch (Exception e) {
            return false;
        }
    }
    @Step("Elemente tiklama islemi gerceklestirir. {WebElement}")
    public void clickElement(WebElement webElement) {
        if (checkElementVisible(webElement)) {
            webElement.click();
        } else {
            // Element görünür değilse hata mesajı yazdırabilir veya istediğiniz işlemi yapabilirsiniz
            LOG.info("Element görünür değil.");
        }
    }
    @Step("Elemente String deger gonderme islemini gerceklestirir. {WebElement},{String}")
    public void sendKeysElement(WebElement webElement, String text) {
        if (checkElementVisible(webElement)) {
            webElement.sendKeys(text);
        } else {
            LOG.info("Element görünür değil.");
        }
    }
    @Step("Bekleme islemini gerceklestirir. {time}")
    @SneakyThrows
    public void wait(int time) {
        TimeUnit.SECONDS.sleep(time);
    }

    @Step("Verilen URL'e gidilir. {url}")
    public void navigate(String url) {
        wait(1);
        getDriver().get(url);
    }

    @Step("Elementi ortalayacak sekilde scroll eder. {WebElement}")
    public void findScrollElementCenter(WebElement webElement) {
        try {
            LOG.debug(webElement + " scroll ediliyor..");
            String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                    + "var elementTop = arguments[0].getBoundingClientRect().top;"
                    + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
            ((JavascriptExecutor) getDriver()).executeScript(scrollElementIntoMiddle, webElement);
            LOG.debug(webElement + " scroll ediliyor..");
        }catch (Exception e){
            LOG.error(webElement+"scroll edilirken problem oluştu..." );
            Assert.fail(webElement + " scroll edilirken problem oluştu...");
        }
    }
    @Step("Element en uste gelecek sekilde scroll eder. {WebElement}")
    public void findScrollElementTop(WebElement webElement) {
        try {
            LOG.debug(webElement + " sayfanın üstüne kaydırılıyor..");
            String scrollElementToTop = "window.scrollTo(0, arguments[0].getBoundingClientRect().top);";
            ((JavascriptExecutor) getDriver()).executeScript(scrollElementToTop, webElement);
            LOG.debug(webElement + " sayfanın üstüne kaydırıldı.");
        } catch (Exception e) {
            LOG.error(webElement + " sayfanın üstüne kaydırılırken bir sorun oluştu...");
            Assert.fail(webElement + " sayfanın üstüne kaydırılırken bir sorun oluştu...");
        }
    }
    @Step("Gorunur popUp varsa kapatma islemini gerceklestirir. {WebElement}")
    public void popUpIfExist(WebElement webElement) {
        wait(2);
        if (checkElementVisible(webElement)) {
            webElement.click();
            LOG.info(" PopUp Kapatıldı");
        }
        else {
            LOG.info("PopUp çıkmamıştır");
        }
    }
    @Step("Element gorunur ve tiklanabilir olana dek bekleme islemini gerceklestirir. {WebElement}")
    public void WaitUntilTheElementAppears(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20L));
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }
}






