package com.denar.bookservice.services;

import com.denar.bookservice.dto.BookDto;
import com.denar.bookservice.repositories.BookRepository;
import com.denar.bookservice.repositories.entityes.Author;
import com.denar.bookservice.repositories.entityes.Book;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    private final BookRepository repository = Mockito.mock(BookRepository.class);
    private final BookService service = new BookService(repository, new BookConvertor(new ModelMapper()));

    private static List<Book> books;
    private static List<BookDto> booksDto;


    @BeforeAll
    public static void init() {
        Author author1 = new Author(1L, "Джорж Оруэлл");
        Author author2 = new Author(2L, "Оскар Уайльд");
        Author author3 = new Author(3L, "Брэм Стокер");

        books = List.of(
                new Book(1L, "1984. Скотный двор", "Description 1984. Скотный двор", author1),
                new Book(2L, "Портрет Дориана Грея", "Description Портрет Дориана Грея", author2),
                new Book(3L, "Дракула", "Description Дракула",  author3)
        );

        booksDto = List.of(
                new BookDto(1L, "1984. Скотный двор", "Description 1984. Скотный двор", author1),
                new BookDto(2L, "Портрет Дориана Грея", "Description Портрет Дориана Грея", author2),
                new BookDto(3L, "Дракула", "Description Дракула",  author3)
        );
    }

    @Test
    void readAll_WhenCalledAllBooks_ThenReturnListBooks() {
        // GIVEN
        List<BookDto> expectedBooks = booksDto;
        doReturn(books).when(repository).findAll();

        // WHEN
        List<BookDto> actualBooks = service.getAllBooks();

        // THEN
        verify(repository).findAll();

        assertNotNull(actualBooks);
        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    void read_WhenPassedBookId_ThenReturnBookByBookId() {
        // GIVEN
        BookDto expectedBook = booksDto.get(0);
        Book book = books.get(0);
        doReturn(Optional.of(book)).when(repository).findById(anyLong());

        // WHEN
        BookDto actualBook = service.getBook(1L);

        // THEN
        verify(repository).findById(any(Long.class));

        assertNotNull(actualBook);
        assertEquals(expectedBook, actualBook);
    }

    // Negative Test
    @Test
    void read_WhenPassedWrongBookId_ThenReturnNull() {
        // GIVEN
        doReturn(Optional.empty()).when(repository).findById(100L);

        // WHEN
        BookDto actualBook = service.getBook(1L);

        // THEN
        verify(repository).findById(any(Long.class));

        assertNull(actualBook);
    }

    @Test
    void readByName_WhenPassedBookName_ThenReturnBooksListByTitle() {
        // GIVEN
        List<BookDto> expectedBooks = List.of(booksDto.get(0));
        List<Book> newBooks = List.of(books.get(0));
        String bookName = "1984. Скотный двор";
        doReturn(newBooks).when(repository).findByName(bookName);

        // WHEN
        List<BookDto> actualBooks = service.getBooks(bookName);

        // THEN
        verify(repository).findByName(bookName);

        assertNotNull(actualBooks);
        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    void readByAuthor_WhenPassedAuthorName_ThenReturnBookListByAuthorName() {
        // GIVEN
        List<BookDto> expectedBooks = List.of(booksDto.get(1));
        List<Book> newBooks = List.of(books.get(1));
        String authorName = "Оскар Уайльд";
        doReturn(newBooks).when(repository).findByAuthor(authorName);

        // WHEN
        List<BookDto> actualBooks = service.getBookByAuthor(authorName);

        // THEN
        verify(repository).findByAuthor(authorName);

        assertNotNull(actualBooks);
        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    void readByAuthorId_WhenPassedAuthorId_ThenReturnBookListByAuthorId() {
        // GIVEN
        List<BookDto> expectedBooks = List.of(booksDto.get(2));
        List<Book> newBooks = List.of(books.get(2));
        Long authorId = 3L;
        doReturn(newBooks).when(repository).findByAuthorId(authorId);

        // WHEN
        List<BookDto> actualBooks = service.getBookByAuthorId(authorId);

        // THEN
        verify(repository).findByAuthorId(authorId);

        assertNotNull(actualBooks);
        assertEquals(expectedBooks, actualBooks);
    }
}