package org.example.service;

import org.example.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * BookService 的集成测试：连测试库，完整走一遍增删改查
 */
@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    void crudFlow() {
        // 新增
        Book book = new Book();
        book.setTitle("测试驱动开发");
        book.setAuthor("Kent Beck");
        book.setPrice(new BigDecimal("66.00"));
        assertTrue(bookService.save(book));
        assertNotNull(book.getId(), "保存后应回填自增主键");

        // 查询
        Book found = bookService.getById(book.getId());
        assertNotNull(found);
        assertEquals("测试驱动开发", found.getTitle());

        // 更新
        found.setPrice(new BigDecimal("55.00"));
        assertTrue(bookService.updateById(found));
        assertEquals(0, new BigDecimal("55.00").compareTo(bookService.getById(book.getId()).getPrice()));

        // 删除
        assertTrue(bookService.removeById(book.getId()));
        assertNull(bookService.getById(book.getId()), "删除后应查不到");
    }
}
