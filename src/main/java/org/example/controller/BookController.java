package org.example.controller;

import org.example.common.ErrorCode;
import org.example.common.Result;
import org.example.dto.BookCreateDTO;
import org.example.dto.BookUpdateDTO;
import org.example.entity.Book;
import org.example.service.BookService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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

    /**
     * 新增：POST /books  Body: {"title":"...", "author":"...", "price":39.5, "categoryCode":"tech"}
     * categoryCode 不入 book 表,仅作业务参数示例(真实场景可能用于校验、关联其他表等)
     */
    @PostMapping
    public Result<Long> create(@Validated @RequestBody BookCreateDTO dto) {
        // DTO → Entity:拷贝同名属性(title/author/price),categoryCode 不入库
        Book book = new Book();
        BeanUtils.copyProperties(dto, book);

        bookService.save(book);
        log.info("新增图书: {}, 作者: {}, 分类: {}", book.getTitle(), book.getAuthor(), dto.getCategoryCode());
        return Result.ok(book.getId());
    }

    /**
     * 更新：PUT /books/1  Body: {"title":"...", "price":45}
     * id 由路径参数提供,防止前端篡改
     */
    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable Long id, @Validated @RequestBody BookUpdateDTO dto) {
        Book book = new Book();
        BeanUtils.copyProperties(dto, book);
        book.setId(id);  // id 从路径来,不从 body 来

        boolean ok = bookService.updateById(book);
        return ok ? Result.ok() : Result.error(ErrorCode.BOOK_NOT_FOUND);
    }

    /** 删除：DELETE /books/1 */
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        boolean ok = bookService.removeById(id);
        log.info("删除图书 id={}, 结果={}", id, ok);
        return ok ? Result.ok() : Result.error(ErrorCode.BOOK_NOT_FOUND);
    }

    /** 事务实验（无事务）：POST /books/tx-demo/no-tx —— 失败后书 A 会留在库里 */
    @PostMapping("/tx-demo/no-tx")
    public Result<Void> txDemoNoTx() {
        bookService.addPairWithoutTx();
        return Result.ok();
    }

    /** 事务实验（有事务）：POST /books/tx-demo/with-tx —— 失败后整体回滚 */
    @PostMapping("/tx-demo/with-tx")
    public Result<Void> txDemoWithTx() {
        bookService.addPairWithTx();
        return Result.ok();
    }
}
