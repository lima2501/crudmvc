package com.limaproject.gestaodeclientes.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.limaproject.gestaodeclientes.dto.ClienteRequest;
import com.limaproject.gestaodeclientes.model.Cliente;
import com.limaproject.gestaodeclientes.repository.ClienteRepository;

@Controller
public class ClienteController {

	@Autowired
	ClienteRepository clienteRepository;

	@GetMapping("/")
	public ModelAndView home() {
		List<Cliente> clientes = this.clienteRepository.findAll();
		ModelAndView mav = new ModelAndView("home");
		mav.addObject("clientes", clientes);
		return mav;
	}

	@GetMapping("/novo")
	public String novo(ClienteRequest cliente) {
		return "novo";
	}

	@PostMapping("/salvar")
	public ModelAndView salvar(@Valid ClienteRequest request, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("novo");
			return mav;
		} else {
			Cliente cliente = request.toCliente();
			this.clienteRepository.save(cliente);
			ModelAndView mav = new ModelAndView("redirect:/");
			return mav;
		}
	}

	@GetMapping("/detalhe/{id}")
	public ModelAndView detalhe(@PathVariable Integer id, RedirectAttributes attributes) {
		Optional<Cliente> optional = this.clienteRepository.findById(id);
		if (optional.isPresent()) {
			Cliente cliente = optional.get();
			this.clienteRepository.save(cliente);
			ModelAndView mav = new ModelAndView("detalhe");
			mav.addObject("cliente", cliente);

			return mav;
		} else {
			attributes.addFlashAttribute("msg", "cliente inexistente");
			ModelAndView mav = new ModelAndView("redirect:/");
			return mav;
		}
	}

	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable Integer id, ClienteRequest request, RedirectAttributes attributes) {

		Optional<Cliente> optional = this.clienteRepository.findById(id);

		if (optional.isPresent()) {
			Cliente cliente = optional.get();
			request.fromcliente(cliente);
			ModelAndView mav = new ModelAndView("edit");
			mav.addObject("clienteId", cliente.getId());
			return mav;
		} else {
			attributes.addFlashAttribute("msg", "cliente inexistente");
			ModelAndView mav = new ModelAndView("redirect:/");
			return mav;
		}

	}
	
	@PostMapping("/edit/{id}")
	public ModelAndView update(@PathVariable Integer id, @Valid ClienteRequest request, BindingResult result, RedirectAttributes attributes) {
		Optional<Cliente> optional = this.clienteRepository.findById(id);
		
		if(result.hasErrors()) {
			ModelAndView mav = new ModelAndView("novo");
			return mav;
		}else {
			if(optional.isPresent()) {
				Cliente cliente = request.toCliente(optional.get());
				this.clienteRepository.save(cliente);
				ModelAndView mav = new ModelAndView("redirect:/");
				return mav;
			}else {
				attributes.addFlashAttribute("msg", "cliente inexistente");
				ModelAndView mav = new ModelAndView("redirect:/");
				return mav;
			}
		}
		
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		this.clienteRepository.deleteById(id);
		return "redirect:/";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
}
