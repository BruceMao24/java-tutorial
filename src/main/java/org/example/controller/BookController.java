package org.example.controller;

import org.example.common.Result;
import org.example.entity.Book;
import org.example.service.BookService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 图书 CRUD 接口（RESTful 风格：同一个 /books 路径，用不同 HTTP 方法区分操作）
 * 所有接口统一返回 Result 结构
 */
@RestController
@RequestMapping("/books")
public class BookController {

    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /** 查询全部：GET /books */
    @GetMapping
    public Result<List<Book>> list() {
        return Result.ok(bookService.list());
    }

    /**
     * 分页 + 条件查询：GET /books/page?pageNum=1&pageSize=5&keyword=Java
     * keyword 可省略，省略时查全部
     */
    @GetMapping("/page")
    public Result<IPage<Book>> page(@RequestParam(defaultValue = "1") int pageNum,
                                    @RequestParam(defaultValue = "5") int pageSize,
                                    @RequestParam(required = false) String keyword) {
        return Result.ok(bookService.pageBooks(pageNum, pageSize, keyword));
    }

    /** 查询单个：GET /books/1 */
    @GetMapping("/{id}")
    public Result<Book> getById(@PathVariable Long id) {
        return Result.ok(bookService.getById(id));
    }

    /** 新增：POST /books  Body: {"title":"...", "author":"...", "price":39.5} */
    @PostMapping
    public Result<Long> create(@Validated @RequestBody Book book) {
        bookService.save(book);
        log.info("新增图书: {}, 作者: {}", book.getTitle(), book.getAuthor());
        return Result.ok(book.getId());
    }

    /** 更新：PUT /books/1  Body: {"title":"...", "price":45} */
    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable Long id, @Validated @RequestBody Book book) {
        book.setId(id);
        boolean ok = bookService.updateById(book);
        return ok ? Result.ok() : Result.error(404, "记录不存在");
    }

    /** 删除：DELETE /books/1 */
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        boolean ok = bookService.removeById(id);
        log.info("删除图书 id={}, 结果={}", id, ok);
        return ok ? Result.ok() : Result.error(404, "记录不存在");
    }
}
