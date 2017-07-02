package com.cld.cawl;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by dong on 2017/7/2.
 */
@Service("jdDataAnalyze")
public class JdDataAnalyze {

    private static final Logger LOG = LoggerFactory.getLogger(JdDataAnalyze.class);

    public List<Goods> analyze(Page page, CrawlDatums next){
        //LOG.info(page.getHtml());
        Element goodsList = page.select("div[id=J_goodsList]", 0);
        if(null != goodsList){
            List<Goods> result = new ArrayList<Goods>();
            Elements lis = goodsList.getElementsByTag("li");
            Iterator<Element> it = lis.iterator();
            while (it.hasNext()){
                Goods goodsBean = new Goods();
                goodsBean.setSourceFrom("京东");
                Element goods = it.next();
                //名称
                Element nameEle = goods.select(".p-name").get(0);
                String name = nameEle.text();
                goodsBean.setName(name);
                //链接
                String url = nameEle.select("a").get(0).attr("href");
                goodsBean.setUrl(url);
                //价格
                Element priceEle = goods.select(".p-price").get(0);
                String price = priceEle.text();
                goodsBean.setPrice(price);
                //评论
                Element commitEle = goods.select(".p-commit").get(0);
                String commit = commitEle.text();
                goodsBean.setCommit(commit);
                result.add(goodsBean);
            }
            return result;
        }
        return null;
    }

}
