package ge.tbc.testautomation.pages;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class HomePage {
    public final SelenideElement demosLink = $("a[href='/purchase.aspx']");
}
