package com.cld.cawl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dong on 2017/7/2.
 */
public class GoodsDB {

    private static GoodsDB Goodsdb = new GoodsDB();

    private static Map<String, List<Goods>> goods = new HashMap<String, List<Goods>>();

    private GoodsDB(){}

    public static void add(String key, List<Goods> data){
        synchronized (goods){
            goods.put(key, data);
        }
    }

    public static List<Goods> get(String key){
        synchronized (goods){
            List<Goods> result = goods.get(key);
            return result;
        }
    }

}
