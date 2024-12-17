package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.retry.RetryAnalyzer;
import ge.tbc.testautomation.retry.RetryCount;
import ge.tbc.testautomation.steps.OrderSteps;
import org.testng.annotations.Test;
import ge.tbc.testautomation.steps.DemosSteps;
import ge.tbc.testautomation.steps.BookSteps;

import static ge.tbc.testautomation.data.Constants.*;

public class SelenideTests2 extends BaseTest {
    private final DemosSteps demosSteps = new DemosSteps();
    private final BookSteps bookSteps = new BookSteps();
    private final OrderSteps orderSteps = new OrderSteps();
    @Test(groups="Selenide 2")
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

    @RetryCount(count = 5)
    @Test(groups="Selenide 2", retryAnalyzer = RetryAnalyzer.class)
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

    @Test(groups="Selenide 2")
    public void chainedLocatorsTest() {
        bookSteps.openBooksPage()
                .validateBooksByPublisherAndTitle(TARGET_PUBLISHER, TITLE_KEYWORD);
    }

    @Test(groups="Selenide 2")
    public void softAssertTest() {
        bookSteps.openBooksPage()

                .validateBooksWithSoftAssertions(
                TARGET_PUBLISHER, TITLE_KEYWORD, EXPECTED_SIZE, EXPECTED_FIRST_BOOK_TITLE
                );
    }
}

