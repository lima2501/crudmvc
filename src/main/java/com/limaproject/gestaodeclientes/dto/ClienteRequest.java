package com.limaproject.gestaodeclientes.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.limaproject.gestaodeclientes.model.Cliente;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClienteRequest {

	@NotBlank(message = "Nome não pode ser em branco")
	private String nome;

	@Email(message = "Por favor digite um email válido")
	@NotBlank(message = "Email não pode ser em branco")
	private String email;

	@NotBlank(message = "Telefone não pode ser em branco")
	private String telefone;

	@NotBlank(message = "Endereço não pode ser em branco")
	private String endereco;

	public Cliente toCliente() {
		Cliente cliente = new Cliente();
		cliente.setNome(this.nome);
		cliente.setEmail(this.email);
		cliente.setTelefone(this.telefone);
		cliente.setEndereco(this.endereco);

		return cliente;
	}
	
	public Cliente toCliente(Cliente cliente) {
		cliente.setNome(this.nome);
		cliente.setEmail(this.email);
		cliente.setTelefone(this.telefone);
		cliente.setEndereco(this.endereco);

		return cliente;
	}

	public void fromcliente(Cliente cliente) {
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
		this.telefone = cliente.getTelefone();
		this.endereco = cliente.getEndereco();
	}
	
}
