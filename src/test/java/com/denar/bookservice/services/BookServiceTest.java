package com.denar.bookservice.services;

import com.denar.bookservice.dto.BookDto;
import com.denar.bookservice.repositories.BookRepository;
import com.denar.bookservice.repositories.entityes.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    private final BookRepository repository = Mockito.mock(BookRepository.class);
    private final BookService service = new BookService(repository, new ModelMapper());

    @Test
    void readAll_WhenCalledAllBooks_ThenReturnListBooks() {
        // GIVEN
        List<BookDto> expectedBooks = getBooksDto();
        List<Book> books = getBooksEntity();
        doReturn(books).when(repository).findAll();

        // WHEN
        List<BookDto> actualBooks = service.readAll();

        // THEN
        verify(repository).findAll();

        assertNotNull(actualBooks);
        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    void read_WhenPassedBookId_ThenReturnBookByBookId() {
        // GIVEN
        BookDto expectedBook = getBooksDto().get(1);
        Book book = getBooksEntity().get(1);
        doReturn(Optional.of(book)).when(repository).findById(anyLong());

        // WHEN
        BookDto actualBook = service.read(1L);

        // THEN
        verify(repository).findById(any(Long.class));

        assertNotNull(actualBook);
        assertEquals(expectedBook, actualBook);
    }

    @Test
    void readByName_WhenPassedBookName_ThenReturnBooksListByTitle() {
        // GIVEN
        List<BookDto> expectedBooks = List.of(getBooksDto().get(0));
        List<Book> books = List.of(getBooksEntity().get(0));
        String bookName = "МУМУ";
        doReturn(books).when(repository).findByName(bookName);

        // WHEN
        List<BookDto> actualBooks = service.readByName(bookName);

        // THEN
        verify(repository).findByName(bookName);

        assertNotNull(actualBooks);
        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    void readByAuthor_WhenPassedAuthorName_ThenReturnBookListByAuthorName() {
        // GIVEN
        List<BookDto> expectedBooks = List.of(getBooksDto().get(0));
        List<Book> books = List.of(getBooksEntity().get(0));
        String authorName = "Иван Тургенев";
        doReturn(books).when(repository).findByAuthor(authorName);

        // WHEN
        List<BookDto> actualBooks = service.readByAuthor(authorName);

        // THEN
        verify(repository).findByAuthor(authorName);

        assertNotNull(actualBooks);
        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    void readByAuthorId_WhenPassedAuthorId_ThenReturnBookListByAuthorId() {
        // GIVEN
        List<BookDto> expectedBooks = List.of(getBooksDto().get(0));
        List<Book> books = List.of(getBooksEntity().get(0));
        Long authorId = 1L;
        doReturn(books).when(repository).findByAuthorId(authorId);

        // WHEN
        List<BookDto> actualBooks = service.readByAuthorId(authorId);

        // THEN
        verify(repository).findByAuthorId(authorId);

        assertNotNull(actualBooks);
        assertEquals(expectedBooks, actualBooks);
    }

    private static List<Book> getBooksEntity() {
        return List.of(
                new Book(1L, "МУМУ", "Description МУМУ", 1L),
                new Book(2L, "Война и мир", "Description Война и мир", 2L),
                new Book(3L, "Зов", "Description Зов", 3L)
        );
    }

    private static List<BookDto> getBooksDto() {
        return List.of(
                new BookDto(1L, "МУМУ", "Description МУМУ", 1L),
                new BookDto(2L, "Война и мир", "Description Война и мир", 2L),
                new BookDto(3L, "Зов", "Description Зов", 3L)
        );
    }
}