package sk.ness.academy.springboothibernate.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import sk.ness.academy.springboothibernate.dao.BookDao;
import sk.ness.academy.springboothibernate.dto.BookDto;
import sk.ness.academy.springboothibernate.model.Book;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BookServiceImplTest {

  @Mock
  private BookDao bookDao;

  @InjectMocks
  private BookServiceImpl bookService;

  private List<Book> books;

  @Test
  public void contextLoadsBookServiceImpl() throws Exception {
    assertThat(bookService).isNotNull();
  }

  @Test
  public void contextLoadsBookDao() throws Exception {
    assertThat(bookDao).isNotNull();
  }

  @Test
  void testFindAll() {
    Mockito.when(bookDao.findAll()).thenReturn(books);

    final List<BookDto> bookDtos = bookService.findAll();

    assertEquals(2, bookDtos.size());
    assertEquals("X_Book_1", bookDtos.get(0).getName());
    assertEquals("X_Book_2", bookDtos.get(1).getName());
  }

  @Test
  void testFindAllEmpty() {
    Mockito.when(bookDao.findAll()).thenReturn(new ArrayList<>());

    final List<BookDto> bookDtos = bookService.findAll();

    assertEquals(0, bookDtos.size());
  }


  @Test
  void testFindAllNull() {
    Mockito.when(bookDao.findAll()).thenReturn(null);

    final List<BookDto> bookDtos = bookService.findAll();

    Assertions.assertTrue(bookDtos.isEmpty());
  }

  @Test
  void testSave() {
    final Book book1 = new Book();
    book1.setName("Book_9");
    book1.setId(1L);

    bookService.save(book1);

    assertNotNull(book1);
    //assertEquals(book1.getName(), .getValue());

    Mockito.when(bookDao.findAll()).thenReturn(books);
    final List<BookDto> bookDtos = bookService.findAll();

    assertEquals("X_Book_9", bookDtos.get(2).getName());


  }


  @BeforeEach
  private void init() {
    final Book book1 = new Book();
    book1.setName("Book_1");
    book1.setId(1L);


    final Book book2 = new Book();
    book2.setName("Book_2");
    book2.setId(2L);

    books = new ArrayList<>();
    books.add(book1);
    books.add(book2);

  }

}