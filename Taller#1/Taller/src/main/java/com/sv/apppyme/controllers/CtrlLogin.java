package com.sv.apppyme.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.sv.apppyme.controllers.dto.LoginDto;
import com.sv.apppyme.controllers.dto.SignUpDto;
import com.sv.apppyme.entities.User;

import jakarta.validation.Valid;

@Controller
public class CtrlLogin {

	List<User> lista = new ArrayList<>();
	String currentUser = "";

	{
		lista.add(new User("Daniel", "123", new Date(), true, "admin"));
		lista.add(new User("Tatiana", "123", new Date(), true, "user"));
	}

	@GetMapping("/")
	public String indexPage() {
		return "index";
	}

	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

	@GetMapping("/error")
	public String errorPage() {
		return "error";
	}

	@GetMapping("/user")
	public String userPage(Model model) {
		model.addAttribute("admin", false);
		model.addAttribute("username", currentUser);
		model.addAttribute("fecha", new Date().toString());
		return "home";
	}
	
	@GetMapping("/admin")
	public String adminPage(Model model) {
		model.addAttribute("admin", true);
		model.addAttribute("username", currentUser);
		model.addAttribute("fecha", new Date().toString());
		model.addAttribute("usuarios", lista);
		return "home";
	}

	@PostMapping("/SingupSrv")
	public String singUp(@ModelAttribute @Valid SignUpDto userInfo, BindingResult result, Model model) {

		System.out.println("tiene errores " + result.hasErrors());
		if (result.hasErrors()) {
			model.addAttribute("hasError", true);
			List<FieldError> errores = result.getFieldErrors();
			List<String> mensajesDeErrores = new ArrayList<>();
			errores.forEach(i -> {
				mensajesDeErrores.add("Error en el campo " + i.getField());
			});
			model.addAttribute("errores", mensajesDeErrores);
			return "index";
		}

		lista.add(new User(userInfo.getUsername(), userInfo.getPassword(), new Date(), false, userInfo.getRol()));
		currentUser = userInfo.getUsername();
		
		switch (userInfo.getRol()){
		case "user":
			return "redirect:user";
		case "admin":
			return "redirect:admin";
		}
		
		return "error";
	}

	@PostMapping("/loginSrv")
	public String login(@ModelAttribute @Valid LoginDto userInfo, BindingResult result, Model model) {
		
		System.out.println("tiene errores " + result.hasErrors());
		
		if (result.hasErrors()) {
			model.addAttribute("hasError", true);
			List<FieldError> errores = result.getFieldErrors();
			List<String> mensajesDeErrores = new ArrayList<>();
			errores.forEach(i -> {
				mensajesDeErrores.add("Error en el campo " + i.getField());
			});
			model.addAttribute("errores", mensajesDeErrores);
			return "login";
		}
		
		for (User user : lista) {
			if (user.getUsername().equals(userInfo.getUsername())
					&& user.getPassword().equals(userInfo.getPassword())) {
				currentUser = userInfo.getUsername();
				switch (user.getRol()){
				case "user":
					return "redirect:user";
				case "admin":
					return "redirect:admin";
				default:
					throw new IllegalArgumentException("Unexpected value: " + user.getRol());
				}
				
			}
		}

		return "error";
	}

}
