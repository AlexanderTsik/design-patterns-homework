package ge.tbc.testautomation.steps;

import com.codeborne.selenide.Condition;
import ge.tbc.testautomation.pages.OrderPage;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;
import static ge.tbc.testautomation.data.Constants.*;

public class OrderSteps {
    OrderPage orderPage = new OrderPage();

    public OrderSteps navigateToOrderPage() {
        open(DEMOS_URL);
        orderPage.pricingLink.click();
        orderPage.buyDevCraftComplete.scrollIntoCenter().click();
        return this;
    }

    public OrderSteps dismissLoginPopup() {
        orderPage.loginPopup.shouldBe(visible);
        orderPage.closePopup.click();
        orderPage.loginPopup.shouldNotBe(visible);
        return this;
    }

    public OrderSteps validateUnitPrice(String expectedPrice) {
        double actualPrice = Double.parseDouble(orderPage.unitPrice.getText()
                .replaceAll("[^\\d.]", "")); // Keep only digits and decimal points
        double expected = Double.parseDouble(expectedPrice);

        assert actualPrice == expected : "Expected: " + expected + ", but found: " + actualPrice;
        return this;
    }

    public OrderSteps validateDiscountsAndPrices() {
        // Open the popup by interacting with the quantity dropdown
        orderPage.quantityDropdown.click();
        orderPage.discountPopup.shouldBe(Condition.visible);

        // Extract percentage values dynamically
        String discount25Text = orderPage.getDiscountPercentage(orderPage.getDiscountRow(0));
        String discount610Text = orderPage.getDiscountPercentage(orderPage.getDiscountRow(1));

        // Convert percentages to numerical values
        double discount25 = extractPercentage(discount25Text);
        double discount610 = extractPercentage(discount610Text);

        System.out.println("2-5 Licenses Discount: " + discount25 + "%");
        System.out.println("6-10 Licenses Discount: " + discount610 + "%");

        // Original price
        double originalPrice = 1499.0;

        // Calculate expected savings
        double calculatedDiscount25 = originalPrice * (discount25 / 100);
        double calculatedDiscount610 = originalPrice * (discount610 / 100);

        // Validate calculated savings dynamically for quantity 2-5
        setQuantityAndValidateSavings("2", calculatedDiscount25);

        // Validate calculated savings dynamically for quantity 6-10
        setQuantityAndValidateSavings("6", calculatedDiscount610);

        return this;
    }

    // Helper method to extract numerical percentage from text
    private double extractPercentage(String discountText) {
        return Double.parseDouble(discountText.replaceAll("[^0-9]", ""));
    }

    // Helper method to set quantity and validate savings
    private void setQuantityAndValidateSavings(String quantity, double expectedSavings) {
        orderPage.quantityDropdown.click();
        orderPage.quantityDropdown.sendKeys(quantity);
        sleep(500); // Slight wait to ensure dropdown registers input
        orderPage.quantityDropdown.sendKeys(Keys.ENTER);

        // Validate the savings
        orderPage.savings.shouldHave(Condition.text(String.format("%.2f", expectedSavings)));
    }

    public OrderSteps validateSubtotalDynamically(int quantity, int term) {
        // Extract and clean unit price
        double unitPriceValue = Double.parseDouble(orderPage.unitPrice.getText().replaceAll("[^\\d.]", ""));

        // Select the term
        orderPage.termDropdown.click();
        orderPage.termDropdown.sendKeys(String.valueOf(term-1));
        orderPage.termDropdown.pressEnter();

        // Select the quantity
        orderPage.quantityDropdown.click();
        orderPage.quantityDropdown.sendKeys(String.valueOf(quantity));
        orderPage.quantityDropdown.pressEnter();

        // Calculate expected subtotal
        double expectedSubtotal = unitPriceValue * quantity * term;

        // Extract actual subtotal and clean it
        double actualSubtotal = Double.parseDouble(orderPage.subtotal.getText().replaceAll("[^\\d.]", ""));
        assert Math.abs(actualSubtotal - expectedSubtotal) < 0.01
                : String.format("Subtotal mismatch! Expected: %.2f, but Found: %.2f", expectedSubtotal, actualSubtotal);

        System.out.println(String.format("Subtotal validated successfully: %.2f", actualSubtotal));
        return this;
    }



    public OrderSteps validateTotalDiscounts() {
        // Step 1: Extract the savings per unit
        double savingsPerUnit = extractPrice(orderPage.savings.getText());

        // Step 2: Get the currently selected quantity
        int quantity = Integer.parseInt(orderPage.selectedQuantity.getText().trim());

        // Step 3: Calculate the expected total discount
        double expectedTotalDiscount = savingsPerUnit * quantity;

        // Step 4: Hover over the tooltip and get the displayed discount
        orderPage.totalDiscountsLabel.hover();
        orderPage.tooltip.shouldBe(visible);

        double actualTotalDiscount = extractPrice(orderPage.licensesDiscountValue.getText());

        assert Math.abs(expectedTotalDiscount - actualTotalDiscount) < 0.01
                : String.format("Discount mismatch! Expected: %.2f, Found: %.2f", expectedTotalDiscount, actualTotalDiscount);

        System.out.println("Total Discount validated successfully: " + actualTotalDiscount);
        return this;
    }

    // Helper method to extract price values (e.g., Save $149.90 -> 149.90)
    private double extractPrice(String priceText) {
        return Double.parseDouble(priceText.replaceAll("[^\\d.]", ""));
    }

    public OrderSteps validateTotalValue() {
        double subtotal = extractPrice(orderPage.subtotal.getText());
        double total = extractPrice(orderPage.totalPrice.getText());

        assert subtotal == total : "Subtotal and Total mismatch!";
        System.out.println("Subtotal: " + subtotal + ", Total: " + total);
        return this;
    }

    public OrderSteps fillFormAndValidateNavigation() {
        orderPage.continueAsGuestButton.scrollIntoCenter().click();

        orderPage.firstNameInput.setValue("John");
        orderPage.lastNameInput.setValue("Doe");
        orderPage.emailInput.setValue("john.doe@example.com");
        orderPage.companyInput.setValue("Example Corp");
        orderPage.phoneInput.setValue("+1234567890");
        orderPage.addressInput.setValue("123 Main Street");
        orderPage.cityInput.setValue("New York");
        orderPage.zipCodeInput.setValue("10001");

        orderPage.countryComboBox.$(".k-input-button").click(); // Open the dropdown
        orderPage.countryDropdownList.shouldBe(visible)
                .findAll("li")
                .findBy(text("Afghanistan"))
                .click();
        // Click "Continue" button
        orderPage.continueButton.scrollIntoCenter().click();

        // Navigate back and verify inputs
        orderPage.backButton.scrollIntoCenter().click();
        orderPage.firstNameInput.shouldHave(value("John"));
        orderPage.lastNameInput.shouldHave(value("Doe"));
        orderPage.emailInput.shouldHave(value("john.doe@example.com"));
        orderPage.companyInput.shouldHave(value("Example Corp"));
        orderPage.phoneInput.shouldHave(value("+1234567890"));
        orderPage.addressInput.shouldHave(value("123 Main Street"));
        orderPage.cityInput.shouldHave(value("New York"));
        orderPage.zipCodeInput.shouldHave(value("10001"));

        return this;
    }

}
