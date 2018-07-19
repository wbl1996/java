package com.wang.recruitwebsite.repository;

import com.wang.recruitwebsite.entity.AllApply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface AllApplyRepository extends JpaRepository<AllApply,Integer> {
    @Query(value = "select id,s_id,s_name,e_name,post,flag from AllApply a where e_name=?1")
    List<AllApply> findByEnterpriseName(String e_name);
    //为什么这里查询的表示根据实体类而不是数据库中的表
    @Modifying
    @Transactional
    @Query(value = "update AllApply set flag = true where s_id=?1")
    public void updateApply(String s_id);
    @Query(value = "select id,s_id,s_name,e_name,post,flag from AllApply a where s_id=?1")
    List<AllApply> findByStudentId(String s_id);
}
