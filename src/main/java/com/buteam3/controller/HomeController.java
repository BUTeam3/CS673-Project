package com.buteam3.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.buteam3.repository.RecordRepository;
import com.buteam3.entity.Record;

import com.stormpath.sdk.account.AccountList;
import com.stormpath.sdk.application.Application;
import com.stormpath.sdk.servlet.application.ApplicationResolver;

@Controller
public class HomeController {

    private RecordRepository repository;

    @Autowired
    public HomeController(RecordRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String home(ModelMap model, HttpServletRequest req) {
        Application application = ApplicationResolver.INSTANCE.getApplication(req);
        AccountList accounts = application.getAccounts();
        model.addAttribute("accounts", accounts);
        issue_tracker(model);
        return "home";
    }

    private void issue_tracker(ModelMap model) {
        List<Record> icebox = repository.findByState(0);
        List<Record> backlog = repository.findByState(1);
        List<Record> current = repository.findByState(2);
        List<Record> done = repository.findByState(3);
        model.addAttribute("icebox", icebox);
        model.addAttribute("backlog", backlog);
        model.addAttribute("current", current);
        model.addAttribute("done", done);
        
    }

    @RequestMapping(value="/task/new", method = RequestMethod.POST)
    public String insertData(ModelMap model,@Valid Record record,BindingResult result) {
        if (!result.hasErrors()) {
           repository.save(record);
        }
        issue_tracker(model);
        return "fragments/issue_tracker";
    }
    @RequestMapping(value="/task/update", method = RequestMethod.POST)
    public String updateData(ModelMap model,@Valid Record record,BindingResult result) {
        if (!result.hasErrors()) {
			long x = 127;
			Record record1 = repository.findById(x);
			record1.setState(1);
			repository.saveAndFlush(record1);
			
        }
        issue_tracker(model);
        return "fragments/issue_tracker";
    }
}
