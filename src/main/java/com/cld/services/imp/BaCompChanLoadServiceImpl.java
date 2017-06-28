package com.cld.services.imp;

import com.cld.bean.BaCompchanData;
import com.cld.bean.BaCompchanFile;
import com.cld.bean.BaCompchanInfo;
import com.cld.services.BaCompChanLoadService;
import com.cld.services.BaCompchanDataService;
import com.cld.services.BaCompchanFileService;
import com.cld.services.BaCompchanInfoService;
import ognl.IntHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dong on 2017/6/28.
 */
@Service
public class BaCompChanLoadServiceImpl implements BaCompChanLoadService {

    @Autowired
    private BaCompchanInfoService baCompchanInfoService;
    @Autowired
    private BaCompchanFileService baCompchanFileService;
    @Autowired
    private BaCompchanDataService baCompchanDataService;

    @Override
    public Map<String, Object> load(String compChanId) {
        //节点描述信息
        BaCompchanData record = baCompchanDataService.selectByCompchanId(compChanId);
        //节点文件
        List<BaCompchanFile> fileList = baCompchanFileService.selectByCompchanId(compChanId);
        //节点自定义信息
        List<BaCompchanInfo> infoList = baCompchanInfoService.selectByCompchanId(compChanId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("desc", record);
        map.put("file", fileList);
        map.put("info", infoList);
        return map;
    }
}
