package com.example.lab_4sprg.controller;

import com.example.lab_4sprg.domain.User;
import com.example.lab_4sprg.domain.group443;
import com.example.lab_4sprg.repos.group443Repo;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private group443Repo group443Repo;

    @Autowired
    private EntityManager entityManager;

    @GetMapping("/")
    public String greeting(Map<String,Object> model){ return "greeting"; }


    @GetMapping("/main")
    public String main(Map<String,Object> model){
        Iterable<group443> group443s=group443Repo.findAllHql();

        model.put("names", group443s);
        return "main";
    }

//    @PostMapping("/main")
//    public String add(Map<String,Object> model){
//        Iterable<group443> names = group443Repo.findAllHql();
//        model.put("names", names);
//
//        return "main";
//    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String,Object> model){
        try{
        Iterable<group443> names;
        if(filter!=null && !filter.isEmpty()){
            names =group443Repo.findByAge2(Integer.parseInt(filter));
        }
        else{
            names = group443Repo.findAllHql();
        }
        model.put("names", names);
        }catch (Exception e){
            model.put("message", "Введите целое число!");
            return "main";
        }
        return "main";
    }

    @GetMapping("/Add")
    public String Add(){
        return "Add";
    }

    @PostMapping("/Add")
    public String Add(@AuthenticationPrincipal User user,@RequestParam String text, @RequestParam String text2, @RequestParam String text3, Map<String,Object> model){
        try {
            group443 test1 = new group443(text, text2, Integer.parseInt(text3), user);
            group443Repo.save(test1);
            Iterable<group443> names = group443Repo.findAllHql();
            model.put("message", "Cтудент успешно добавлен!");
            model.put("names", names);
        }
        catch (Exception e){
            model.put("messageAdd", "Не правильный формат ввода!");
            return "Add";
        }
        return "main";
    }

    @GetMapping("/DeleteById")
    public String DeleteById(){
        return "DeleteById";
    }

    @Modifying
    @Transactional
    @PostMapping("/DeleteById")
    public String DeleteById(@RequestParam String text, Map<String,Object> model){
        try {
            if (!text.isEmpty()) {
                Session session = session();
                group443 student = FindByIdCrit(session, Integer.parseInt(text));
                if (student != null) {
                    Transaction txn = session.beginTransaction();
                    String hql = "delete com.example.lab_4sprg.domain.group443 as gr where gr.id=:id";
                    Query query = session.createQuery(hql);
                    query.setParameter("id", Integer.parseInt(text));
                    query.executeUpdate();
                    txn.commit();
                    Iterable<group443> names = group443Repo.findAllHql();
                    model.put("message", "Студент с id=" + text + " успешно удален!");
                    model.put("names", names);
                    return "main";
                } else {
                    model.put("messageDel", "Студента с id=" + text + " не существует!");
                    return "DeleteById";
                }
            } else {
                model.put("messageDel", "Не правильный id!");
                return "DeleteById";
            }
        }catch (Exception e){
            model.put("messageDel", "Не правильный формат ввода!");
            return "DeleteById";
        }
    }

    @GetMapping("/UpdateById")
    public String UpdateById(){
        return "UpdateById";
    }

    @Modifying
    @Transactional
    @PostMapping("/UpdateById")
    public String UpdateById(@RequestParam String text0,@RequestParam String text1, @RequestParam String text2, @RequestParam String text3, Map<String,Object> model){
        try {
            if (!text0.isEmpty()) {
                Session session = session();
                group443 student = FindByIdCrit(session, Integer.parseInt(text0));
                if (student != null) {
                    Transaction txn = session.beginTransaction();
                    String hql = "update com.example.lab_4sprg.domain.group443 as gr set gr.sname1=:sname, gr.name1=:name, gr.age=:age where gr.id=:id";
                    Query query = session.createQuery(hql);
                    query.setParameter("id", Integer.parseInt(text0));
                    text2 = text2.isEmpty() ? student.getSname1() : text2;
                    query.setParameter("sname", text2);
                    text1 = text1.isEmpty() ? student.getName1() : text1;
                    query.setParameter("name", text1);
                    text3 = text3.isEmpty() ? student.getAge().toString() : text3;
                    query.setParameter("age", Integer.parseInt(text3));
                    query.executeUpdate();
                    txn.commit();
                    Iterable<group443> names = group443Repo.findAllHql();
                    model.put("message", "Студент с id=" + text0 + " успешно обновлен!");
                    model.put("names", names);
                    return "main";
                } else {
                    model.put("messageUp", "Студента с id=" + text0 + " не существует!");
                    return "UpdateById";
                }
            } else {
                model.put("messageUp", "Не правильный id!");
                return "UpdateById";
            }
        }catch (Exception e){
            model.put("messageUp", "Не правильный формат ввода!");
            return "UpdateById";
        }

    }


    public group443 FindByIdCrit(Session session, Integer id){
        Transaction txn= session.beginTransaction();
        Criteria criteria = session.createCriteria(group443.class)
                .add(Restrictions.like("id",id));
        group443 group = (group443)criteria.uniqueResult();
        txn.commit();
        return group;
    }

    public Session session(){
        entityManager=entityManager.getEntityManagerFactory().createEntityManager();
        Session session = (Session) entityManager.unwrap(Session.class);
        return session;
    }
}