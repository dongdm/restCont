package com.cld.setting;

import com.cld.cawl.GoodsBreadthCrawler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by dong on 2017/7/2.
 */
@Configuration
public class Config {

    @Bean
    public GoodsBreadthCrawler crawler(){
        GoodsBreadthCrawler craw = new GoodsBreadthCrawler("otherCraw", true);
        return craw;
    }

}
