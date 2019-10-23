package com.model.controller;

import com.model.entity.Student;
import com.model.service.DroolsService;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 对类的描述
 *
 * @author liutianyang
 * @since 1.0
 */
@RestController
@RequestMapping("/drools")
public class DroolsController {

    @Autowired
    private DroolsService droolsService;

    @Autowired
    private KieSession kieSession;

    @ResponseBody
    @RequestMapping("/test1")
    public String test1() throws Exception {
        kieSession.insert(new Double("3"));
        kieSession.insert("2");
        kieSession.getAgenda().getAgendaGroup("abc").setFocus();
        kieSession.fireAllRules();
        return "success";
    }

    @ResponseBody
    @RequestMapping("/test2")
    public String test2(){
        Student student = new Student();
        student.setAge(23);
        kieSession.insert(student);
        kieSession.fireAllRules();
        return "test2";
    }
}
