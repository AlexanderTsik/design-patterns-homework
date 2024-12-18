package ge.tbc.testautomation.steps;
import com.codeborne.selenide.Condition;
import ge.tbc.testautomation.pages.CheckBoxPage;

import static com.codeborne.selenide.Selenide.*;
import static ge.tbc.testautomation.data.Constants.*;
import io.qameta.allure.*;

public class CheckBoxSteps {
    private final CheckBoxPage checkBoxPage = new CheckBoxPage();

    @Step("Open the checkbox page")
    public CheckBoxSteps openCheckBoxPage(){
        open(CHECKBOXES_URL);
        return this;
    }
    @Step("Validate all checkboxes are selectable and of type 'checkbox'")
    public CheckBoxSteps validateCheckboxes() {
        checkBoxPage.checkboxes.get(0).setSelected(true);
        checkBoxPage.checkboxes.forEach(checkbox -> checkbox.shouldHave(Condition.attribute("type", "checkbox")));
        return this;
    }
}
