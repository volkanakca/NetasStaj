package base;


import config.ConfigReader;
import config.Settings;
import driver.CurrentPageContext;
import driver.DriverFactory;
import driver.LocalDriverContext;
import driver.PageGenarator;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pages.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;

public class TestBase extends PageGenarator {
    public TestBase() {
    }

    @Step("Test oncesi yapilandirmalar gercekelsiyior.")
    @BeforeSuite(alwaysRun = true)
    public void Initialize() throws IOException {
        ConfigReader.readBrowserConfig();
    }

    @Step("Tarayıcı Ayarları Yapılıyor.")
    @BeforeMethod(alwaysRun = true)
    public void beforeTest() throws MalformedURLException {
        try {
            wait(6);
            LocalDriverContext.setDriver(DriverFactory.InitializeBrowser());
            LocalDriverContext.getDriver().get(Settings.BaseURL);
            LocalDriverContext.getDriver().manage().window().maximize();
            LocalDriverContext.getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
            homePage().popUpIfExist(homePage().whatsappPopup);
        }catch (Exception e){
            Assert.fail("Error occurred while initilaze browser");
        }
    }

    @Step("Tarayıcı sonlandırılıyor.")
    @AfterMethod(alwaysRun = true)
    public void afterTest() {
        if(LocalDriverContext.getDriver() != null){
            LocalDriverContext.getDriver().quit();
        }
    }

    public HomePage homePage(){
        PageGenarator base = new PageGenarator();
        CurrentPageContext.setCurrentPage(base.GetInstance(HomePage.class));
        return CurrentPageContext.getCurrentPage().As(HomePage.class);}

    public ProductPage productPage(){
        PageGenarator base = new PageGenarator();
        CurrentPageContext.setCurrentPage(base.GetInstance(ProductPage.class));
        return CurrentPageContext.getCurrentPage().As(ProductPage.class);}

    public BasketPage basketPage(){
        PageGenarator base = new PageGenarator();
        CurrentPageContext.setCurrentPage(base.GetInstance(BasketPage.class));
        return CurrentPageContext.getCurrentPage().As(BasketPage.class);}

    public DeliveryPage deliveryPage(){
        PageGenarator base = new PageGenarator();
        CurrentPageContext.setCurrentPage(base.GetInstance(DeliveryPage.class));
        return CurrentPageContext.getCurrentPage().As(DeliveryPage.class);}

    public OrderSummaryPage orderSummaryPage(){
        PageGenarator base = new PageGenarator();
        CurrentPageContext.setCurrentPage(base.GetInstance(OrderSummaryPage.class));
        return CurrentPageContext.getCurrentPage().As(OrderSummaryPage.class);}

    public PaymentPage paymentPage(){
        PageGenarator base = new PageGenarator();
        CurrentPageContext.setCurrentPage(base.GetInstance(PaymentPage.class));
        return CurrentPageContext.getCurrentPage().As(PaymentPage.class);}
}
