package ge.tbc.testautomation.steps;

import com.codeborne.selenide.Condition;
import ge.tbc.testautomation.pages.OrderPage;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import static ge.tbc.testautomation.data.Constants.*;

public class OrderSteps {
    private final OrderPage orderPage = new OrderPage();

    @Step("Navigate to the Order page")
    public OrderSteps navigateToOrderPage() {
        open(DEMOS_URL);
        orderPage.pricingLink.click();
        orderPage.buyDevCraftComplete.scrollIntoCenter().click();
        return this;
    }

    @Step("Dismiss the login popup")
    public OrderSteps dismissLoginPopup() {
        orderPage.loginPopup.shouldBe(visible);
        orderPage.closePopup.click();
        orderPage.loginPopup.shouldNotBe(visible);
        return this;
    }

    @Step("Validate unit price. Expected: {expectedPrice}")
    public OrderSteps validateUnitPrice(String expectedPrice) {
        double actualPrice = Double.parseDouble(orderPage.unitPrice.getText().replaceAll("[^\\d.]", ""));
        double expected = Double.parseDouble(expectedPrice);

        assert actualPrice == expected : "Expected: " + expected + ", but found: " + actualPrice;
        return this;
    }

    @Step("Validate discounts and prices")
    public OrderSteps validateDiscountsAndPrices() {
        orderPage.quantityDropdown.click();
        orderPage.discountPopup.shouldBe(Condition.visible);

        String discount25Text = orderPage.getDiscountPercentage(orderPage.getDiscountRow(0));
        String discount610Text = orderPage.getDiscountPercentage(orderPage.getDiscountRow(1));

        double discount25 = extractPercentage(discount25Text);
        double discount610 = extractPercentage(discount610Text);

        System.out.println("2-5 Licenses Discount: " + discount25 + "%");
        System.out.println("6-10 Licenses Discount: " + discount610 + "%");

        double originalPrice = ORIGINAL_PRICE;
        double calculatedDiscount25 = originalPrice * (discount25 / 100);
        double calculatedDiscount610 = originalPrice * (discount610 / 100);

        setQuantityAndValidateSavings("2", calculatedDiscount25);
        setQuantityAndValidateSavings("6", calculatedDiscount610);

        return this;
    }

    private double extractPercentage(String discountText) {
        return Double.parseDouble(discountText.replaceAll("[^0-9]", ""));
    }

    private void setQuantityAndValidateSavings(String quantity, double expectedSavings) {
        orderPage.quantityDropdown.click();
        orderPage.quantityDropdown.sendKeys(quantity);
        sleep(500);
        orderPage.quantityDropdown.sendKeys(Keys.ENTER);

        orderPage.savings.shouldHave(Condition.text(String.format("%.2f", expectedSavings)));
    }

    @Step("Validate subtotal dynamically for quantity: {quantity} and term: {term}")
    public OrderSteps validateSubtotalDynamically(int quantity, int term) {
        double unitPriceValue = Double.parseDouble(orderPage.unitPrice.getText().replaceAll("[^\\d.]", ""));

        orderPage.termDropdown.click();
        orderPage.termDropdown.sendKeys(String.valueOf(term - 1));
        orderPage.termDropdown.pressEnter();

        orderPage.quantityDropdown.click();
        orderPage.quantityDropdown.sendKeys(String.valueOf(quantity));
        orderPage.quantityDropdown.pressEnter();

        double expectedSubtotal = unitPriceValue * quantity * term;
        double actualSubtotal = Double.parseDouble(orderPage.subtotal.getText().replaceAll("[^\\d.]", ""));

        assert Math.abs(actualSubtotal - expectedSubtotal) < 0.01
                : String.format("Subtotal mismatch! Expected: %.2f, but Found: %.2f", expectedSubtotal, actualSubtotal);

        System.out.println(String.format("Subtotal validated successfully: %.2f", actualSubtotal));
        return this;
    }

    @Step("Validate total discounts")
    public OrderSteps validateTotalDiscounts() {
        double savingsPerUnit = extractPrice(orderPage.savings.getText());
        int quantity = Integer.parseInt(orderPage.selectedQuantity.getText().trim());
        double expectedTotalDiscount = savingsPerUnit * quantity;

        orderPage.totalDiscountsLabel.hover();
        orderPage.tooltip.shouldBe(visible);

        double actualTotalDiscount = extractPrice(orderPage.licensesDiscountValue.getText());

        assert Math.abs(expectedTotalDiscount - actualTotalDiscount) < 0.01
                : String.format("Discount mismatch! Expected: %.2f, Found: %.2f", expectedTotalDiscount, actualTotalDiscount);

        System.out.println("Total Discount validated successfully: " + actualTotalDiscount);
        return this;
    }

    private double extractPrice(String priceText) {
        return Double.parseDouble(priceText.replaceAll("[^\\d.]", ""));
    }

    @Step("Validate total value matches subtotal")
    public OrderSteps validateTotalValue() {
        double subtotal = extractPrice(orderPage.subtotal.getText());
        double total = extractPrice(orderPage.totalPrice.getText());

        assert subtotal == total : "Subtotal and Total mismatch!";
        System.out.println("Subtotal: " + subtotal + ", Total: " + total);
        return this;
    }

    @Step("Fill the order form and validate navigation")
    public OrderSteps fillFormAndValidateNavigation() {
        orderPage.continueAsGuestButton.scrollIntoCenter().click();

        orderPage.firstNameInput.setValue(FIRST_NAME);
        orderPage.lastNameInput.setValue(LAST_NAME);
        orderPage.emailInput.setValue(EMAIL);
        orderPage.companyInput.setValue(COMPANY);
        orderPage.phoneInput.setValue(PHONE);
        orderPage.addressInput.setValue(ADDRESS);
        orderPage.cityInput.setValue(CITY);
        orderPage.zipCodeInput.setValue(ZIP_CODE);

        orderPage.countryComboBox.$(".k-input-button").click();
        orderPage.countryDropdownList.shouldBe(visible)
                .findAll("li")
                .findBy(text(COUNTRY))
                .click();

        orderPage.continueButton.scrollIntoCenter().click();

        orderPage.backButton.scrollIntoCenter().click();
        orderPage.firstNameInput.shouldHave(value(FIRST_NAME));
        orderPage.lastNameInput.shouldHave(value(LAST_NAME));
        orderPage.emailInput.shouldHave(value(EMAIL));
        orderPage.companyInput.shouldHave(value(COMPANY));
        orderPage.phoneInput.shouldHave(value(PHONE));
        orderPage.addressInput.shouldHave(value(ADDRESS));
        orderPage.cityInput.shouldHave(value(CITY));
        orderPage.zipCodeInput.shouldHave(value(ZIP_CODE));

        return this;
    }
}
