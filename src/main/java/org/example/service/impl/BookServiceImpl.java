package org.example.service.impl;

import com.baomidou.mybatisplus.extension.repository.AbstractRepository;
import org.example.entity.Book;
import org.example.mapper.BookMapper;
import org.example.service.BookService;
import org.springframework.stereotype.Service;

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
