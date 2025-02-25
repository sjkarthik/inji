package inji.pages;

import inji.constants.Target;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;


public class RetrieveIdPage extends BasePage {
	@AndroidFindBy(accessibility = "retrieveIdHeader")
	@iOSXCUITFindBy(accessibility = "retrieveIdHeader")
	private WebElement retrieveIdText;

	@AndroidFindBy(accessibility = "idInputModalIndividualId")
	@iOSXCUITFindBy(accessibility = "idInputModalIndividualId")
	private WebElement enterIdTextBox;

	@AndroidFindBy(accessibility = "generateVc")
	@iOSXCUITFindBy(accessibility = "generateVc")
	private WebElement generateCardButton;

	@iOSXCUITFindBy(className = "XCUIElementTypePickerWheel")
	private WebElement vidDropDownValueIos;

	@AndroidFindBy(xpath = "//*[contains(@text,'VID')]")
	private WebElement vidDropDownValueAndroid;

	@AndroidFindBy(className = "android.widget.Spinner")
	private WebElement spinnerButton;

	@AndroidFindBy(accessibility = "getItNow")
	@iOSXCUITFindBy(accessibility = "getItNow")
	private WebElement getItNowText;

	@AndroidFindBy(xpath = "//*[contains(@text,'UIN invalid')]")
	private WebElement invalidUin;

	@AndroidFindBy(xpath = "//*[contains(@text,'The input format is incorrect')]")
	@iOSXCUITFindBy(accessibility = "idInputModalIndividualId")
	private WebElement inputFormatErrorMessage;

	@AndroidFindBy(xpath = "//*[contains(@text,'AID is not ready yet')]")
	@iOSXCUITFindBy(accessibility = "AID is not ready yet")
	private WebElement aidIsNotReadyYetMessage;
	
	@AndroidFindBy(xpath = "//*[contains(@text,'Select ID type and enter the MOSIP provided UIN or VID you')]")
	@iOSXCUITFindBy(accessibility = "Select ID type and enter the MOSIP provided UIN or VID you wish to download. In the next step, you will be asked to enter OTP.")
	private WebElement downloadIdGuideMessage;
	
	@AndroidFindBy(accessibility = "IdInputToolTipInfo")
	private WebElement infoIcon;

	public RetrieveIdPage(AppiumDriver driver) {
		super(driver);
	}

	public boolean isRetrieveIdPageLoaded() {
		return this.isElementDisplayed(retrieveIdText);
	}
	
	public boolean isInfoIconDisplayed() {
		return this.isElementDisplayed(infoIcon);
	}
	public void clickInfoIcon() {
        clickOnElement(infoIcon);
    }
	
	public String getRetrieveIdPageHeader() {
		return this.getTextFromLocator(retrieveIdText);
	}

	public RetrieveIdPage setEnterIdTextBox(String uinOrVid) {
		clickOnElement(generateCardButton);
		sendKeysToTextBox(enterIdTextBox, uinOrVid);
		return this;
	}

	public OtpVerificationPage clickOnGenerateCardButton() {
		this.clickOnElement(generateCardButton);
		return new OtpVerificationPage(driver);
	}

	public GenerateUinOrVidPage clickOnGetItNowText() {
		this.clickOnElement(getItNowText);
		return new GenerateUinOrVidPage(driver);
	}
	
	public String verifyGetItTextDisplayed() {
		return this.getTextFromLocator(getItNowText);
	}
	
	public boolean verifyDownloadIdPageGuideMessage() {
		return this.isElementDisplayed(downloadIdGuideMessage);
	}

	public boolean isInvalidUinMassageLoaded() {
		return this.isElementDisplayed(invalidUin);
	}

	public boolean isAidIsNotReadyYetErrorDisplayed() {
		return this.isElementDisplayed(aidIsNotReadyYetMessage);
	}

	public RetrieveIdPage clickOnVid(Target os) {
		int maxRetries = 3; 

		switch (os) {
		case ANDROID:
			clickOnElement(spinnerButton);
			for (int i = 0; i < maxRetries; i++) {
				try {
					clickOnElement(vidDropDownValueAndroid);
					return this; 
				} catch (StaleElementReferenceException e) {
					if (i == maxRetries - 1) {
						throw e; 
					}
				}
			}
			break;
		case IOS:
			try {
				sendKeysToTextBox(vidDropDownValueIos, "VID");
			} catch (StaleElementReferenceException e) {

			}
			break;
		}
		return this;
	}

	public boolean isIncorrectInputFormatErrorMessageDisplayed() {
		return isElementDisplayed(inputFormatErrorMessage);
	}

}
