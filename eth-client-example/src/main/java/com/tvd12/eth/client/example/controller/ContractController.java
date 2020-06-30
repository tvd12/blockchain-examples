package com.tvd12.eth.client.example.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tvd12.eth.client.example.AmazingToken;
import com.tvd12.eth.client.example.body.ContractTransferRequest;
import com.tvd12.eth.client.example.data.AccountData;
import com.tvd12.eth.client.example.service.AccountService;
import com.tvd12.eth.client.example.service.ContractService;

import lombok.Setter;

@Setter
@Controller
@RequestMapping("/")
public class ContractController {

	@Autowired
	private AccountService accountService;
	
	@Autowired 
	private ContractService contractService;
	
	@GetMapping("contract")
	public ModelAndView index() throws Exception {
		ModelAndView modelAndView = new ModelAndView("contract");
		AmazingToken contract = contractService.loadAmazingToken();
		Map<String, AccountData> accounts = accountService.getAllAccounts();
		modelAndView.addObject("accounts", accounts);
		modelAndView.addObject("contractAddress", contract.getContractAddress());
		return modelAndView;
	}
	
	@PostMapping("deploy-amazing-contract")
	public ModelAndView deployAmazingContract() throws Exception {
		ModelAndView modelAndView = new ModelAndView("contract");
		AmazingToken contract = contractService.deployAmazingToken();
		Map<String, AccountData> accounts = accountService.getAllAccounts();
		modelAndView.addObject("accounts", accounts);
		modelAndView.addObject("contractAddress", contract.getContractAddress());
		return modelAndView;
	}
	
	@PostMapping("amazing-contract-transfer")
	public ModelAndView amazingContractTransfer(
			@ModelAttribute("transfer") ContractTransferRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView("contract");
		contractService.transfer(request.getTo(), request.getValue());
		Map<String, AccountData> accounts = accountService.getAllAccounts();
		modelAndView.addObject("accounts", accounts);
		modelAndView.addObject("contractAddress", "0x187edf145d6e33e483c0c9765d9e36443b0f14a5");
		return modelAndView;
	} 
	
}
