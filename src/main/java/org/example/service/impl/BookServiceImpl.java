package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.repository.AbstractRepository;
import org.example.entity.Book;
import org.example.mapper.BookMapper;
import org.example.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * 图书业务实现
 * AbstractRepository 自带 save/getById/updateById/removeById/list 等常用方法，
 * 只需提供 getBaseMapper() 告诉它操作哪张表
 */
@Service
public class BookServiceImpl extends AbstractRepository<BookMapper, Book> implements BookService {

    private final BookMapper bookMapper;

    public BookServiceImpl(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public BookMapper getBaseMapper() {
        return bookMapper;
    }

    @Override
    public Book getById(Serializable id) {
        Book book = bookMapper.selectById(id);
        if (book != null) {
            // 非表字段：查出来之后在内存里拼好，随对象返回给前端
            book.setDisplayLabel(book.getAuthor() + " · " + book.getTitle());
        }
        return book;
    }

    @Override
    public IPage<Book> pageBooks(int pageNum, int pageSize, String keyword) {
        // QueryWrapper：用 Java 方法拼 SQL 条件，字段名直接写字符串
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        if (keyword != null && !keyword.isBlank()) {
            // WHERE title LIKE '%kw%' OR author LIKE '%kw%'
            wrapper.like("title", keyword).or().like("author", keyword);
        }
        wrapper.orderByDesc("id");

        // Page 对象既是入参（第几页/每页几条）也是出参（总条数/当前页数据）
        return bookMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
    }

    @Override
    public void addPairWithoutTx() {
        // 第一步：插入书 A（没有事务，这条 SQL 立即提交）
        save(demoBook("TX-NOTX"));
        // 第二步：模拟后续操作失败（如下游接口报错、唯一约束冲突）
        throw new RuntimeException("模拟第二步失败（无事务）");
    }

    /**
     * @Transactional：方法内的所有数据库操作在同一个事务里
     * 方法抛出异常 → Spring 自动回滚（撤销已执行的 SQL）
     * 方法正常返回 → 自动提交
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addPairWithTx() {
        save(demoBook("TX-WITHTX"));
        throw new RuntimeException("模拟第二步失败（有事务）");
    }

    private Book demoBook(String title) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor("tx-demo");
        book.setPrice(new BigDecimal("9.9"));
        return book;
    }

    @Override
    public boolean saveBatch(Collection<Book> entityList, int batchSize) {
        return executeBatch(entityList, batchSize, (sqlSession, entity) -> bookMapper.insert(entity));
    }

    @Override
    public boolean updateBatchById(Collection<Book> entityList, int batchSize) {
        return executeBatch(entityList, batchSize, (sqlSession, entity) -> bookMapper.updateById(entity));
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<Book> entityList, int batchSize) {
        return executeBatch(entityList, batchSize, (sqlSession, entity) -> saveOrUpdate(entity) ? 1 : 0);
    }
}
