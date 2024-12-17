package ge.tbc.testautomation.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.*;

public class PricingPage {
    // Pricing Info
    public SelenideElement devCraftUIBundlePricingInfo = $(byCssSelector("tr.Pricings-info")).$(byCssSelector("th.UI.is-active"));
    public SelenideElement devCraftCompleteBundlePricingInfo = $(byCssSelector("tr.Pricings-info")).$(byCssSelector("th.Complete"));
    public SelenideElement devCraftUltimateBundlePricingInfo = $(byCssSelector("tr.Pricings-info")).$(byCssSelector("th.Ultimate"));

    // Pricing Support
    public SelenideElement devCraftUIBundlePricingSupport = $(byCssSelector("tr.Pricings-support")).$(byCssSelector("th.UI.is-active"));
    public SelenideElement devCraftCompleteBundlePricingSupport = $(byCssSelector("tr.Pricings-support")).$(byCssSelector("th.Complete"));
    public SelenideElement devCraftUltimateBundlePricingSupport = $(byCssSelector("tr.Pricings-support")).$(byCssSelector("th.Ultimate"));



    public SelenideElement pricingLink = $("a[href='/purchase.aspx']");

    // product type links
    public SelenideElement productBundlesTab = $(".Tabs").$("a[href='#product-bundles']");
    public SelenideElement individualProductsTab = $(".Tabs").$("a[href='#individual-products']");
    public SelenideElement renewTab = $(".Tabs").$("a[href='#renew']");

    //kendo Images
    public SelenideElement kendoUICard = $(".Box--pricing3[data-opti-expid='Kendo UI']");
    public SelenideElement kendoUIPrice = kendoUICard.$("span.js-price");
    public SelenideElement kendoUIImage = kendoUICard.$(".Box-ninja img[alt='kendoka']");

    public SelenideElement kendoReactCard = $(".Box--pricing3[data-opti-expid='KendoReact']");
    public SelenideElement kendoReactPrice = kendoReactCard.$("span.js-price");
    public SelenideElement reactKendokaImage = kendoReactCard.$(".Box-ninja img[alt='react-kendoka']");



    public final SelenideElement stickyHeader = $("#js-sticky-head");
    public final SelenideElement kendoUIBox = $(".Box--pricing3[data-opti-expid='Kendo UI']");
    public final SelenideElement kendoReactBox = $(".Box--pricing3[data-opti-expid='KendoReact']");
    public final SelenideElement dropdownUI = kendoUIBox.find(".Dropdown");
    public final SelenideElement dropdownReact = kendoReactBox.find(".Dropdown");
}
