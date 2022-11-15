package com.denar.bookservice.controllers;

import com.denar.bookservice.dto.BookDto;
import com.denar.bookservice.repositories.entityes.Author;
import com.denar.bookservice.services.BookService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class BookControllerTest {
    @Mock
    private BookService bookService;
    @InjectMocks
    private BookController controller;
    private static List<BookDto> booksDto;


    @BeforeAll
    public static void init() {
        Author author1 = new Author(1L, "Джорж Оруэлл");
        Author author2 = new Author(2L, "Оскар Уайльд");
        Author author3 = new Author(3L, "Брэм Стокер");

        booksDto = List.of(
                new BookDto(1L, "1984. Скотный двор", "Description 1984. Скотный двор", author1),
                new BookDto(2L, "Портрет Дориана Грея", "Description Портрет Дориана Грея", author2),
                new BookDto(3L, "Дракула", "Description Дракула",  author3)
        );
    }

    @Test
    void getAllBooks_WhenCalledAllBooks_ThenReturnBooksAndStatusOK() {
        // GIVEN
        ResponseEntity<List<BookDto>> expectedResponse = new ResponseEntity<>(booksDto, HttpStatus.OK);
        doReturn(booksDto).when(bookService).getAllBooks();

        // WHEN
        ResponseEntity<List<BookDto>> actualResponse = controller.getAllBooks();

        // THEN
        verify(bookService).getAllBooks();

        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getBookId_WhenPassedBookId_ThenReturnBookByBookIdAndStatusOK() {
        // GIVEN
        ResponseEntity<BookDto> expectedResponse = new ResponseEntity<>(booksDto.get(0), HttpStatus.OK);
        BookDto book = booksDto.get(0);
        doReturn(book).when(bookService).getBook(1L);

        // WHEN
        ResponseEntity<BookDto> actualResponse = controller.getBookById(1L);

        // THEN
        verify(bookService).getBook(anyLong());

        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getBookId_WhenPassedWrongBookId_ThenReturnStatusNotFound() {
        // GIVEN
        ResponseEntity<BookDto> expectedResponse = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        doReturn(null).when(bookService).getBook(100L);

        // WHEN
        ResponseEntity<BookDto> actualResponse = controller.getBookById(100L);

        // THEN
        verify(bookService).getBook(anyLong());

        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getBooksByName_WhenPassedBookName_ThenReturnBooksListByTitleAndStatusOK() {
        // GIVEN
        ResponseEntity<List<BookDto>> expectedResponse = new ResponseEntity<>(List.of(booksDto.get(0)), HttpStatus.OK);
        String bookName = "МУМУ";
        doReturn(List.of(booksDto.get(0))).when(bookService).getBooks(bookName);

        // WHEN
        ResponseEntity<List<BookDto>> actualResponse = controller.getBooksByName(bookName);

        // THEN
        verify(bookService).getBooks(anyString());

        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getBooksByAuthor_WhenPassedAuthorName_ThenReturnBookListByAuthorNameAndStatusOK() {
        // GIVEN
        ResponseEntity<List<BookDto>> expectedResponse = new ResponseEntity<>(List.of(booksDto.get(0)), HttpStatus.OK);
        String authorName = "Иван Тургенев";
        doReturn(List.of(booksDto.get(0))).when(bookService).getBookByAuthor(authorName);

        // WHEN
        ResponseEntity<List<BookDto>> actualResponse = controller.getBooksByAuthor(authorName);

        // THEN
        verify(bookService).getBookByAuthor(anyString());

        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getBooksByAuthorId_WhenPassedAuthorId_ThenReturnBookListByAuthorIdAndStatusOK() {
        // GIVEN
        ResponseEntity<List<BookDto>> expectedResponse = new ResponseEntity<>(List.of(booksDto.get(0)), HttpStatus.OK);
        Long authorId = 1L;
        doReturn(List.of(booksDto.get(0))).when(bookService).getBookByAuthorId(authorId);

        // WHEN
        ResponseEntity<List<BookDto>> actualResponse = controller.getBooksByAuthorId(authorId);

        // THEN
        verify(bookService).getBookByAuthorId(anyLong());

        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
    }
}