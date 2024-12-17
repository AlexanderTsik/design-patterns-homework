package ge.tbc.testautomation.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class TextBoxPage {
    public final SelenideElement fullNameField = $("#userName");
    public final SelenideElement emailField = $("#userEmail");
    public final SelenideElement currentAddressField = $("#currentAddress");
    public final SelenideElement permanentAddressField = $("#permanentAddress");
    public final SelenideElement submitButton = $("#submit");
    public final SelenideElement outputName = $("#output #name");
    public final SelenideElement outputEmail = $("#output #email");
    public final SelenideElement outputCurrentAddress = $("#output #currentAddress");
    public final SelenideElement outputPermanentAddress = $("#output #permanentAddress");
}
