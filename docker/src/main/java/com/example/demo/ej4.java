package com.example.demo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class ej4 {
	@GetMapping("/")
	public String controller_home(HttpSession session,Model model) {
		ArrayList<Anuncio> asuntoList = (ArrayList<Anuncio>) session.getAttribute("asuntoList");		

		if(asuntoList != null && !asuntoList.isEmpty()) {
			model.addAttribute("asuntoList",asuntoList);
		}
		
		return "ej4_home.html";
	}
	@GetMapping("/new")
	public String controller_now(Model model) {

		
		return "ej4_now.html";
	}
	@GetMapping("/insert")
	public String controller_insert(@RequestParam("nombre") String nombre,@RequestParam("asunto") String asunto,@RequestParam("comentario") String comentario,Model model,HttpSession session) {

		ArrayList<Anuncio> asuntoList = (ArrayList<Anuncio>) session.getAttribute("asuntoList");		
		
		if(asuntoList == null) {
			asuntoList = new ArrayList<>();
		}
		
		asuntoList.add(new Anuncio(nombre, asunto, comentario));
		
		session.setAttribute("asuntoList",asuntoList);
		
		return "ej4_insert.html";
	}
	
	@GetMapping("/mostrar/{nombre}")
	public String controller_mostrar(@PathVariable("nombre") String nombre,Model model,HttpSession session) {

		ArrayList<Anuncio> asuntoList = (ArrayList<Anuncio>) session.getAttribute("asuntoList");		
		
		for(Anuncio anuncio : asuntoList) {
			if(anuncio.getNombre().equals(nombre)) {
				model.addAttribute("asunto", anuncio.getAsunto());
				model.addAttribute("descripcion", anuncio.getComentario());

			}
		}
		
		model.addAttribute("nombre", nombre);
		


		
		return "ej4_mostrar.html";
	}
}
