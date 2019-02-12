package com.hsshy.beam.multi.service.impl;


import com.hsshy.beam.common.constant.DatasourceEnum;
import com.hsshy.beam.common.mutidatasource.annotion.DataSource;
import com.hsshy.beam.multi.entity.Test;
import com.hsshy.beam.multi.dao.TestMapper;
import com.hsshy.beam.multi.service.TestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author fengshuonan
 * @since 2018-07-10
 */
@Service
public class TestServiceImpl implements TestService {

    @Resource
    private TestMapper testMapper;

    @Override
    @DataSource(name = DatasourceEnum.DATA_SOURCE_BIZ)
    @Transactional
    public void testBiz() {
        Test test = new Test();
        test.setName("bizTest");
        testMapper.insert(test);
    }

    @Override
    @DataSource(name = DatasourceEnum.DATA_SOURCE_GUNS)
    @Transactional
    public void testGuns() {
        Test test = new Test();
        test.setName("gunsTest");
        testMapper.insert(test);
    }

    @Override
    @Transactional
    public void testAll() {
        testGuns();

        testBiz();
//        int i = 1 / 0;
    }

}
