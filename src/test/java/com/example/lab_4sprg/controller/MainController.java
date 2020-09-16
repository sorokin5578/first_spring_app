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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private group443Repo group443Repo;

    @Autowired
    private EntityManager entityManager;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }


    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter,
                       Model model) {
        Iterable<group443> names;
        try {
            if (filter != null && !filter.isEmpty()) {
                names = group443Repo.findByAge2(Integer.parseInt(filter));
            } else {
                names = group443Repo.findAllHql();
            }
            model.addAttribute("names", names);
            model.addAttribute("filter", filter);
            return "main";
        } catch (Exception e) {
            names = group443Repo.findAllHql();
            model.addAttribute("filter", filter);
            model.addAttribute("message", "Введите целое число!");
            model.addAttribute("names", names);
            return "main";
        }
    }

    @GetMapping("del/{name}")
    public String del(@PathVariable group443 name, Model model) {
        try {
            return DeleteById(name.getId().toString(), (Map<String, Object>) model);
        } catch (Exception e) {
            return main("",model);
    }

    }

    @GetMapping("up/{name}")
    public String up(@PathVariable group443 name, Model model) {
        try {
            model.addAttribute("text0", name.getId().toString());
            model.addAttribute("text1", name.getName1());
            model.addAttribute("text2", name.getSname1());
            model.addAttribute("text3", name.getAge().toString());
            return "UpdateById";
        } catch (Exception e) {
            return main("",model);
        }
    }

    @GetMapping("/Add")
    public String Add() {
        return "Add";
    }

    @PostMapping("/Add")
    public String Add(
            @AuthenticationPrincipal User user,
            @RequestParam String text1,
            @RequestParam String text2,
            @RequestParam String text3,
            Map<String, Object> model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        try {
            group443 test1 = new group443(text1, text2, Integer.parseInt(text3), user);

            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                String uuidFile = UUID.randomUUID().toString();
                String resultFileName = uuidFile + "." + file.getOriginalFilename();
                file.transferTo(new File(uploadPath + "/" + resultFileName));
                test1.setFileName(resultFileName);
            }

            group443Repo.save(test1);
            Iterable<group443> names = group443Repo.findAllHql();
            model.put("message", "Cтудент успешно добавлен!");
            model.put("names", names);
            return "main";
        }catch(Exception c){
            model.put("messageAdd", "Не правильный формат ввода!");
            model.put("text1", text1);
            model.put("text2", text2);
            model.put("text3", text3);
            return "Add";
        }
    }

    @GetMapping("/DeleteById")
    public String DeleteById() {
        return "DeleteById";
    }

    @Modifying
    @Transactional
    @PostMapping("/DeleteById")
    public String DeleteById(@RequestParam String text, Map<String, Object> model) {
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
        } catch (Exception e) {
            model.put("messageDel", "Не правильный формат ввода!");
            model.put("text", text);
            return "DeleteById";
        }
    }

    @GetMapping("/UpdateById")
    public String UpdateById() {
        return "UpdateById";
    }

    @Modifying
    @Transactional
    @PostMapping("/UpdateById")
    public String UpdateById(@RequestParam String text0,
                             @RequestParam String text1,
                             @RequestParam String text2,
                             @RequestParam String text3,
                             Map<String, Object> model,
                             @RequestParam("file") MultipartFile file
    ) {
        try {
            if (!text0.isEmpty()) {
                Session session = session();
                group443 student = FindByIdCrit(session, Integer.parseInt(text0));
                if (student != null) {
                    if (file != null && !file.getOriginalFilename().isEmpty()) {
                        File uploadDir = new File(uploadPath);
                        if (!uploadDir.exists()) {
                            uploadDir.mkdir();
                        }
                        String uuidFile = UUID.randomUUID().toString();
                        String resultFileName = uuidFile + "." + file.getOriginalFilename();
                        file.transferTo(new File(uploadPath + "/" + resultFileName));
                        student.setFileName(resultFileName);
                    }
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
                    model.put("text0", text0);
                    model.put("text1", text1);
                    model.put("text2", text2);
                    model.put("text3", text3);
                    return "UpdateById";
                }
            } else {
                model.put("messageUp", "Не правильный id!");
                model.put("text0", text0);
                model.put("text1", text1);
                model.put("text2", text2);
                model.put("text3", text3);
                return "UpdateById";
            }
        } catch (Exception e) {
            model.put("messageUp", "Не правильный формат ввода!");
            model.put("text0", text0);
            model.put("text1", text1);
            model.put("text2", text2);
            model.put("text3", text3);
            return "UpdateById";
        }

    }


    public group443 FindByIdCrit(Session session, Integer id) {
        Transaction txn = session.beginTransaction();
        Criteria criteria = session.createCriteria(group443.class)
                .add(Restrictions.like("id", id));
        group443 group = (group443) criteria.uniqueResult();
        txn.commit();
        return group;
    }

    public Session session() {
        entityManager = entityManager.getEntityManagerFactory().createEntityManager();
        Session session = (Session) entityManager.unwrap(Session.class);
        return session;
    }
}