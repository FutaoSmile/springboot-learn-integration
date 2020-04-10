package com.futao.springboot.learn.rabbitmq.doc.reliabledelivery;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

/**
 * XXL-JOB配置
 *
 * @author futao
 * @date 2020/4/1.
 */
@Setter
@Getter
@Slf4j
//@Configuration
//@ConfigurationProperties(prefix = "xxl.job")
public class XxlJobConfig {


    private final Admin admin = new Admin();
    private final Executor executor = new Executor();

    @Bean
    public XxlJobSpringExecutor xxlJobExecutor(XxlJobConfig xxlJobConfig) {
        log.info(">>>>>>>>>>> xxl-job config init.");
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(xxlJobConfig.getAdmin().getAddresses());
        xxlJobSpringExecutor.setAppName(xxlJobConfig.getExecutor().getAppName());
        xxlJobSpringExecutor.setIp(xxlJobConfig.getExecutor().getIp());
        xxlJobSpringExecutor.setPort(xxlJobConfig.getExecutor().getPort());
        xxlJobSpringExecutor.setLogPath(xxlJobConfig.getExecutor().getLogPath());
        xxlJobSpringExecutor.setLogRetentionDays(xxlJobConfig.getExecutor().getLogRetentionDays());
        return xxlJobSpringExecutor;
    }

    @Getter
    @Setter
    public static class Admin {
        private String addresses;
    }

    @Getter
    @Setter
    public static class Executor {
        private String appName;
        private String ip;
        private int port;
        private String logPath;
        private int logRetentionDays;
    }
}
