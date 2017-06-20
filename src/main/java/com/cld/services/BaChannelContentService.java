package com.cld.services;

import com.cld.bean.BaChannelContent;

import java.util.List;

/**
 * Created by szb on 2017/6/20
 */
public interface BaChannelContentService {

     public BaChannelContent findById(int id);

     public List<BaChannelContent> searchAll(BaChannelContent baChannelContent);

     void add(BaChannelContent baChannelContent);

     void merge(BaChannelContent baChannelContent);

}
