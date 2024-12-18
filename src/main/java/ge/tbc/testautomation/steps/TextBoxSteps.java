package ge.tbc.testautomation.steps;

import com.codeborne.selenide.Condition;
import ge.tbc.testautomation.pages.TextBoxPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.open;
import static ge.tbc.testautomation.data.Constants.*;

public class TextBoxSteps {

    private final TextBoxPage textBoxPage = new TextBoxPage();

    @Step("Fill and submit the form with Full Name: {fullName}, Email: {email}, Current Address: {currentAddress}, Permanent Address: {permanentAddress}")
    public TextBoxSteps fillAndSubmitForm(String fullName, String email, String currentAddress, String permanentAddress) {
        open(TEXTBOX_URL); // Navigate to the page

        textBoxPage.fullNameField.setValue(fullName);
        textBoxPage.emailField.setValue(email);
        textBoxPage.currentAddressField.setValue(currentAddress);
        textBoxPage.permanentAddressField.setValue(permanentAddress);

        textBoxPage.submitButton.click();

        return this;
    }

    @Step("Validate the output of the form with Full Name: {fullName}, Email: {email}, Current Address: {currentAddress}, Permanent Address: {permanentAddress}")
    public TextBoxSteps validateOutput(String fullName, String email, String currentAddress, String permanentAddress) {
        textBoxPage.outputName.shouldHave(Condition.text("Name:" + fullName));
        textBoxPage.outputEmail.shouldHave(Condition.text("Email:" + email));
        textBoxPage.outputCurrentAddress.shouldHave(Condition.text("Current Address :" + currentAddress));
        textBoxPage.outputPermanentAddress.shouldHave(Condition.text("Permananet Address :" + permanentAddress));

        return this;
    }
}
