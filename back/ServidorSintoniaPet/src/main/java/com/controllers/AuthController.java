package com.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/")
public class AuthController {
	
	
	
	@PostMapping(value = "login")
	public @ResponseBody String login()
	{
		return "Logado";
	}
	
	@PostMapping(value = "logout")
	public @ResponseBody String logout(HttpSession session)
	{
		session.invalidate();
		return "Deslogado";
	}
	
}
