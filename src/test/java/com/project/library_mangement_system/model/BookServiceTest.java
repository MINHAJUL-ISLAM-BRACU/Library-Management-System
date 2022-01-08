package com.project.library_mangement_system.model;

import com.project.library_mangement_system.repository.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.verify;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;
    @Mock
    private BookService bookService;
    private Book book;
    private Category category;
    private List<Long> count;
    private List<Book> bookList;

    @Before
    public void setUp() {
        this.bookService=new BookService(this.bookRepository);
        category= new Category("Childeren Stroy","Child");
        book=new Book(category,"Bikal R Golpo","Child","Minhjaul");
        book=bookService.save(book);
    }

    @Test
    public void testGetTotalCount() {
        Long i= bookService.getTotalCount();
        verify(bookRepository).count();
        assertNotNull(i);
    }

    @Test
    public void testGetTotalIssuedBooks() {
        Long i= bookService.getTotalIssuedBooks();
        verify(bookRepository).countByStatus(Constants.BOOK_STATUS_ISSUED);
        assertNotNull(i);
    }

    @Test
    public void testGetAll() {
        bookList= bookService.getAll();
        verify(bookRepository).findAll();
        assertNotNull(bookList);
    }

    @Test
    public void testGetByTag() {
        Book book=bookService.getByTag("Min");
        verify(bookRepository).findByTag("Min");
        assertNull(book);
    }

    @Test
    public void testGet() {
        bookList=bookService.get(count);
        verify(bookRepository).findAllById(count);
        assertNotNull(bookList);
    }

    @Test
    public void testGetByCategory() {
        bookList=bookService.getByCategory(category);
        verify(bookRepository).findByCategory(category);
        assertNotNull(bookList);
    }

    @Test
    public void testGeTotalIssuedBooks() {
        bookList=bookService.geAvailabletByCategory(category);
        verify(bookRepository).findByCategoryAndStatus(category, Constants.BOOK_STATUS_AVAILABLE);
        assertNotNull(bookList);
    }

    @Test
    public void testDelete() {
        bookService.delete(book);
        verify(bookRepository).delete(book);
        assertNull(book);
    }

    @Test
    public void testTestDelete() {
        bookService.delete(1L);
        verify(bookRepository).deleteById(1L);
        assertNull(book);
    }

    @Test
    public void testAddNew() {
        Book book=new Book(category,"Ajkaer Mojar Golpo","Child","Minhajul");
        bookService.addNew(book);
        verify(bookRepository).save(book);
        assertNotNull(book.getTitle());
    }

    @Test
    public void testSave() {
        Book book=new Book(category,"Ajkaer Mojar Golpo","Child","Minhajul");
        bookService.save(book);
        verify(bookRepository).save(book);
        assertNotNull(book.getTitle());
    }

}