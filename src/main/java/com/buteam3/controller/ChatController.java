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
     * Gets accounts from thymeleaf and adds it to the model to be used
	 * calls the chatmsg method and sends it the model to be populated by a list of messages
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
     * Loads a list of messages from database based on channel id
     * 
     * @param model model map linking messages to chat UI chatbox
     */
    private void chatmsg(ModelMap model) {
        List<Message> message = repository.findBychannelid(0);
        model.addAttribute("chatbox", message);
    }
    /**
     * Method called when a new message is entered. Saves the new message
     * to the database through message repository.
     * 
     * @param model
     * @param message
     * @return 
     */
    @RequestMapping(value="/chat_msg/new", method = RequestMethod.POST)
    public String insertData(ModelMap model, @Valid Message message) {
        repository.save(message);
        model.addAttribute("messages", message);
        return "fragments/chat";
    }
    /**
     * Repopulates the chat box with new messages from other users
     * 
     * @param model
	 * @param mid
     * @return 
     */
    @RequestMapping(value="/chat_msg/read", method = RequestMethod.POST)
    public String readData(ModelMap model, long mid, int id) {
        List<Message> messages = repository.findByMidGreaterThanAndChannelid(mid, id);
        model.addAttribute("messages", messages);
        return "fragments/chat";
    }
}
