package com.cld.cawl;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by dong on 2017/7/2.
 */
@Service("tmDataAnalyze")
public class TmDataAnalyze {

    private static final Logger LOG = LoggerFactory.getLogger(TmDataAnalyze.class);

    public List<Goods> analyze(Page page, CrawlDatums next){
        LOG.info(page.getHtml());
        return null;
    }

}
