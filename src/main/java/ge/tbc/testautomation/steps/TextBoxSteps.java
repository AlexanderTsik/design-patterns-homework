package ge.tbc.testautomation.steps;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.testng.asserts.SoftAssert;
import ge.tbc.testautomation.pages.TextBoxPage;


import static com.codeborne.selenide.Selenide.open;
import static ge.tbc.testautomation.data.Constants.*;

public class TextBoxSteps {

    private final TextBoxPage textBoxPage = new TextBoxPage();

    public TextBoxSteps fillAndSubmitForm(String fullName, String email, String currentAddress, String permanentAddress) {
        open(TEXTBOX_URL); // Navigate to the page

        textBoxPage.fullNameField.setValue(fullName); // Fill the Full Name field
        textBoxPage.emailField.setValue(email);       // Fill the Email field
        textBoxPage.currentAddressField.setValue(currentAddress); // Fill the Current Address field
        textBoxPage.permanentAddressField.setValue(permanentAddress); // Fill the Permanent Address field

        textBoxPage.submitButton.click(); // Submit the form

        return this; // Return for Fluent Interface
    }

    public TextBoxSteps validateOutput(String fullName, String email, String currentAddress, String permanentAddress) {
        textBoxPage.outputName.shouldHave(Condition.text("Name:" + fullName));
        textBoxPage.outputEmail.shouldHave(Condition.text("Email:" + email));
        textBoxPage.outputCurrentAddress.shouldHave(Condition.text("Current Address :" + currentAddress));
        textBoxPage.outputPermanentAddress.shouldHave(Condition.text("Permananet Address :" + permanentAddress));

        return this;
    }
}

