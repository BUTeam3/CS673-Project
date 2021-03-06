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

/**
 * Class is a controller handling the initial loading
 * of the UI for the issue tracker as well as modification to 
 * the database for task objects.
 * 
 * @author buteam3
 */

@Controller
public class HomeController {

    private RecordRepository repository;
    /**
     * Constructor for home controller. Takes in a repository
     * of tasks which are then loaded in UI. 
     * 
     * @param Record repository of tasks 
     */
    @Autowired
    public HomeController(RecordRepository repository) {
        this.repository = repository;
    }
    
    /**
     * Method loads a list of user accounts, adds them to a model map,
     * then loads the tasks to the UI using issue_tracker method.
     * 
     * @param model
     * @param req
     * @return 
     */
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String home(ModelMap model, HttpServletRequest req) {
        Application application = ApplicationResolver.INSTANCE.getApplication(req);
        AccountList accounts = application.getAccounts();
        model.addAttribute("accounts", accounts);
        issue_tracker(model);
        return "home";
    }

    /**
     * Method loads lists of record(task) objects according to their
     * 'state'. The state indicates what column the task is listed in
     * within the GUI, i.e icebox, backlog etc. 
     * 
     * 
     * @param model Model map for front end mapping to Record objects
     */
    private void issue_tracker(ModelMap model) {
        List<Record> icebox = repository.findByState(0);
        List<Record> backlog = repository.findByState(1);
        List<Record> current = repository.findByState(2);
        List<Record> done = repository.findByState(3);
        List<Record> tasks = repository.findAll();
        model.addAttribute("icebox", icebox);
        model.addAttribute("backlog", backlog);
        model.addAttribute("current", current);
        model.addAttribute("done", done);
        model.addAttribute("tasks", tasks);
        
    }

    /**
     * Method called when a new task is added. the new task
     * is saved to database through jpa repository. The columns
     * of the issue tracker are re-updated with issue tracker
     * method.
     * 
     * @param model
     * @param record
     * @return 
     */
    @RequestMapping(value="/task/new", method = RequestMethod.POST)
    public String insertData(ModelMap model,@Valid Record record) {
        repository.save(record);
        issue_tracker(model);
        return "fragments/issue_tracker";
    }
    /**
     * Method called when data for a task is updated in anyway, such
     * as on drag/drop. The updated task is re-saved to database
     * and the tasks in the issue tracker GUI are updated with issue-tracker
     * method.
     * 
     * @param model
     * @param id
     * @param state
     * @return 
     */
    @RequestMapping(value="/task/update", method = RequestMethod.POST)
    public String updateData(ModelMap model,Long id,int state) {	
		Record record = repository.findById(id);
		record.setState(state);
		repository.save(record);
		issue_tracker(model);
		return "fragments/issue_tracker";
    }
    /**
     * Method called when difficulty of a task in the icebox is updated,
     * task will be moved to the backlog column
     * 
     * @param model
     * @param id
     * @param state
     * @return 
     */
    @RequestMapping(value="/task/difficulty", method = RequestMethod.POST)
    public String updateDifficulty(ModelMap model,Long id,int difficulty) {	
		Record record = repository.findById(id);
		record.setDifficulty(difficulty);
		repository.save(record);
		issue_tracker(model);
		return "fragments/issue_tracker";
    }
}
