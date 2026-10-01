package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

/**
 * 图书实体：对应数据库 book 表
 */
@TableName("book")
public class Book {

    /** 主键，自增 */
    @TableId(type = IdType.AUTO)
    private Long id;

    @NotBlank(message = "书名不能为空")
    @Size(max = 100, message = "书名不能超过 100 个字符")
    private String title;

    private String author;

    @NotNull(message = "价格不能为空")
    @DecimalMin(value = "0.0", inclusive = false, message = "价格必须大于 0")
    private BigDecimal price;

    /**
     * 展示标签（作者·书名）——不在表里的字段
     * @TableField(exist = false) 告诉 MyBatis-Plus：这不是表列，别生成进 SQL
     * 值由 Service 查询后在内存里填充
     */
    @TableField(exist = false)
    private String displayLabel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDisplayLabel() {
        return displayLabel;
    }

    public void setDisplayLabel(String displayLabel) {
        this.displayLabel = displayLabel;
    }
}
