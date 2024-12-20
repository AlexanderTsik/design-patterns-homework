package ge.tbc.testautomation.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import ge.tbc.testautomation.pages.BookPage;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;
import static ge.tbc.testautomation.data.Constants.*;
import io.qameta.allure.*;

public class BookSteps {
    private final BookPage bookPage = new BookPage();

    @Step("Open books page")
    public BookSteps openBooksPage() {
        open(DEMOQA_BOOKS_URL);
        return this;
    }

    @Step("Validate books by publisher: {publisher} and title: {title}")
    public BookSteps validateBooksByPublisherAndTitle(String publisher, String title) {
        var filteredBooks = bookPage.books.filterBy(text(publisher)).filterBy(text(title));

        // Validate each book's image
        filteredBooks.forEach(book -> {
            String imgSrc = book.$("img").getAttribute("src");
            if (imgSrc == null || imgSrc.isEmpty()) {
                throw new AssertionError("Book image should not be empty");
            }
        });

        // Print the titles of books for debugging
        filteredBooks.forEach(book -> System.out.println("Book found: " + book.$(".rt-td:nth-child(2)").text()));

        // Assert books exist
        if (filteredBooks.isEmpty()) {
            throw new AssertionError("No books found with the specified criteria.");
        }
        return this; // Enables method chaining
    }
    @Step("Find books by publisher: {publisher} and title keyword: {titleKeyword}")
    public ElementsCollection findBooksByPublisherAndTitle(String publisher, String titleKeyword) {
        return bookPage.books
                .filterBy(Condition.text(publisher))
                .filterBy(Condition.text(titleKeyword));
    }

    @Step("Validate books with soft assertions for publisher: {publisher}, title keyword: {titleKeyword}, expected size: {expectedSize}, and expected first book title: {expectedFirstBookTitle}")
    public void validateBooksWithSoftAssertions(String publisher, String titleKeyword, int expectedSize, String expectedFirstBookTitle) {
        SoftAssert softAssert = new SoftAssert();

        // Find books matching criteria
        ElementsCollection filteredBooks = findBooksByPublisherAndTitle(publisher, titleKeyword);

        // Validate size (failed case)
        softAssert.assertEquals(filteredBooks.size(), expectedSize, "Expected size of books list is incorrect!");

        // Validate first book title (success case)
        if (!filteredBooks.isEmpty()) {
            String actualFirstTitle = bookPage.books.first().$(bookPage.titleColumn).getText();
            softAssert.assertEquals(actualFirstTitle, expectedFirstBookTitle, "First book's title does not match!");
        } else {
            softAssert.fail("No books found for given criteria.");
        }
        softAssert.assertAll();
    }
}


