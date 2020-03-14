package com.futao.springboot.learn.jpa.controller;

import com.futao.springboot.learn.jpa.model.Book;
import com.futao.springboot.learn.jpa.service.BookService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @author futao
 * Created on 2020/3/13.
 */
public class BookControllerTest {
    @Mock
    BookService bookService;
    @InjectMocks
    BookController bookController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAdd() throws Exception {
        when(bookService.add(any())).thenReturn(new Book(0, "name", "author", null, "desc", new GregorianCalendar(2020, Calendar.MARCH, 13, 19, 8).getTime(), new GregorianCalendar(2020, Calendar.MARCH, 13, 19, 8).getTime()));

        Book result = bookController.add(new Book(0, "name", "author", null, "desc", new GregorianCalendar(2020, Calendar.MARCH, 13, 19, 8).getTime(), new GregorianCalendar(2020, Calendar.MARCH, 13, 19, 8).getTime()));
        Assert.assertEquals(new Book(0, "name", "author", null, "desc", new GregorianCalendar(2020, Calendar.MARCH, 13, 19, 8).getTime(), new GregorianCalendar(2020, Calendar.MARCH, 13, 19, 8).getTime()), result);
    }

    @Test
    public void testUpdate() throws Exception {
        when(bookService.update(any())).thenReturn(new Book(0, "name", "author", null, "desc", new GregorianCalendar(2020, Calendar.MARCH, 13, 19, 8).getTime(), new GregorianCalendar(2020, Calendar.MARCH, 13, 19, 8).getTime()));

        Book result = bookController.update(new Book(0, "name", "author", null, "desc", new GregorianCalendar(2020, Calendar.MARCH, 13, 19, 8).getTime(), new GregorianCalendar(2020, Calendar.MARCH, 13, 19, 8).getTime()));
        Assert.assertEquals(new Book(0, "name", "author", null, "desc", new GregorianCalendar(2020, Calendar.MARCH, 13, 19, 8).getTime(), new GregorianCalendar(2020, Calendar.MARCH, 13, 19, 8).getTime()), result);
    }

    @Test
    public void testDelete() throws Exception {
        bookController.delete(0);
    }

    @Test
    public void testById() throws Exception {
        when(bookService.byId(anyInt())).thenReturn(null);

        Book result = bookController.byId(0);
        Assert.assertEquals(new Book(0, "name", "author", null, "desc", new GregorianCalendar(2020, Calendar.MARCH, 13, 19, 8).getTime(), new GregorianCalendar(2020, Calendar.MARCH, 13, 19, 8).getTime()), result);
    }

    @Test
    public void testList() throws Exception {
        when(bookService.list()).thenReturn(Arrays.<Book>asList(new Book(0, "name", "author", null, "desc", new GregorianCalendar(2020, Calendar.MARCH, 13, 19, 8).getTime(), new GregorianCalendar(2020, Calendar.MARCH, 13, 19, 8).getTime())));

        List<Book> result = bookController.list();
        Assert.assertEquals(Arrays.<Book>asList(new Book(0, "name", "author", null, "desc", new GregorianCalendar(2020, Calendar.MARCH, 13, 19, 8).getTime(), new GregorianCalendar(2020, Calendar.MARCH, 13, 19, 8).getTime())), result);
    }

    @Test
    public void testGetBookByIdAndAuthor() throws Exception {
        when(bookService.getBookByIdAndAuthor(anyInt(), anyString())).thenReturn(Arrays.<Book>asList(new Book(0, "name", "author", null, "desc", new GregorianCalendar(2020, Calendar.MARCH, 13, 19, 8).getTime(), new GregorianCalendar(2020, Calendar.MARCH, 13, 19, 8).getTime())));

        List<Book> result = bookController.getBookByIdAndAuthor(0, "author");
        Assert.assertEquals(Arrays.<Book>asList(new Book(0, "name", "author", null, "desc", new GregorianCalendar(2020, Calendar.MARCH, 13, 19, 8).getTime(), new GregorianCalendar(2020, Calendar.MARCH, 13, 19, 8).getTime())), result);
    }

    @Test
    public void testGetBooksByNameStartingWith() throws Exception {
        when(bookService.getBooksByNameStartingWith(anyString())).thenReturn(Arrays.<Book>asList(new Book(0, "name", "author", null, "desc", new GregorianCalendar(2020, Calendar.MARCH, 13, 19, 8).getTime(), new GregorianCalendar(2020, Calendar.MARCH, 13, 19, 8).getTime())));

        List<Book> result = bookController.getBooksByNameStartingWith("name");
        Assert.assertEquals(Arrays.<Book>asList(new Book(0, "name", "author", null, "desc", new GregorianCalendar(2020, Calendar.MARCH, 13, 19, 8).getTime(), new GregorianCalendar(2020, Calendar.MARCH, 13, 19, 8).getTime())), result);
    }

    @Test
    public void testGetBooksByPriceGreaterThan() throws Exception {
        when(bookService.getBooksByPriceGreaterThan(anyFloat())).thenReturn(Arrays.<Book>asList(new Book(0, "name", "author", null, "desc", new GregorianCalendar(2020, Calendar.MARCH, 13, 19, 8).getTime(), new GregorianCalendar(2020, Calendar.MARCH, 13, 19, 8).getTime())));

        List<Book> result = bookController.getBooksByPriceGreaterThan(0f);
        Assert.assertEquals(Arrays.<Book>asList(new Book(0, "name", "author", null, "desc", new GregorianCalendar(2020, Calendar.MARCH, 13, 19, 8).getTime(), new GregorianCalendar(2020, Calendar.MARCH, 13, 19, 8).getTime())), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme