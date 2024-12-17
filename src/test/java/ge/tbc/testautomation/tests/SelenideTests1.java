package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.pages.CheckBoxPage;
import ge.tbc.testautomation.pages.DropDownPage;
import ge.tbc.testautomation.pages.PricingPage;
import ge.tbc.testautomation.steps.CheckBoxSteps;
import ge.tbc.testautomation.steps.DropDownSteps;
import ge.tbc.testautomation.steps.TextBoxSteps;
import org.testng.annotations.Test;
import ge.tbc.testautomation.steps.PricingSteps;

import static ge.tbc.testautomation.data.Constants.*;

public class SelenideTests1 extends BaseTest {
    private final PricingSteps pricingSteps = new PricingSteps();
    private final PricingPage pricingPage = new PricingPage();
    private final CheckBoxSteps checkBoxSteps = new CheckBoxSteps();
    private final CheckBoxPage checkBoxPage = new CheckBoxPage();
    private final DropDownSteps dropDownSteps = new DropDownSteps();
    private final DropDownPage dropDownPage = new DropDownPage();
    private final TextBoxSteps textBoxSteps = new TextBoxSteps();

    @Test(groups="Selenide 1")
    public void validateBundleOffers() {
        pricingSteps.openPricingPage()

                // 1. 'Mocking solution for rapid unit testing' feature is not included in DevCraft UI.
                .validateNotIncluded(pricingPage.devCraftUIBundlePricingInfo,MOCKING_SOLUTION)

                // 2. 'Issue escalation' is supported only in DevCraft Ultimate.
                .validateIncluded(pricingPage.devCraftUltimateBundlePricingSupport,ISSUE_ESCALATION)
                .validateNotIncluded(pricingPage.devCraftUIBundlePricingSupport,ISSUE_ESCALATION)
                .validateNotIncluded(pricingPage.devCraftCompleteBundlePricingSupport,ISSUE_ESCALATION)

                // 3. 'End-to-end report management solution' is supported only in DevCraft Ultimate.
                .validateIncluded(pricingPage.devCraftUltimateBundlePricingInfo,REPORT_MANAGEMENT_SOLUTION)
                .validateNotIncluded(pricingPage.devCraftUIBundlePricingInfo,REPORT_MANAGEMENT_SOLUTION)
                .validateNotIncluded(pricingPage.devCraftCompleteBundlePricingInfo,REPORT_MANAGEMENT_SOLUTION)

                // 4. 'Telerik Test Studio Dev Edition' is supported only in DevCraft Ultimate.
                .validateFeatureNotIncluded(TESLERIK_TEST, 1)
                .validateFeatureNotIncluded(TESLERIK_TEST, 2)
                .validateFeatureIncluded(TESLERIK_TEST, 3)

                // 5. 'Kendo UI for jQuery' is supported on all offers.
                .validateFeatureIncluded(JQUERY, 1)
                .validateFeatureIncluded(JQUERY, 2)
                .validateFeatureIncluded(JQUERY, 3)

                // 6. DevCraft Ultimate supports 1 instance of 'Telerik Report Server' with 15 users.
                .validateCellText("Telerik Report Server", 3, "1 instance with 15 users")

                // 7. 'Telerik Reporting' is supported by only DevCraft Complete and DevCraft Ultimate.
                .validateFeatureNotIncluded("Telerik Reporting", 1)
                .validateFeatureIncluded("Telerik Reporting", 2)
                .validateFeatureIncluded("Telerik Reporting", 3)

                // 8. 'Access to on-demand videos' is supported by all offers.
                .validateFeatureIncluded("Access to on-demand videos", 1)
                .validateFeatureIncluded("Access to on-demand videos", 2)
                .validateFeatureIncluded("Access to on-demand videos", 3);
    }

    @Test (groups="Selenide 1")
    public void validateSticky() {
        pricingSteps.openPricingPage()
                .validateStickyHeader();
    }

    @Test(groups="Selenide 1")
    public void validateIndividualOffers() {
        pricingSteps.openIndividualPricing()
                //1) A Kendo Ninja image appears once you hover over any of the two offers.
                .validateKendoImages()
                //2) Both offers have Priority Support selected by default.
                .validateDropdownOptions()
                //  3) The price of Priority Support is $999 on KendoReact.
                .validatePricing(pricingPage.kendoReactPrice,999)
                //	4) The price of Priority Support is $1149 on Kendo UI.
                .validatePricing(pricingPage.kendoUIPrice,1149);
    }

    @Test(groups = {"dropDown-FrontEnd"})
    public void checkBoxTest() {
        checkBoxSteps.openCheckBoxPage()
                .validateCheckboxes();
    }

    @Test(groups = {"dropDown-FrontEnd"})
    public void dropDownTest() {
        dropDownSteps.openDropDownPage()
                .validateDropdownSelection("Option 2");
    }

    @Test(groups="Selenide 1")
    public void collectionsTest() {
        textBoxSteps.fillAndSubmitForm(
                FULL_NAME,          // Full Name
                EMAIL,              // Email
                CURRENT_ADDRESS,    // Current Address
                PERMANENT_ADDRESS   // Permanent Address
        ).validateOutput(
                FULL_NAME,
                EMAIL,
                CURRENT_ADDRESS,
                PERMANENT_ADDRESS
        );
    }
}
