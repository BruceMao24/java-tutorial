package org.example.controller;

import org.example.entity.Book;
import org.example.service.BookService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 图书 CRUD 接口（RESTful 风格：同一个 /books 路径，用不同 HTTP 方法区分操作）
 */
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /** 查询全部：GET /books */
    @GetMapping
    public List<Book> list() {
        return bookService.list();
    }

    /** 查询单个：GET /books/1 */
    @GetMapping("/{id}")
    public Book getById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    /** 新增：POST /books  Body: {"title":"...", "author":"...", "price":39.5} */
    @PostMapping
    public Map<String, Object> create(@Validated @RequestBody Book book) {
        bookService.save(book);
        return Map.of("code", 0, "message", "创建成功", "id", book.getId());
    }

    /** 更新：PUT /books/1  Body: {"title":"...", "price":45} */
    @PutMapping("/{id}")
    public Map<String, Object> update(@PathVariable Long id, @Validated @RequestBody Book book) {
        book.setId(id);
        boolean ok = bookService.updateById(book);
        return Map.of("code", 0, "message", ok ? "更新成功" : "记录不存在");
    }

    /** 删除：DELETE /books/1 */
    @DeleteMapping("/{id}")
    public Map<String, Object> delete(@PathVariable Long id) {
        boolean ok = bookService.removeById(id);
        return Map.of("code", 0, "message", ok ? "删除成功" : "记录不存在");
    }
}
