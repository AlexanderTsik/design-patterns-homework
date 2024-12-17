package ge.tbc.testautomation.pages;
import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.*;

public class BookPage {
    public ElementsCollection books = $$(".rt-tr-group"); // All books in the table
    public String titleColumn = ".rt-td:nth-child(2)";
}
