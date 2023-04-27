package com.generation.lojadegames.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_produtos")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Atribua um nome ao produto!")
	@Size(min = 5, max = 100, message = "O atributo nome deve ter no minimo 05 e no máximo 50 caracteres!")
	private String nome;

	@NotBlank(message = "Faça uma breve descrição do produto!")
	@Size(min = 5, max = 1000, message = "O atributo nome deve ter no minimo 05 e no máximo 1000 caracteres!")
	private String descricao;

	@NotNull(message = "Coloque a quantidade disponível na loja!")
	private Integer quantidade;

	@NotNull(message = "Por favor, insira o valor do produto!")
	@Positive
	private BigDecimal preco;
	
	@NotBlank
	@Size
	private String console;


	// Relacionamento com Categoria
	@ManyToOne
	@JsonIgnoreProperties("produto")
	private Categoria categoria;
	
	// Relacionamente com classe Usuário
	@ManyToOne
	@JsonIgnoreProperties("produto")
	private Usuario usuario;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getConsole() {
		return console;
	}

	public void setConsole(String console) {
		this.console = console;
	}
	
	// Método Get e Set de Categoria
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	// Get e Set de Usuário
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
