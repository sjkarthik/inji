package androidTestCases;

import BaseTest.AndroidBaseTest;
import inji.constants.Target;
import inji.pages.*;
import inji.utils.TestDataReader;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

import static org.testng.Assert.assertTrue;

public class ReceiveCardTest extends AndroidBaseTest {
	
	 @Test
	    public void verifyRecivedCardAndQrCode() {
		 ChooseLanguagePage chooseLanguagePage = new ChooseLanguagePage(driver);

	        assertTrue(chooseLanguagePage.isChooseLanguagePageLoaded(), "Verify if choose language page is displayed");
	        WelcomePage welcomePage = chooseLanguagePage.clickOnSavePreference();

	        assertTrue(welcomePage.isWelcomePageLoaded(), "Verify if welcome page is loaded");
	        AppUnlockMethodPage appUnlockMethodPage = welcomePage.clickOnSkipButton();

	        assertTrue(appUnlockMethodPage.isAppUnlockMethodPageLoaded(), "Verify if app unlocked page is displayed");
	        SetPasscode setPasscode = appUnlockMethodPage.clickOnUsePasscode();

	        assertTrue(setPasscode.isSetPassCodePageLoaded(), "Verify if set passcode page is displayed");
	        ConfirmPasscode confirmPasscode = setPasscode.enterPasscode(TestDataReader.readData("passcode"), Target.ANDROID);

	        assertTrue(confirmPasscode.isConfirmPassCodePageLoaded(), "Verify if confirm passcode page is displayed");
	        HomePage homePage = confirmPasscode.enterPasscodeInConfirmPasscodePage(TestDataReader.readData("passcode"), Target.ANDROID);

	        assertTrue(homePage.isHomePageLoaded(), "Verify if home page is displayed");
	        SettingsPage settingsPage = homePage.clickOnSettingIcon();
	        
	        ReceiveCardPage receiveCardPage = settingsPage.clickOnReceiveCard();
	        
	        receiveCardPage.clickOnAllowButton();
	        assertTrue(receiveCardPage.isReceiveCardHeaderDisplayed(), "Verify if QR code  header is displayed");
	        assertTrue(receiveCardPage.isWaitingForConnectionDisplayed(), "Verify if waiting for connection displayed");
	 }
	 
	 @Test
	    public void verifyRecivedCardAndQrCodeInFilipinoLanguage() {
	        ChooseLanguagePage chooseLanguagePage = new ChooseLanguagePage(driver);

	        assertTrue(chooseLanguagePage.isChooseLanguagePageLoaded(), "Verify if choose language page is displayed");
	        WelcomePage welcomePage = chooseLanguagePage.clickOnSavePreference();

	        assertTrue(welcomePage.isWelcomePageLoaded(), "Verify if welcome page is loaded");
	        AppUnlockMethodPage appUnlockMethodPage = welcomePage.clickOnSkipButton();

	        assertTrue(appUnlockMethodPage.isAppUnlockMethodPageLoaded(), "Verify if app unlocked page is displayed");
	        SetPasscode setPasscode = appUnlockMethodPage.clickOnUsePasscode();

	        assertTrue(setPasscode.isSetPassCodePageLoaded(), "Verify if set passcode page is displayed");
	        ConfirmPasscode confirmPasscode = setPasscode.enterPasscode(TestDataReader.readData("passcode"), Target.ANDROID);

	        assertTrue(confirmPasscode.isConfirmPassCodePageLoaded(), "Verify if confirm passcode page is displayed");
	        HomePage homePage = confirmPasscode.enterPasscodeInConfirmPasscodePage(TestDataReader.readData("passcode"), Target.ANDROID);

	        assertTrue(homePage.isHomePageLoaded(), "Verify if home page is displayed");
	        SettingsPage settingsPage = homePage.clickOnSettingIcon();

	        assertTrue(settingsPage.isSettingPageLoaded(), "Verify if setting page is displayed");
	        settingsPage.clickOnLanguage().clickOnFilipinoLanguage();

	        assertTrue(settingsPage.verifyFilipinoLanguage(), "Verify if language is changed to filipino");
	        ReceiveCardPage receiveCardPage =settingsPage.clickOnReceiveCardFilipinoLanguage();
	        
	        assertTrue(receiveCardPage.isReceiveCardHeaderInFilipinoLanguageDisplayed(), "Verify if QR code  header is displayed filipino");
	 }
}
