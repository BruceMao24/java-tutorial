package org.example.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

/**
 * 新增图书请求 DTO
 * 只包含前端需要传的字段,不包含 id、createTime 等后端控制的字段
 */
public class BookCreateDTO {

    @NotBlank(message = "书名不能为空")
    @Size(max = 100, message = "书名不能超过 100 个字符")
    private String title;

    private String author;

    @NotNull(message = "价格不能为空")
    @DecimalMin(value = "0.0", inclusive = false, message = "价格必须大于 0")
    private BigDecimal price;

    /**
     * 分类编码(示例:不入 book 表,但前端要传、要校验)
     * 真实场景里可能是 categoryCode、tags、captcha 等临时字段
     */
    @NotBlank(message = "分类编码不能为空")
    @Size(max = 20, message = "分类编码不能超过 20 个字符")
    private String categoryCode;

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

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }
}
