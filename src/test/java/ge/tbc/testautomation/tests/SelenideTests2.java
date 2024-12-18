package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.retry.RetryAnalyzer;
import ge.tbc.testautomation.retry.RetryCount;
import ge.tbc.testautomation.steps.OrderSteps;
import org.testng.annotations.Test;
import ge.tbc.testautomation.steps.DemosSteps;
import ge.tbc.testautomation.steps.BookSteps;

import io.qameta.allure.*;

import static ge.tbc.testautomation.data.Constants.*;

@Epic("Application Features")
@Feature("Demos and Orders")
public class SelenideTests2 extends BaseTest {
    private final DemosSteps demosSteps = new DemosSteps();
    private final BookSteps bookSteps = new BookSteps();
    private final OrderSteps orderSteps = new OrderSteps();


    @Severity(SeverityLevel.NORMAL)
    @Story("Validate demos page design elements")
    @Description("This test checks the design consistency of the demos page, including overlays, links, and navigation bar.")
    @Test(groups = "Selenide 2")
    public void validateDemosDesign() {
        demosSteps.openDemosPage()

                .validatePurpleOverlay(EXPECTED_COLOR)
                .validateKendoUIOverlay(EXPECTED_OVERLAY_TEXT)
                .filterMicrosoftStoreItems()
                .validateTelerikXamarinAvailability()
                .validateStickyNavBar()
                .validateLinks(SECTION_LINKS)
                .validateLinksWork(SECTION_LINKS);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Story("Validate order mechanics")
    @Description("This test verifies the functionality of the order page, including pricing calculations, discounts, and form navigation.")
    @Test(groups = "Selenide 2")
    public void validateOrderMechanics(){
        orderSteps.navigateToOrderPage()
                .dismissLoginPopup()
                .validateUnitPrice(PRICE_NUM)
                .validateDiscountsAndPrices()
                .validateSubtotalDynamically(QUANTITY_NUM,TERM_NUM)
                .validateTotalDiscounts()
                .validateTotalValue()
                .fillFormAndValidateNavigation();
    }

    @Severity(SeverityLevel.NORMAL)
    @Story("Test chained locators for book validation")
    @Description("This test validates books by publisher and title using chained locators.")
    @Test(groups = "Selenide 2")
    public void chainedLocatorsTest() {
        bookSteps.openBooksPage()
                .validateBooksByPublisherAndTitle(TARGET_PUBLISHER, TITLE_KEYWORD);
    }

    @Severity(SeverityLevel.MINOR)
    @Story("Validate books with soft assertions")
    @Description("This test verifies books based on the publisher and title using soft assertions.")
    @Test(groups = "Selenide 2")
    public void softAssertTest() {
        bookSteps.openBooksPage()

                .validateBooksWithSoftAssertions(
                TARGET_PUBLISHER, TITLE_KEYWORD, EXPECTED_SIZE, EXPECTED_FIRST_BOOK_TITLE
                );
    }
}

