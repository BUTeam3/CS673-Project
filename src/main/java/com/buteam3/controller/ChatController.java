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

import com.buteam3.repository.MessageRepository;
import com.buteam3.entity.Message;

import com.stormpath.sdk.account.AccountList;
import com.stormpath.sdk.application.Application;
import com.stormpath.sdk.servlet.application.ApplicationResolver;

/**
 * Class is controller object which handles interaction between
 * front-end and database for chat tool.
 * 
 * @author buteam3
 */

@Controller
public class ChatController {

    private MessageRepository repository;

    /**
     * Constructor for home controller. Takes in a repository
     * of messages to be loaded into chat window in UI. 
     * 
     * @param repository repository of messages
     */
    @Autowired
    public ChatController(MessageRepository repository) {
        this.repository = repository;
    }	
    
    /**
     * 
     * @param model
     * @param req
     * @return 
     */
    public String chat(ModelMap model, HttpServletRequest req) {
            Application application = ApplicationResolver.INSTANCE.getApplication(req);
    AccountList accounts = application.getAccounts();
    model.addAttribute("accounts", accounts);
    chatmsg(model);
    return "chat";
    }
    /**
     * Loads a a list of messages from database based on channel id
     * 
     * @param model model map linking messages to chat UI chatbox
     */
    private void chatmsg(ModelMap model) {
        List<Message> message = repository.findByMidGreaterThan(0);
        model.addAttribute("chatbox", message);
    }
    /**
     * Method called when a new message is entered. Saves the new message
     * to the database through message repository.
     * 
     * @param model
     * @param message
     * @param result
     * @return 
     */
    @RequestMapping(value="/chat_msg/new", method = RequestMethod.POST)
    public String insertData(ModelMap model,
                             @Valid Message message,
                             BindingResult result) {
        if (!result.hasErrors()) {
            repository.save(message);
        }
        chatmsg(model);
        return "fragments/chat";
    }
    /**
     * Calls chatmsg to Load a a list of messages from database based on channel id
     * 
     * @param model
     * @param message
     * @param result
     * @return 
     */
    @RequestMapping(value="/chat_msg/read", method = RequestMethod.POST)
    public List readData(Long mid) {
        List<Message> message = repository.findByMidGreaterThan(mid);
        return message;
    }
}
