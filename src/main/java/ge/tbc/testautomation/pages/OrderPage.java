package ge.tbc.testautomation.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class OrderPage {
    public final SelenideElement pricingLink = $$(".TK-Menu-Item a").findBy(text("Pricing"));
    public final SelenideElement buyDevCraftComplete = $("a[href='https://store.progress.com/configure-purchase?skuId=801']");
    public final SelenideElement loginPopup = $(".popup-hidden.login-container");
    public final SelenideElement closePopup = loginPopup.$("i.far.fa-times");

    public final SelenideElement unitPrice = $(".e2e-price-per-license");
    public final SelenideElement quantityDropdown = $("quantity-select kendo-dropdownlist");
    public final SelenideElement subtotal = $(".e2e-cart-item-subtotal");

    public final SelenideElement tooltipTrigger = $(".e2e-total-discounts-label");
    public final SelenideElement tooltip = $(".tooltip-info.tooltip-info--font-l.tooltip-info--no-after");
    public final SelenideElement licensesLabel = tooltip.$(".e2e-tooltip-licenses-discounts-label");
    public final SelenideElement licensesValue = tooltip.$(".e2e-licenses-discounts");
    public final SelenideElement msLabel = tooltip.$(".e2e-tooltip-ms-discounts-label");
    public final SelenideElement msValue = tooltip.$(".e2e-ms-discounts");

    public final SelenideElement total = $(".e2e-total-price");
    public final SelenideElement continueAsGuestButton = $("button.e2e-continue");
    public final SelenideElement backButton = $(".btn.btn-default.e2e-back");

    public final SelenideElement firstNameInput = $("#biFirstName");
    public final SelenideElement lastNameInput = $("#biLastName");
    public final SelenideElement emailInput = $("#biEmail");
    public final SelenideElement companyInput = $("#biCompany");
    public final SelenideElement phoneInput = $("#biPhone");
    public final SelenideElement addressInput = $("#biAddress");
    public final SelenideElement cityInput = $("#biCity");
    public final SelenideElement zipCodeInput = $("#biZipCode");
    public final SelenideElement countryComboBox = $("kendo-combobox#biCountry");
    public final SelenideElement countryDropdownList = $(".k-list-container");

    // Quantity dropdown and popup

    public SelenideElement discountPopup = $("kendo-popup .k-popup");

    // Discount rows
    public ElementsCollection discountRows = discountPopup.$$(
            "div.u-df.justify-content-between.u-mb10"
    );

    public SelenideElement savings = $(".label.label--grey.sm-hidden.e2e-item-licenses-savings");

    // Get row dynamically by index
    public SelenideElement getDiscountRow(int index) {
        return discountRows.get(index);
    }

    // Helper to get discount percentage from a row
    public String getDiscountPercentage(SelenideElement row) {
        return row.$("span.page-body--success").getText();
    }


    public SelenideElement termDropdown = $("period-select kendo-dropdownlist");

    public SelenideElement totalDiscountsLabel = $(".e2e-total-discounts-label");
    public SelenideElement licensesDiscountValue = $(".e2e-licenses-discounts");
    public SelenideElement selectedQuantity = $(".k-input-value-text");
    public SelenideElement totalPrice = $(".e2e-total-price");
    public SelenideElement continueButton = $(".btn.btn-primary.e2e-continue");

}
