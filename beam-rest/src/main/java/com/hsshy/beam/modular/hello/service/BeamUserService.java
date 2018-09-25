package com.hsshy.beam.modular.hello.service;
import com.hsshy.beam.jpa.entity.BeamUser;
import org.springframework.data.repository.query.Param;

/**
 * @description: 用户服务类接口
 * @author: hs
 * @create: 2018-09-24 22:47:37
 **/
public interface BeamUserService {

    BeamUser findByAge(Integer age);

    BeamUser findByUserNameAndAge(String name, Integer age);

    BeamUser findUser(@Param("name") String name);
}
