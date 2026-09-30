package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.entity.Book;

/**
 * book 表的数据库操作接口
 * 继承 BaseMapper 就自动拥有 insert/delete/update/select 等全套方法，无需写 SQL
 */
public interface BookMapper extends BaseMapper<Book> {
}
