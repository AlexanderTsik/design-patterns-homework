package ge.tbc.testautomation.steps;

import ge.tbc.testautomation.pages.DropDownPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static ge.tbc.testautomation.data.Constants.*;

public class DropDownSteps {
    public final DropDownPage dropDownPage = new DropDownPage();
    @Step("Open dropdown page")
    public DropDownSteps openDropDownPage(){
        open(DROPDOWN_URL);
        return this;
    }

    @Step("Validate dropdown selection with option: {option}")
    public DropDownSteps validateDropdownSelection(String option) {
        dropDownPage.dropdown.shouldHave(text("Please select an option")); // Validate default text
        dropDownPage.dropdown.selectOption(option); // Select the desired option
        dropDownPage.dropdown.shouldHave(text(option)); // Validate selected option
        return this;
    }
}
