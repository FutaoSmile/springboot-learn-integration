package com.futao.springboot.learn.rabbitmq.doc.deadletter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author futao
 * @date 2020/4/7.
 */
@RestController
@RequestMapping("/deadLetter")
public class DeadLetterController {

    @Autowired
    private DeadLetterSender sender;

    @GetMapping("/send/{dur}")
    public void send(@PathVariable("dur") String dur) {
        sender.send(dur);
    }
}
