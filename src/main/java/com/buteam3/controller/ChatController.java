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

@Controller
public class ChatController {

    private MessageRepository repository;

    @Autowired
    public ChatController(MessageRepository repository) {
        this.repository = repository;
    }
    private void chat(ModelMap model) {
        List<Message> message = repository.findByChannel(0);
        model.addAttribute("chatbox", message);
    }

    @RequestMapping(value="/chat_msg/new", method = RequestMethod.POST)
    public String insertData(ModelMap model,
                             @Valid Message message,
                             BindingResult result) {
        if (!result.hasErrors()) {
            repository.save(message);
        }
        chat(model);
        return "fragments/chat";
    }
}
