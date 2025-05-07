package com.basic.Mmyspringbootlab.runner;

import com.basic.Mmyspringbootlab.config.MyEnvironment;
import com.basic.Mmyspringbootlab.property.MyPropProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class MyPropRunner implements ApplicationRunner {
    @Value("${myprop.username}")
    private String name;

    @Value("${myprop.port}")
    private int port;

    @Autowired
    private Environment environment;

    @Autowired
    private MyPropProperties properties;

    @Autowired
    private MyEnvironment myEnvironment;

    private Logger logger = LoggerFactory.getLogger(MyPropRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Logger 구현체 => " + logger.getClass().getName());

        logger.debug("${myprop.username} = " + name);
        logger.debug("${myprop.port} = " + port);
        logger.debug("${myprop.username} = {}", environment.getProperty("myprop.username"));

        logger.info("MyBootProperties getUsername() = {}", properties.getName());
        logger.info("MyBootProperties getPort() = {}", properties.getPort());
        logger.info("설정된 Port 번호 = {}", environment.getProperty("local.server.port") );

        logger.info("현재 활성화된 MyEnvironment Bean = {}", myEnvironment);

        // foo 라는 VM 아규먼트 있는지 확인
        logger.debug("VM 아규먼트 foo : {}", args.containsOption("foo"));
        // bar 라는 Program 아규먼트 있는지 확인
        logger.debug("Program 아규먼트 bar : {}", args.containsOption("bar"));

        // Program 아규먼트 목록 출력
        args.getOptionNames() //Set<String>
                .forEach( name -> System.out.println(name));

    }
}
