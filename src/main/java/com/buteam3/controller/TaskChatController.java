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
import com.buteam3.repository.ChannelRepository;
import com.buteam3.entity.Channel;
import com.buteam3.repository.MessageRepository;
import com.buteam3.entity.Message;

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
public class TaskChatController {

    private RecordRepository repository;
	private ChannelRepository Chrepository;
    private MessageRepository Msrepository;

    @Autowired
    public TaskChatController(RecordRepository repository, ChannelRepository Chrepository, MessageRepository Msrepository) {
        this.repository = repository;
        this.Chrepository = Chrepository;
        this.Msrepository = Msrepository;
    }
    public String taskchat(ModelMap model, HttpServletRequest req) {
        Application application = ApplicationResolver.INSTANCE.getApplication(req);
        AccountList accounts = application.getAccounts();
        model.addAttribute("accounts", accounts);
        task_chat(model);
        return "chat";
    }

    @RequestMapping(value="/issue/new", method = RequestMethod.POST)
    public String insertData(ModelMap model, @Valid Message message, int channelId) {
		Channel channel = Chrepository.findByTaskid(channelId);
		message.setChannelId((int)channel.getChannelId());
        Msrepository.save(message);
        model.addAttribute("messages", message);
        return "fragments/chat";
    }
    private void task_chat(ModelMap model) {
        List<Message> message = Msrepository.findBychannelid(-1);
        model.addAttribute("chatbox", message);
        
    }
    @RequestMapping(value="/issue/updateChannel", method = RequestMethod.POST)
    public String updateChannel(ModelMap model,int met, String data) {
		Channel channel;
		if (met==0){
			channel = new Channel();
			Record record = repository.findByData(data);
			channel.setChannelName(data);
			channel.setTaskId((int)record.getId());
			Chrepository.save(channel);
		}
		else{
			channel = Chrepository.findByChannelname(data);
			Record record = repository.findByData(data);
			record.setChannelId(channel.getChannelId());
			repository.save(record);
		}
		task_chat(model);
		return "fragments/chat";
    }
	
    @RequestMapping(value="/issue/readmsg", method = RequestMethod.POST)
    public String readData(ModelMap model, int id) {
		Channel channel = Chrepository.findByTaskid(id);
        List<Message> messages = Msrepository.findBychannelid((int)Chrepository.findByTaskid(id).getChannelId());
        //List<Message> messages = Msrepository.findBychannelid(id);
        model.addAttribute("messages", messages);
        return "fragments/chat";
    }
}
