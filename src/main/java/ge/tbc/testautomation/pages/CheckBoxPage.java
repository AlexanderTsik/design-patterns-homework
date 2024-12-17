package ge.tbc.testautomation.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class CheckBoxPage {
    public final SelenideElement checkboxesForm = $("#checkboxes");
    public final ElementsCollection checkboxes = checkboxesForm.$$("input[type='checkbox']");
}
