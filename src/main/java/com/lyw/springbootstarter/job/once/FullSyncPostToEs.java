package com.lyw.springbootstarter.job.once;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


// todo 取消注释开启任务
@Component
@Slf4j
public class FullSyncPostToEs implements CommandLineRunner {


    @Override
    public void run(String... args) {

        log.info("FullSyncPostToEs enter");
    }
}
