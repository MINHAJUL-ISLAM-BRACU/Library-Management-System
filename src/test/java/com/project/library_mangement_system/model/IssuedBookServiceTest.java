package com.project.library_mangement_system.model;

import com.project.library_mangement_system.repository.IssuedBookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
@RunWith(MockitoJUnitRunner.class)
public class IssuedBookServiceTest {
    @Mock
    private IssuedBookRepository issuedBookRepository;
    @Mock
    private IssuedBookService issuedBookService;
    private Book book;
    private Category category;
    private Issue issue;
    private IssuedBook issuedBook;
    private List<IssuedBook> issuedBookList;

    @Before
    public void setUp() {
        this.issuedBookService=new IssuedBookService(this.issuedBookRepository);
        category= new Category("Children Story","Child");
        book=new Book(category,"Bikal R Golpo","Child","Minhajul");
        Date d= new Date();
        issue=new Issue(d);
        issuedBook=new IssuedBook(book,issue);
    }

    @Test
    public void testGetAll() {
        issuedBookList=issuedBookService.getAll();
        verify(issuedBookRepository).findAll();
        assertNotNull(issuedBookList);
    }

    @Test
    public void testGetCountByBook() {
        Long i=issuedBookService.getCountByBook(book);
        verify(issuedBookRepository).countByBookAndReturned(book, Constants.BOOK_NOT_RETURNED);
        assertNotNull(i);
    }

    @Test
    public void testSave() {
        category= new Category("Uponash","story");
        book=new Book(category,"Ajkaer Mojar Bikal","Story","Hafiz");
        Date d= new Date();
        issue=new Issue(d);
        IssuedBook issuedBook=new IssuedBook(book,issue);
        issuedBookService.save(issuedBook);
        verify(issuedBookRepository).save(issuedBook);
        assertNotNull(issuedBook);
    }

    @Test
    public void testAddNew() {
        category= new Category("Uponash","story");
        book=new Book(category,"Ajkaer Mojar Bikal","Story","Hafiz");
        Date d= new Date();
        issue=new Issue(d);
        IssuedBook issuedBook=new IssuedBook(book,issue);
        issuedBookService.addNew(issuedBook);
        verify(issuedBookRepository).save(issuedBook);
        assertNotNull(issuedBook);
    }
}