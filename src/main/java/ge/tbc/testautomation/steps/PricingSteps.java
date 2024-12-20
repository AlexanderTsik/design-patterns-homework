package ge.tbc.testautomation.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ge.tbc.testautomation.pages.PricingPage;
import ge.tbc.testautomation.utils.TableHandler;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

import static ge.tbc.testautomation.data.Constants.*;

public class PricingSteps {
    private final PricingPage pricingPage = new PricingPage();
    private final TableHandler tableHandler = new TableHandler();

    @Step("Open the Pricing page")
    public PricingSteps openPricingPage() {
        open(DEMOS_URL);
        pricingPage.pricingLink.click();
        return this;
    }

    @Step("Open the Individual Pricing page")
    public PricingSteps openIndividualPricing() {
        open(DEMOS_URL);
        pricingPage.pricingLink.click();
        pricingPage.individualProductsTab.click();
        return this;
    }

    @Step("Validate feature '{feature}' is NOT included in column index: {columnIndex}")
    public PricingSteps validateFeatureNotIncluded(String feature, int columnIndex) {
        SelenideElement row = tableHandler.getRowByFirstColumnText(feature);
        SelenideElement cell = tableHandler.getCellInRow(row, columnIndex);
        cell.shouldNotHave(text("●"));
        return this;
    }

    @Step("Validate feature '{feature}' is included in column index: {columnIndex}")
    public PricingSteps validateFeatureIncluded(String feature, int columnIndex) {
        SelenideElement row = tableHandler.getRowByFirstColumnText(feature);
        SelenideElement cell = tableHandler.getCellInRow(row, columnIndex);
        cell.$("span.dot").shouldBe(Condition.exist);
        return this;
    }

    @Step("Validate cell text for row '{rowText}' in column index: {columnIndex}")
    public PricingSteps validateCellText(String rowText, int columnIndex, String expectedText) {
        SelenideElement row = tableHandler.getRowByFirstColumnText(rowText);
        SelenideElement cell = tableHandler.getCellInRow(row, columnIndex);
        cell.scrollIntoView(true).hover();
        SelenideElement textElement = cell.$("p span.u-fs15");
        String actualText = textElement.getText().trim();
        if (!actualText.equals(expectedText)) {
            throw new AssertionError("Expected text: '" + expectedText + "' but found: '" + actualText + "'");
        }
        return this;
    }

    @Step("Validate feature '{feature}' is NOT included in the bundle")
    public PricingSteps validateNotIncluded(SelenideElement bundle, String feature) {
        bundle.shouldNotHave(text(feature));
        return this;
    }

    @Step("Validate feature '{feature}' is included in the bundle")
    public PricingSteps validateIncluded(SelenideElement bundle, String feature) {
        bundle.shouldHave(text(feature));
        return this;
    }

    @Step("Validate the sticky header is visible and contains expected text")
    public PricingSteps validateStickyHeader() {
        executeJavaScript("window.scrollBy(0, 1200)");
        pricingPage.stickyHeader.shouldBe(Condition.visible)
                .shouldHave(text("DevCraft UI"))
                .shouldHave(text("DevCraft Complete"))
                .shouldHave(text("DevCraft Ultimate"));
        return this;
    }

    @Step("Validate Kendo images are visible")
    public PricingSteps validateKendoImages() {
        pricingPage.kendoUIBox.scrollIntoView(true).hover();
        pricingPage.kendoUIImage.shouldBe(Condition.visible);

        pricingPage.kendoReactBox.scrollIntoView(true).hover();
        pricingPage.reactKendokaImage.shouldBe(Condition.visible);
        return this;
    }

    @Step("Validate dropdown options have 'Priority Support' selected by default")
    public PricingSteps validateDropdownOptions() {
        pricingPage.dropdownUI.shouldHave(text("Priority Support"));
        pricingPage.dropdownReact.shouldHave(text("Priority Support"));
        return this;
    }

    @Step("Validate the price element matches the expected price: {expectedPrice}")
    public PricingSteps validatePricing(SelenideElement priceElement, int expectedPrice) {
        priceElement.shouldHave(text(String.format("%,d", expectedPrice)));
        return this;
    }
}
