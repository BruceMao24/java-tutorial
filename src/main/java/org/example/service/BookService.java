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

    /**
     * 事务实验（无事务）：插入书 A 后模拟第二步失败
     * 没有事务保护，书 A 会作为脏数据留在库里
     */
    void addPairWithoutTx();

    /**
     * 事务实验（有事务）：插入书 A 后模拟第二步失败
     * 有 @Transactional 保护，异常时书 A 也会被回滚
     */
    void addPairWithTx();
}
