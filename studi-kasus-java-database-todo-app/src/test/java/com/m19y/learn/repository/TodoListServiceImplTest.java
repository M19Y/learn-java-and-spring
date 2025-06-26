package com.m19y.learn.repository;

import com.m19y.learn.entity.Todo;
import com.m19y.learn.util.DatabaseUtil;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.*;

import static com.m19y.learn.util.QueryUtil.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TodoListServiceImplTest {

  private HikariDataSource dataSource;
  private TodoListRepository repository;


  @BeforeAll
  void setUp() {
    dataSource = DatabaseUtil.getHikariDataSource();
    repository = new TodoListRepositoryImpl(dataSource);
    deleteAllTodo();
  }

  @AfterAll
  void tearDown() {
    dataSource.close();
  }

  @BeforeEach
  void cleanDb() {
    deleteAllTodo();
  }

  @Test
  void testAddTodo() {
    Todo todo = new Todo();
    todo.setTodo("Learn Database");

    Assertions.assertTrue(findByNameIfExists("Learn Database"));
    repository.add(todo);
  }

  @Test
  void testSuccessRemoveTodo() {
    // tambah dulu todonya minimal 1
    Todo todo = new Todo();
    todo.setTodo("Eat");
    repository.add(todo);

    Integer id = findByNameAndGetId("Eat");

    // id harus tidak sama dengan 0
    Assertions.assertNotEquals(0, id);

    // hapus todonya
    Assertions.assertTrue(repository.remove(id));
  }

  @Test
  void testFailedRemoveTodo() {
    Assertions.assertFalse(repository.remove(0));
  }


  @Test
  void getAllTest() {

    for (int i = 0; i < 10; i++) {
      Todo todo = new Todo();
      todo.setTodo("Eat" + i);
      repository.add(todo);
    }
    Todo[] todos = repository.getAll();
    Assertions.assertEquals(10, todos.length);

  }
}
