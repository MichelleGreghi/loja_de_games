package com.generation.lojadegames.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.lojadegames.model.Produto;
import com.generation.lojadegames.repository.CategoriaRepository;
import com.generation.lojadegames.repository.ProdutoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping 
	public ResponseEntity<List<Produto>> getAll(){
		return ResponseEntity.ok(produtoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Long id){
		
		return produtoRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome){
		
		return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
		
	@PostMapping
	public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto){
		
		return categoriaRepository.findById(produto.getCategoria().getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto)))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	
	@PutMapping
	public ResponseEntity<Produto> put(@Valid @RequestBody Produto produto){
				
		return produtoRepository.findById(produto.getId())
				.map(resposta -> 
					categoriaRepository.findById(produto.getCategoria().getId())
						.map(resposta2 -> ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto)))
						.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build())
				)
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		return produtoRepository.findById(id)
				.map(resposta -> {
					produtoRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
	}
	
	@GetMapping("/preco_maior/{preco_maior}")
	public ResponseEntity<List<Produto>> getByPrecoMaior(@PathVariable BigDecimal preco_maior){
		return ResponseEntity.ok(produtoRepository.findAllByPrecoGreaterThan(preco_maior));
	}
	
	@GetMapping("/preco_menor/{preco_menor}")
	public ResponseEntity<List<Produto>> getByPrecoMenor(@PathVariable BigDecimal preco_menor){
		return ResponseEntity.ok(produtoRepository.findAllByPrecoLessThan(preco_menor));
	}
	
	
		

		
}
