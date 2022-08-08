package com.SystemVeiculos.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.SystemVeiculos.model.Veiculo;
import com.SystemVeiculos.model.VeiculoRepository;

@Controller
@RequestMapping(path="/veiculos")

public class VeiculoController {
	
	
	private final VeiculoRepository veiculoRepository;
	
	@Autowired
	public VeiculoController(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }
	
	@GetMapping("signup")
    public String showSignUpForm(Veiculo veiculo) {
        return "addVeiculos";
    }
	
	@GetMapping("list")
    public String showUpdateForm(Model model) {
        model.addAttribute("veiculos", veiculoRepository.findAll());
        return "index";
    }
	
	 @PostMapping("add")
	    public String addVeiculo(Veiculo veiculo, BindingResult result, Model model) {
	        if (result.hasErrors()) {
	            return "addVeiculos";
	        }
	        veiculoRepository.save(veiculo);
	        return "addVeiculos";
	    }
	 
	 @GetMapping("/delete/{id}")
	    public String deleteVeiculo(@PathVariable(value = "id") long id) {
	        veiculoRepository.deleteById(id);
	        return "index";
	    }
	       
	 @GetMapping("edit/{id}")
	    public String showUpdateForm(@PathVariable("id") long id, Model model) {
	        Veiculo veiculo = veiculoRepository.findById(id).orElseThrow(() ->new IllegalArgumentException("Invalid Veiculo Id:" + id));
	        model.addAttribute("veiculo", veiculo);
	        return "updateVeiculos";
	    }
	
	 @PostMapping("update/{id}")
	    public String updateVeiculo(@PathVariable("id") long id, Veiculo veiculo, BindingResult result,
	        Model model) {
	        if (result.hasErrors()) {
	            veiculo.setId(id);
	            return "updateVeiculos";
	        }

	        veiculoRepository.save(veiculo);
	        model.addAttribute("veiculos", veiculoRepository.findAll());
	        return "index";
	    }
}

