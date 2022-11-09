package com.denar.bookservice.controllers;

import com.denar.bookservice.dto.BookDto;
import com.denar.bookservice.services.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Test
    void getAllBooks_WhenCalledAllBooks_ThenReturnBooksAndStatusOK() {
        // GIVEN
        ResponseEntity<List<BookDto>> expectedResponse = new ResponseEntity<>(getBooksDto(), HttpStatus.OK);
        doReturn(getBooksDto()).when(bookService).readAll();

        // WHEN
        ResponseEntity<List<BookDto>> actualResponse = controller.getAllBooks();

        // THEN
        verify(bookService).readAll();

        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getBookId_WhenPassedBookId_ThenReturnBookByBookIdAndStatusOK() {
        // GIVEN
        ResponseEntity<BookDto> expectedResponse = new ResponseEntity<>(getBooksDto().get(0), HttpStatus.OK);
        BookDto book = getBooksDto().get(0);
        doReturn(book).when(bookService).read(1L);

        // WHEN
        ResponseEntity<BookDto> actualResponse = controller.getBookId(1L);

        // THEN
        verify(bookService).read(anyLong());

        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getBooksByName_WhenPassedBookName_ThenReturnBooksListByTitleAndStatusOK() {
        // GIVEN
        ResponseEntity<List<BookDto>> expectedResponse = new ResponseEntity<>(List.of(getBooksDto().get(0)), HttpStatus.OK);
        String bookName = "МУМУ";
        doReturn(List.of(getBooksDto().get(0))).when(bookService).readByName(bookName);

        // WHEN
        ResponseEntity<List<BookDto>> actualResponse = controller.getBooksByName(bookName);

        // THEN
        verify(bookService).readByName(anyString());

        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getBooksByAuthor_WhenPassedAuthorName_ThenReturnBookListByAuthorNameAndStatusOK() {
        // GIVEN
        ResponseEntity<List<BookDto>> expectedResponse = new ResponseEntity<>(List.of(getBooksDto().get(0)), HttpStatus.OK);
        String authorName = "Иван Тургенев";
        doReturn(List.of(getBooksDto().get(0))).when(bookService).readByAuthor(authorName);

        // WHEN
        ResponseEntity<List<BookDto>> actualResponse = controller.getBooksByAuthor(authorName);

        // THEN
        verify(bookService).readByAuthor(anyString());

        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getBooksByAuthorId_WhenPassedAuthorId_ThenReturnBookListByAuthorIdAndStatusOK() {
        // GIVEN
        ResponseEntity<List<BookDto>> expectedResponse = new ResponseEntity<>(List.of(getBooksDto().get(0)), HttpStatus.OK);
        Long authorId = 1L;
        doReturn(List.of(getBooksDto().get(0))).when(bookService).readByAuthorId(authorId);

        // WHEN
        ResponseEntity<List<BookDto>> actualResponse = controller.getBooksByAuthorId(authorId);

        // THEN
        verify(bookService).readByAuthorId(anyLong());

        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
    }

    private static List<BookDto> getBooksDto() {
        return new ArrayList<>(
                Arrays.asList(
                        new BookDto(1L, "МУМУ", "Description МУМУ", 1L),
                        new BookDto(2L, "Война и мир", "Description Война и мир", 2L),
                        new BookDto(3L, "Зов", "Description Зов", 3L)
                )
        );
    }
}