package ge.tbc.testautomation.steps;
import com.codeborne.selenide.Condition;
import ge.tbc.testautomation.pages.CheckBoxPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static ge.tbc.testautomation.data.Constants.*;

public class CheckBoxSteps {
    private final CheckBoxPage checkBoxPage = new CheckBoxPage();
    public CheckBoxSteps openCheckBoxPage(){
        open(CHECKBOXES_URL);
        return this;
    }

    public CheckBoxSteps validateCheckboxes() {
        checkBoxPage.checkboxes.get(0).setSelected(true);
        checkBoxPage.checkboxes.forEach(checkbox -> checkbox.shouldHave(Condition.attribute("type", "checkbox")));
        return this;
    }
}
