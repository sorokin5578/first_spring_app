package com.example.lab_4sprg.repos;
import com.example.lab_4sprg.domain.group443;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface group443Repo extends CrudRepository<group443, Integer> {

    @Query("from com.example.lab_4sprg.domain.group443 as gr where gr.age=:age ORDER BY gr.id ASC")
    List<group443> findByAge2(@Param("age")Integer age);

    @Query("from com.example.lab_4sprg.domain.group443 as gr ORDER BY gr.id ASC")
    List<group443> findAllHql();

//    @Transactional
//    @Modifying
//    @Query("delete com.example.lab_4sprg.domain.group443 as gr where gr.id=:id")
//    List<group443> DeleteById(@Param("id")Integer id);
//
//    @Transactional
//    @Modifying
//    @Query("update com.example.lab_4sprg.domain.group443 as gr set gr.sname1=:sname, gr.name1=:name, gr.age=:age where gr.id=:id")
//    List<group443> UpdateById(@Param("id")Integer id,@Param("sname")String sname,@Param("name")String name,@Param("age")Integer age);
}