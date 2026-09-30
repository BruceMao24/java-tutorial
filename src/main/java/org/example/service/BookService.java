package org.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.repository.IRepository;
import org.example.entity.Book;

/**
 * 图书业务接口
 */
public interface BookService extends IRepository<Book> {

    /**
     * 分页 + 条件查询：keyword 非空时按书名或作者模糊匹配
     */
    IPage<Book> pageBooks(int pageNum, int pageSize, String keyword);
}
