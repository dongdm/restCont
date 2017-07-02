package com.cld.cawl;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.net.HttpResponse;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by dong on 2017/7/2.
 */
public class GoodsBreadthCrawler extends BreadthCrawler {

    private static final Logger LOG = LoggerFactory.getLogger(GoodsBreadthCrawler.class);

    @Qualifier("jdDataAnalyze")
    @Autowired
    private JdDataAnalyze jdDataAnalyze = new JdDataAnalyze();
    @Qualifier("tmDataAnalyze")
    @Autowired
    private TmDataAnalyze tmDataAnalyze = new TmDataAnalyze();

    private String keyWord;

    public GoodsBreadthCrawler(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);
        this.setExecuteInterval(6000);
        this.setThreads(1);
    }

    @Override
    public void visit(Page page, CrawlDatums next) {
        if(page.getUrl().contains("tmall.com")){
            //数据来源天猫
            List<Goods> result = tmDataAnalyze.analyze(page, next);
            GoodsDB.add(keyWord, result);
        }else if(page.getUrl().contains("jd.com")){
            //数据来源京东
            List<Goods> result = jdDataAnalyze.analyze(page, next);
            GoodsDB.add(keyWord, result);
        }else{
            //数据来源不明
        }

    }

    public HttpResponse getResponse(CrawlDatum crawlDatum) throws Exception {
        HttpRequest request = new HttpRequest(crawlDatum.getUrl());
        String outputData = crawlDatum.meta("outputData");
        if (outputData != null) {
            request.setOutputData(outputData.getBytes("utf-8"));
        }
        return request.getResponse();
    }

    public void service(String url) throws Exception{
        CrawlDatum datum = new CrawlDatum(url);
        this.addSeed(datum);
        this.start(1);
    }

    public void jdService(String keyWord) throws Exception{
        String url = "https://search.jd.com/Search?enc=utf-8&wq=%E9%9D%92%E5%B2%9B%E5%95%A4%E9%85%92&" +
                "pvid=d19bd73ccb1348b499912067f02cbd43&keyword="+keyWord;
        this.keyWord = keyWord;
        this.service(url);
    }



    public static void main(String[] args) throws Exception{


        GoodsBreadthCrawler craw = new GoodsBreadthCrawler("otherCraw", true);
        craw.jdService("青岛啤酒");

        /*
        String url = "https://list.tmall.com/search_product.htm?q=%C7%E0%B5%BA%C6%A1%BE%C6&type=p&vmarket=&spm=875.7931836%2FB.a2227oh.d100&from=mallfp..pc_1_searchbutton";
        GoodsBreadthCrawler craw = new GoodsBreadthCrawler("otherCraw", true);
        craw.service(url);
        */
    }

}
