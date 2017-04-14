package com.cld.mapper;

import com.cld.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by dong on 2017/4/13.
 */
@Mapper
public interface UserMapper {

    /**
     * findAll
     * @return
     */
    List<User> findAll();

}
