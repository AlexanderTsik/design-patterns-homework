package ge.tbc.testautomation.data;

import com.codeborne.selenide.SelenideElement;
import ge.tbc.testautomation.pages.PricingPage;
import java.util.List;

public class Constants {
    static PricingPage pricingPage = new PricingPage();

    //Columns
    public static final int DEVCRAFT_UI_COLUMN = 1;
    public static final int DEVCRAFT_COMPLETE_COLUMN = 2;
    public static final int DEVCRAFT_ULTIMATE_COLUMN = 3;

    //Strings
    public static final String MOCKING_SOLUTION = "Mocking solution for rapid unit testing";
    public static final String ISSUE_ESCALATION = "Issue escalation";
    public static final String REPORT_MANAGEMENT_SOLUTION = "End-to-end report management solution";
    public static final String TESLERIK_TEST = "Telerik Test Studio Dev Edition";
    public static final String JQUERY = "Kendo UI for jQuery";
    public static final String REPORT_SERVER = "Telerik Report Server";
    public static final String REPORT_SERVER_INSTANCES = "1 instance with 15 users";
    public static final String REPORTING = "Telerik Reporting";
    public static final String ON_DEMAND_VIDEOS = "Access to on-demand videos";

    public static final List<SelenideElement> JQUERY_SUPPORTED_COLUMNS = List.of(
            pricingPage.devCraftUIBundlePricingInfo,
            pricingPage.devCraftCompleteBundlePricingInfo,
            pricingPage.devCraftUltimateBundlePricingInfo
    );

    public static final List<SelenideElement> ON_DEMAND_VIDEOS_SUPPORTED_COLUMNS = List.of(
            pricingPage.devCraftUIBundlePricingInfo,
            pricingPage.devCraftCompleteBundlePricingInfo,
            pricingPage.devCraftUltimateBundlePricingInfo
    );

    //URLS
    public static final String DEMOS_URL = "https://www.telerik.com/support/demos";
    public static final String CHECKBOXES_URL = "http://the-internet.herokuapp.com/checkboxes";
    public static final String DROPDOWN_URL = "http://the-internet.herokuapp.com/dropdown";
    public static final String TEXTBOX_URL = "https://demoqa.com/text-box";
    public static final String DEMOQA_BOOKS_URL = "https://demoqa.com/books";

    //Kendo Prices
    public static final int KENDO_REACT_PRICE = 999;
    public static final int KENDO_UI_PRICE = 1149;


    public static final String DROPDOWN_OPTION = "Option 2";

    // Forum Constants
    public static final String FULL_NAME = "John Doe";
    public static final String EMAIL = "johndoe@example.com";
    public static final String CURRENT_ADDRESS = "123 Current St.";
    public static final String PERMANENT_ADDRESS = "456 Permanent Ave.";

    public static final int TERM_NUM = 1;
    public static final int QUANTITY_NUM = 6;

    //Demos
    public static final String EXPECTED_COLOR = "rgba(40, 46, 137, 0.75)";
    public static final String EXPECTED_OVERLAY_TEXT = "UI for Vue demos";


    public static final String[][] SECTION_LINKS = {
            {"#web", "Web"},
            {"#desktop", "Desktop"},
            {"#mobile", "Mobile"},
            {"#reporting", "Reporting"},
            {"#testing", "Testing & Mocking"},
            {"#debugging", "Debugging"},
            {"#conversational-ui", "Conversational UI"},
            {"#sitefinity-cms", "Sitefinity CMS"}
    };

    //books
    public static final String TARGET_PUBLISHER = "O'Reilly Media";
    public static final String TITLE_KEYWORD = "JavaScript";
    public static final int EXPECTED_SIZE = 10; // Failed case
    public static final String EXPECTED_FIRST_BOOK_TITLE = "Git Pocket Guide";


    public static final String PRICE_NUM = "1499";
}
