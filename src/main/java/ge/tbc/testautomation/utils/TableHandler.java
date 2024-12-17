package ge.tbc.testautomation.utils;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class TableHandler {
    private final SelenideElement table = $(".PricingTable");

    public ElementsCollection getRows() {
        return table.$$("tbody tr");
    }

    public SelenideElement getRowByFirstColumnText(String text) {
        return getRows().findBy(text(text));
    }

    public ElementsCollection getCellsInRow(SelenideElement row) {
        return row.$$("td");
    }

    public SelenideElement getCellInRow(SelenideElement row, int columnIndex) {
        return getCellsInRow(row).get(columnIndex);
    }

    public boolean doesCellContainDot(SelenideElement cell) {
        return cell.$("span.dot").exists();
    }

    public String getCellText(SelenideElement cell) {
        return cell.getText().trim();
    }
}

