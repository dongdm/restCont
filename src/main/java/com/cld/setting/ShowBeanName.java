package com.cld.setting;

import com.cld.cawl.GoodsBreadthCrawler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * Created by dong on 2017/7/2.
 */
@Configuration
public class ShowBeanName implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(ShowBeanName.class);

    @Autowired
    private ApplicationContext app;

    @Override
    public void run(String... strings) throws Exception {
        String[] names = app.getBeanDefinitionNames();
        for(String name: names){
            //LOG.info(name);
        }
    }
}
