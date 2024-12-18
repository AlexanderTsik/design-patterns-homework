package ge.tbc.testautomation.steps;
import com.codeborne.selenide.Condition;
import ge.tbc.testautomation.pages.DemosPage;
import ge.tbc.testautomation.pages.HomePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.open;
import static ge.tbc.testautomation.data.Constants.*;
import static org.testng.Assert.assertTrue;

public class DemosSteps {
    private final HomePage homePage = new HomePage();
    private final DemosPage demosPage = new DemosPage();

    @Step("Open demos page")
    public DemosSteps openDemosPage() {
        open(DEMOS_URL);
        return this;
    }

    @Step("Validate that the purple overlay has the expected color: {expectedColor}")
    public DemosSteps validatePurpleOverlay(String expectedColor) {
        demosPage.webSectionCards.forEach(card -> card.hover()
                .shouldHave(cssValue("background-color", expectedColor)));
        return this;
    }

    @Step("Validate the Kendo UI overlay text contains: {expectedText}")
    public DemosSteps validateKendoUIOverlay(String expectedText) {
        demosPage.kendoUICard.hover();
        System.out.println(demosPage.kendoUICard.text());
        assertTrue(demosPage.kendoUILinks.stream()
                        .anyMatch(link -> link.getText().contains(expectedText)),
                "Overlay text missing: " + expectedText);
        return this;
    }

    @Step("Filter desktop cards for Microsoft Store links")
    public DemosSteps filterMicrosoftStoreItems() {
        // Filter desktop cards containing a Microsoft Store link

        List<String> availableCards = demosPage.desktopCards.stream()
                .filter(card -> card.$("a[href*='microsoft.com/store']").exists())
                .map(card -> card.$("h3").text())
                .collect(Collectors.toList());

        // Print filtered cards
        System.out.println((availableCards));
        availableCards.forEach(card -> System.out.println("Available on Microsoft Store: " + card));

        // Assert at least one card is found
        if (availableCards.isEmpty()) {
            throw new AssertionError("No cards available on Microsoft Store.");
        }

        return this;
    }

    @Step("Validate Telerik Xamarin availability in mobile section")
    public DemosSteps validateTelerikXamarinAvailability() {
        demosPage.mobileSection.scrollIntoView(true);

        demosPage.appleStoreLink.shouldBe(visible);
        System.out.println("Apple Store link is visible");
        demosPage.googlePlayLink.shouldBe(visible);
        System.out.println("Google Play link is visible");
        demosPage.microsoftStoreLinkXam.shouldBe(visible);
        System.out.println("Microsoft Store link is visible");

        return this;
    }


    @Step("Validate the sticky navigation bar functionality")
    public DemosSteps validateStickyNavBar() {
        demosPage.footer.scrollTo();
        demosPage.navBar.shouldHave(Condition.cssValue("position", "fixed"));
        demosPage.navBar.shouldHave(Condition.cssClass("is-fixed"));
        return this;
    }

    @Step("Validate navigation links highlight for each section")
    public DemosSteps validateLinks(String[][] sections) {
        for (String[] section : sections) {
            String sectionId = section[0];
            String linkText = section[1];

            $(sectionId).scrollTo();
            demosPage.navLinks.findBy(Condition.text(linkText)).shouldHave(Condition.cssClass("is-active"));
        }
        return this;
    }

    @Step("Validate links navigation works for all sections")
    public DemosSteps validateLinksWork(String[][] links) {
        for (String[] link : links) {
            String sectionId = link[0];
            String linkText = link[1];

            demosPage.navLinks.findBy(text(linkText)).click();// Click the navigation link based on text
            demosPage.getSection(sectionId).shouldBe(visible);// Verify the correct section is visible
        }
        return this;
    }
}

