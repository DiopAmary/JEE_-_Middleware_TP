package sn.dioppp___.Spring_Boot_project.controller;


import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sn.dioppp___.Spring_Boot_project.dao.Patient;
import sn.dioppp___.Spring_Boot_project.dao.PatientRepository;




@Controller
public class HomeController {
	
	@Autowired
	private PatientRepository patientRepository;
	
	
	@GetMapping(value = "/")
	public String home(Model model,
			@RequestParam(name = "page", defaultValue = "0")int page,
			@RequestParam(name = "size", defaultValue = "5")int size,
			@RequestParam(name = "motCle", defaultValue = "")String motCle) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Patient> patientsList = patientRepository.findByNomContains(motCle, pageable);
		model.addAttribute("listPatients",patientsList.getContent());
		model.addAttribute("pagesNumber", new int[patientsList.getTotalPages()]);
		model.addAttribute("pageCourante", page);
		model.addAttribute("motCle", motCle);
		return "home";
	}
	
	@GetMapping(value = "/delete")
	public String delete (Long id, String motCle, String page, String size){
		patientRepository.deleteById(id);
		return "redirect:/?page="+page+"&size="+size+"&motCle="+motCle;
	}
	
	@GetMapping(value = "/edit")
	public String edit( Model model, Long id){
		Patient patient = patientRepository.getOne(id);
		model.addAttribute("patient", patient);
		return "edit";
	}
	
	@GetMapping(value = "/save")
	public String save( Model model, String nom, Date dateNaissance, int score, boolean malade){
		Patient patient = new Patient(0, nom, dateNaissance, score, malade);
		patientRepository.save(patient);
		return "redirect:/?page=&size=&motCle="+patient.getNom();
	}
	
	@GetMapping(value = "/ajouter")
	public String ajouter( Model model){
		Patient patient = new Patient();
		model.addAttribute("patient", patient);
		return "edit";
	}
	
	@GetMapping(value = "/login")
	public String login( ){
		return "login";
	}
	@GetMapping(value = "/logout")
	public String logout(HttpServletRequest request ) throws ServletException{
		request.logout();
		return "redirect:/login";
	}
	
	
}