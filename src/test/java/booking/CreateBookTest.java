package booking;

import io.restassured.response.Response;
import org.booking.model.Book;
import org.booking.BookApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreateBookTest {

  @BeforeEach
  void setup() {
    BookApi.deleteAllBooks();
  }

  @Test
  void shouldThrowErrorIfTitleIsEmpty() {
    // Arrange
    Book book = Book.builder()
            .title("")
            .author("Test")
            .build();

    // Act
    Response addBookResponse = BookApi.addBook(book);

    // Assert
    Assertions.assertAll(
            "Verify Status and Title error message",
            () -> Assertions.assertEquals(400, addBookResponse.statusCode()),
            () -> Assertions.assertEquals("title cannot be empty", addBookResponse.jsonPath().getString("error"))
    );
  }

  @Test
  void shouldThrowErrorIfAuthorIsEmpty() {
    // Arrange
    Book book = Book.builder()
            .title("Test")
            .author("")
            .build();

    // Act
    Response addBookResponse = BookApi.addBook(book);

    // Assert
    Assertions.assertAll(
            "Verify Status and Author error message",
            () -> Assertions.assertEquals(400, addBookResponse.statusCode()),
            () -> Assertions.assertEquals("author cannot be empty", addBookResponse.jsonPath().getString("error"))
    );
  }

  @Test
  void shouldBeAbleToAddNewBook() {
    // Arrange
    Book book = Book.builder()
            .title("Core Java")
            .author("John")
            .build();

    // Act
    Response addBookResponse = BookApi.addBook(book);

    // Assert
    Assertions.assertAll(
            "Verify successful book creation",
            () -> Assertions.assertEquals(200, addBookResponse.statusCode()),
            () -> Assertions.assertNotNull( addBookResponse.jsonPath().getString("id")),
            () -> Assertions.assertEquals(book.getTitle(), addBookResponse.jsonPath().getString("title")),
            () -> Assertions.assertEquals(book.getAuthor(), addBookResponse.jsonPath().getString("author"))
    );
  }

  @Test
  void shouldThrowErrorIfDuplicateBookIsAdded() {
    // Arrange
    Book book = Book.builder()
            .title("Core Java")
            .author("john")
            .build();
    Book duplicateBook = Book.builder()
            .title("Core Java")
            .author("John")
            .build();
    BookApi.addBook(book);

    // Act
    Response addBookResponse = BookApi.addBook(duplicateBook);

    // Assert
    Assertions.assertAll(
            "Verify error message for duplicate book",
            () -> Assertions.assertEquals(400, addBookResponse.statusCode()),
            () -> Assertions.assertEquals("Book  with title \"Core Java\" already exists", addBookResponse.jsonPath().getString("error"))
    );
  }


}
