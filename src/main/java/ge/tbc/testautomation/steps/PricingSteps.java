package ge.tbc.testautomation.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ge.tbc.testautomation.pages.PricingPage;
import ge.tbc.testautomation.utils.TableHandler;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static ge.tbc.testautomation.data.Constants.*;

public class PricingSteps {
    private final PricingPage pricingPage = new PricingPage();
    private final TableHandler tableHandler = new TableHandler();

    public PricingSteps openPricingPage() {
        open(DEMOS_URL);
        pricingPage.pricingLink.click();
        return this;
    }

    public PricingSteps openIndividualPricing() {
        open(DEMOS_URL);
        pricingPage.pricingLink.click();
        pricingPage.individualProductsTab.click();
        return this;
    }


    public PricingSteps validateFeatureNotIncluded(String feature, int columnIndex) {
        SelenideElement row = tableHandler.getRowByFirstColumnText(feature);
        SelenideElement cell = tableHandler.getCellInRow(row, columnIndex);
        cell.shouldNotHave(text("●"));
        return this;
    }

    public PricingSteps validateFeatureIncluded(String feature, int columnIndex) {
        SelenideElement row = tableHandler.getRowByFirstColumnText(feature);
        SelenideElement cell = tableHandler.getCellInRow(row, columnIndex);
        cell.$("span.dot").shouldBe(Condition.exist);
        return this;
    }

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


    public PricingSteps validateNotIncluded(SelenideElement bundle, String feature){
        bundle.shouldNotHave(text(feature));

        return this;
    }
    public PricingSteps validateIncluded(SelenideElement bundle, String feature){
        bundle.shouldHave(text(feature));

        return this;
    }

    public PricingSteps validateStickyHeader() {
        executeJavaScript("window.scrollBy(0, 1200)");
        pricingPage.stickyHeader.shouldBe(Condition.visible)
                .shouldHave(text("DevCraft UI"))
                .shouldHave(text("DevCraft Complete"))
                .shouldHave(text("DevCraft Ultimate"));
        return this;
    }

    public PricingSteps validateKendoImages() {
        pricingPage.kendoUIBox.scrollIntoView(true).hover();
        pricingPage.kendoUIImage.shouldBe(Condition.visible);

        pricingPage.kendoReactBox.scrollIntoView(true).hover();
        pricingPage.reactKendokaImage.shouldBe(Condition.visible);
        return this;
    }

    public PricingSteps validateDropdownOptions() {
        pricingPage.dropdownUI.shouldHave(text("Priority Support"));
        pricingPage.dropdownReact.shouldHave(text("Priority Support"));
        return this;
    }

    public PricingSteps validatePricing(SelenideElement priceElement, int expectedPrice){
        priceElement.shouldHave(text(String.format("%,d", expectedPrice)));
        return this;
    }
}

