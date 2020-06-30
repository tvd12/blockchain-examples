package com.tvd12.eth.client.example.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tvd12.eth.client.example.body.CreateAccoutRequest;
import com.tvd12.eth.client.example.data.AccountData;
import com.tvd12.eth.client.example.service.AccountService;
import com.tvd12.ezyfox.util.EzyLoggable;

import lombok.Setter;

@Setter
@Controller
@RequestMapping("/")
public class AccountManagerController extends EzyLoggable  {
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("account-manager")
	public ModelAndView index() throws Exception {
		ModelAndView modelAndView = new ModelAndView("account-manager");
		Map<String, AccountData> accounts = accountService.getAllAccounts();
		modelAndView.addObject("createdAccount", "");
		modelAndView.addObject("accounts", accounts);
		return modelAndView;
	}
	
	@PostMapping("create-account") 
	public ModelAndView createAccount(
			@ModelAttribute("account") CreateAccoutRequest request) throws Exception {
		String accountAddress = accountService.createAccount(request.getPassword());
		ModelAndView modelAndView = new ModelAndView("account-manager");
		Map<String, AccountData> accounts = accountService.getAllAccounts();
		modelAndView.addObject("createdAccount", accountAddress);
		modelAndView.addObject("accounts", accounts);
		return modelAndView;
	}
}
