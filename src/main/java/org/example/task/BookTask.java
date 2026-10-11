package org.example.task;

import org.example.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 定时任务示例：无需任何人调接口，到点自动执行
 */
@Component
public class BookTask {

    private static final Logger log = LoggerFactory.getLogger(BookTask.class);
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("HH:mm:ss");

    private final BookService bookService;

    public BookTask(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * fixedRate：每 30 秒执行一次；initialDelay：启动后先等 5 秒再第一次执行
     * 真实场景类比：每隔一段时间统计一次业务数据
     * 注解已注释，需要演示时取消注释即可生效
     */
    // @Scheduled(initialDelay = 5000, fixedRate = 30000)
    public void reportBookCount() {
        long count = bookService.count();
        log.info("[TASK] 定时统计：当前共 {} 本图书，执行时间 {}", count, LocalDateTime.now().format(FMT));
    }

    /**
     * cron 表达式：秒 分 时 日 月 周（Spring 是 6 位）
     * "0 0 2 * * ?" = 每天凌晨 2 点执行，真实场景：夜间跑批、数据同步
     * 为了不干扰演示，这里用注释形式给出写法
     */
    // @Scheduled(cron = "0 0 2 * * ?")
    // public void dailyJob() { ... }
}
