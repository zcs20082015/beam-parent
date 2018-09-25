package com.hsshy.beam.jpa.repository;

import com.hsshy.beam.jpa.entity.BeamUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BeamUserRepository extends JpaRepository<BeamUser,Long> {

    /**
     * 根据年纪查询用户
     * @param age
     * @return
     */
    BeamUser findByAge(Integer age);

    /**
     * 根据年纪和姓名查询
     * @param name
     * @param age
     * @return
     */
    BeamUser findByUserNameAndAge(String name, Integer age);

    /**
     * 对于复杂查询可以使用@Query 编写sql
     * @param name
     * @return
     */
    @Query("from BeamUser u where u.userName=:name")
    BeamUser findUser(@Param("name") String name);
    
}