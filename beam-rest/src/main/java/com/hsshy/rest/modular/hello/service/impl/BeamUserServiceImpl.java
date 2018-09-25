package com.hsshy.rest.modular.hello.service.impl;

import com.hsshy.jpa.entity.BeamUser;
import com.hsshy.jpa.repository.BeamUserRepository;
import com.hsshy.rest.modular.hello.service.BeamUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @description: 用户服务实现类
 * @author: hs
 * @create: 2018-09-24 22:48:55
 **/
@Service
public class BeamUserServiceImpl implements BeamUserService {

    @Autowired
    private BeamUserRepository beamUserRepository;

    @Override
    public BeamUser findByAge(Integer age) {
        return beamUserRepository.findByAge(age);
    }

    @Override
    public BeamUser findByUserNameAndAge(String name, Integer age) {
        return beamUserRepository.findByUserNameAndAge(name,age);
    }

    @Override
    public BeamUser findUser(String name) {
        return beamUserRepository.findUser(name);
    }
}
