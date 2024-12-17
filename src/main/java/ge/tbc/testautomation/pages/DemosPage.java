package ge.tbc.testautomation.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DemosPage {
    // Web Section Cards
    public ElementsCollection webSectionCards = $$("div.HoverImg.u-mb1");

    // Kendo UI Card and its overlay container
    public SelenideElement kendoUICard = $("div.HoverImg.u-mb1 img[title='Kendo Ui']").parent();
    public ElementsCollection kendoUILinks = kendoUICard.$$(".LinkContainer a");

    //desktop section
    public SelenideElement desktopSectionHeader = $("h2#desktop");
    public ElementsCollection desktopCards = desktopSectionHeader.sibling(0).parent().$$("div.sf_colsIn");
    public SelenideElement microsoftStoreLink = $("a[href*='microsoft.com/store']");

    //Mobile Section
    public SelenideElement mobileSection = $("#mobile");
    public SelenideElement telerikXamarinCard = mobileSection.parent()
            .$$("#ContentPlaceholder1_C340_Col01")
            .findBy(text("Telerik UI for Xamarin"));


    // Store links for validation
    public SelenideElement appleStoreLink = telerikXamarinCard.$("a[href*='itunes.apple.com']");
    public SelenideElement googlePlayLink = telerikXamarinCard.$("a[href*='play.google.com']");
    public SelenideElement microsoftStoreLinkXam = telerikXamarinCard.$("a[href*='microsoft.com']");

    public final SelenideElement navBar = $("nav.NavAlt");
    public final ElementsCollection navLinks = $$("nav.NavAlt a.NavAlt-anchor");

    public SelenideElement getSection(String sectionId) {
        return $(sectionId);
    }
    public final SelenideElement footer = $("footer");

}
