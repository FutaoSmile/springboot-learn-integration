package com.futao.springboot.learn.rabbitmq.doc.limiting.model;

import cn.hutool.core.util.IdUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

/**
 * @author futao
 * @date 2020/4/17.
 */
@Getter
@Setter
@Builder
public class Article implements Serializable {
    private String id;
    private String content;
    private LocalDateTime publishDateTime;


    public static Article defaultArticle() {
        return Article
                .builder()
                .id(IdUtil.simpleUUID())
                .content(getFileContent())
                .publishDateTime(LocalDateTime.now(ZoneOffset.ofHours(8)))
                .build();
    }

    private static String getFileContent() {
        Optional<String> reduce = null;
        try {
            reduce = Files.readAllLines(Paths.get("/Users/futao/Desktop/学习记录/f2.md")).stream().reduce(String::concat);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reduce.get();
    }
}
