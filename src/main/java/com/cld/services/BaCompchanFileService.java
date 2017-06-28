package com.cld.services;

import com.cld.bean.BaCompchanFile;

import java.io.File;
import java.util.List;

/**
 * Created by dong on 2017/6/23.
 */
public interface BaCompchanFileService {

    int deleteByPrimaryKey(String id);

    int insert(BaCompchanFile record);

    int insertSelective(String filePath, String fileName, String chanDataId);

    int insertSelective(String filePath, String fileName,long fileSize, String chanDataId);

    BaCompchanFile selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaCompchanFile record);

    int updateByPrimaryKey(BaCompchanFile record);

    List<BaCompchanFile> selectByCompchanId(String compChanId);
}
