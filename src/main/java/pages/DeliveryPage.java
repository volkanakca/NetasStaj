package pages;

import config.ConfigReader;
import driver.PageGenarator;
import io.qameta.allure.Step;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;


public class DeliveryPage extends PageGenarator {
    private static final Logger LOG = LogManager.getLogger(HomePage.class);

    protected final String fullName = ConfigReader.readDataInfo().getProperty("fullName");
    protected final String GSM = ConfigReader.readDataInfo().getProperty("GSM");
    protected final String eposta = ConfigReader.readDataInfo().getProperty("eposta");
    protected final String idCard = ConfigReader.readDataInfo().getProperty("idCard");
    protected final String adressHome = ConfigReader.readDataInfo().getProperty("adressHome");
    protected final String crediCardNumber = ConfigReader.readDataInfo().getProperty("crediCardNumber");
    protected final String cvvCode = ConfigReader.readDataInfo().getProperty("cvvCode");

    @FindBy(xpath = "//input[@id='fullName']")
    private WebElement nameSurname;

    @FindBy(xpath = "//input[@id='gsm-1']")
    private WebElement gsm;

    @FindBy(xpath = "//input[@id='email']")
    private WebElement email;

    @FindBy(xpath = "//input[@id='tckimlik']")
    private WebElement identificationNumber;

    @FindBy(xpath = "//span[@id='select2-select-cities-container']")
    private WebElement city;

    @FindBy(xpath = "//li[@title='Ankara']")
    private WebElement selectCity;

    @FindBy(xpath = "//span[@id='select2-select-disct-2-container']")
    private WebElement disct;

    @FindBy(xpath = "//input[@role='searchbox']")
    private WebElement disctSearchBox;

    @FindBy(xpath = "//li[@title='Etimesgut']") // dinamik hale getirilecek
    private WebElement selectDisct;

    @FindBy(xpath = "//button[contains(text(),'Siparişe Devam Et')]")
    private WebElement contiuneOrder;

    @FindBy(xpath = "//div[@class='m-aside-box']")
    private WebElement infoBox;

    @FindBy(xpath = "//input[@id='customerDeliveryAddressName']")
    private WebElement customerDeliveryAddressName;

    @FindBy(xpath = "//input[@id='customerDeliveryGsm']")
    private WebElement customerDeliveryGsm;

    @FindBy(xpath = "//textarea[@id='textarea-02']") // dinamik hale getirilecek
    private WebElement adress;

    @FindBy(xpath = "//button[contains(text(),'Siparişe Devam Et')]") // dinamik hale getirilecek her sayfada var.
    private WebElement contiuneOrderInDelivery;

    @FindBy(xpath = "//input[@id='ccCardHolderName-1']")
    private WebElement ccNameSurname;

    @FindBy(xpath = "//input[@id='ccCardNumber-1']")
    private WebElement ccNumber;

    @FindBy(xpath = "//span[@id='select2-ccMonth-1-container']")
    private WebElement ccMonth;

    @FindBy(xpath = "//li[contains(@id,'select2-ccMonth-1-result') and @title='8']")
    private WebElement ccSelectMonth;

    @FindBy(xpath = "//span[@id='select2-ccYear-1-container']")
    private WebElement ccYear;

    @FindBy(xpath = "//li[contains(@id,\"select2-ccYear-1-result\") and text()='2025']")
    private WebElement ccSelectYear;

    @FindBy(xpath = "//input[@id='ccSecurityNumber-1']")
    private WebElement ccvNumber;

    // Sanal Kart Pathleri

    @FindBy(xpath = "//span[@class='js-credit-card-name']")
    private WebElement creditCardName;

    @FindBy(xpath = "//span[@class='js-credit-card-number']")
    private WebElement creditCardNumber;

    @FindBy(xpath = "//sub[@class='js-credit-card-month']")
    private WebElement creditCardMonth;

    @FindBy(xpath = "//sub[@class='js-credit-card-years']")
    private WebElement creditCardYear;

    @FindBy(xpath = "//span[@class='js-credit-card-cvv']")
    private WebElement creditCardCvv;

    public DeliveryPage() throws IOException {
    }

    @Step("Müsteri bilgileri alanlara giriliyor.")
    public void customerInfo() {

        WaitUntilTheElementAppears(nameSurname);
        sendKeysElement(nameSurname,fullName);
        WaitUntilTheElementAppears(gsm);
        sendKeysElement(gsm,GSM);
        WaitUntilTheElementAppears(email);
        sendKeysElement(email,eposta);
        WaitUntilTheElementAppears(identificationNumber);
        sendKeysElement(identificationNumber,idCard);
    }
    @Step("Teslimat bilgileri alanlara giriliyor.")
    public void deliveryInfo() {
        WaitUntilTheElementAppears(city);
        clickElement(city);
        findScrollElementCenter(selectCity);
        WaitUntilTheElementAppears(selectCity);
        clickElement(selectCity);
        wait(1);
        clickElement(disct);
        findScrollElementCenter(selectDisct);
        wait(1);
        WaitUntilTheElementAppears(selectDisct);
        clickElement(selectDisct);
        findScrollElementCenter(infoBox);
        WaitUntilTheElementAppears(customerDeliveryAddressName);
        sendKeysElement(customerDeliveryAddressName,fullName);
        WaitUntilTheElementAppears(customerDeliveryGsm);
        sendKeysElement(customerDeliveryGsm,GSM);
        WaitUntilTheElementAppears(adress);
        sendKeysElement(adress,adressHome);
        WaitUntilTheElementAppears(contiuneOrderInDelivery);
        clickElement(contiuneOrderInDelivery);
    }
    @Step("Kredi kart bilgileri alanlara giriliyor.")
    public void paymentInfo() {
        WaitUntilTheElementAppears(ccNameSurname);
        sendKeysElement(ccNameSurname,fullName);
        WaitUntilTheElementAppears(ccNumber);
        sendKeysElement(ccNumber,crediCardNumber);
        WaitUntilTheElementAppears(ccMonth);
        clickElement(ccMonth);
        findScrollElementCenter(ccSelectMonth);
        WaitUntilTheElementAppears(ccSelectMonth);;
        clickElement(ccSelectMonth);
        WaitUntilTheElementAppears(ccYear);
        clickElement(ccYear);
        WaitUntilTheElementAppears(ccSelectYear);
        clickElement(ccSelectYear);
        WaitUntilTheElementAppears(ccvNumber);
        sendKeysElement(ccvNumber,cvvCode);
        WaitUntilTheElementAppears(contiuneOrder);
        clickElement(contiuneOrder);
    }
    @Step("Kredi kart bilgileri ekraninda kart numarasinin son 4 hanesini kayit altina aliyor.")
    public String getCreditCardNumber() {
        try {
            String ccNumberPaymentPage = creditCardNumber.getText().substring(15,19);
            return ccNumberPaymentPage;
        } catch (Exception e) {
            throw new AssertionError("Kredi kartı numarası basılırken hata olustu", e);
        }
    }
}
