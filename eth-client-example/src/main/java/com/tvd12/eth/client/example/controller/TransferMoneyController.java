package com.tvd12.eth.client.example.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tvd12.eth.client.example.body.TransferMoneyRequest;
import com.tvd12.eth.client.example.data.AccountData;
import com.tvd12.eth.client.example.service.AccountService;
import com.tvd12.eth.client.example.service.TransactionService;

import lombok.Setter;

@Setter
@Controller
@RequestMapping("/")
public class TransferMoneyController {

	@Autowired
	private AccountService accountService;
	
	@Autowired 
	private TransactionService transactionService;
	
	@GetMapping("transfer-money")
	public ModelAndView index() throws Exception {
		ModelAndView modelAndView = new ModelAndView("transfer-money");
		Map<String, AccountData> accounts = accountService.getAllAccounts();
		modelAndView.addObject("accounts", accounts);
		return modelAndView;
	}
	
	@PostMapping("transfer")
	public ModelAndView transfer(
			@ModelAttribute("transfer") TransferMoneyRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView("transfer-money");
		transactionService.transferMoney(
				request.getFromPassword(), 
				request.getFromWalletFile(), 
				request.getFrom(), 
				request.getTo(), 
				request.getValue());
		Map<String, AccountData> accounts = accountService.getAllAccounts();
		modelAndView.addObject("accounts", accounts);
		return modelAndView;
	}
	
}
