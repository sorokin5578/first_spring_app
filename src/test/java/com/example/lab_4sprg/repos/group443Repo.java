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

}