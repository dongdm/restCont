package com.cld.services.imp;

import com.cld.bean.BaCompchanFile;
import com.cld.mapper.BaCompchanFileMapper;
import com.cld.services.BaCompchanFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * Created by dong on 2017/6/23.
 */
@Service
public class BaCompchanFileServiceImpl implements BaCompchanFileService {

    @Autowired
    private BaCompchanFileMapper mapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        Integer idInt = new Integer(id);
        return mapper.deleteByPrimaryKey(idInt);
    }

    @Override
    public int insert(BaCompchanFile record) {
        return 0;
    }

    @Override
    public int insertSelective(String filePath, String fileName, String chanDataId) {
        return insertSelective( filePath,  fileName,0,  chanDataId);
    }

    @Override
    public int insertSelective(String filePath, String fileName,long fileSize, String chanDataId) {
        BaCompchanFile record = new BaCompchanFile();
        record.setFileName(fileName);
        record.setFileUrl(filePath);
        record.setFileSize(fileSize + "");
        Integer chanId = new Integer(chanDataId);
        record.setCompChanId(chanId);
        mapper.insertSelective(record);
        return record.getId();
    }

    @Override
    public BaCompchanFile selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(BaCompchanFile record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(BaCompchanFile record) {
        return 0;
    }

    @Override
    public List<BaCompchanFile> selectByCompchanId(String compChanId) {
        BaCompchanFile record = new BaCompchanFile();
        record.setCompChanId(new Integer(compChanId));
        return mapper.selectByOther(record);
    }
}
