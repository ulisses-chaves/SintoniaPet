package com.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.models.Pet;
import com.models.Usuario;
import com.repository.UsuarioRepository;

@RestController
@RequestMapping(value = "/pet")
public class PetController {

	@Autowired
	private UsuarioRepository usuarios;
	
	@GetMapping(value = "/all/{login}")
	public ResponseEntity<List<Pet>> getAll(@PathVariable("login") String login)
	{
		Usuario usuario = usuarios.findByLogin(login);
		
		if(usuario == null)
			return new ResponseEntity<>(new ArrayList<Pet>(), HttpStatus.BAD_REQUEST); ;
		
		return new ResponseEntity<>(usuario.getPets(), HttpStatus.OK) ;
			
	}
	
	@PostMapping(value = "/add/{login}")
	public ResponseEntity<String> add(@RequestBody Pet pet, @PathVariable("login") String login)
	{
		
		Usuario usuario = usuarios.findByLogin(login);
		
		if(usuario == null)
			return new ResponseEntity<>("Usuário com esse login não existe", HttpStatus.BAD_REQUEST); ;
			
		
		
		if(usuario.getPets().contains(pet))
			return new ResponseEntity<>("Pet já existe", HttpStatus.BAD_REQUEST); ;
			
		usuario.getPets().add(pet);
		usuarios.save(usuario);
		return new ResponseEntity<>(HttpStatus.OK) ;
		
	}
	
	@PutMapping(value = "/update/{login}")
	public ResponseEntity<String> update(@RequestBody Pet pet, @PathVariable("login") String login)
	{
		Usuario usuario = usuarios.findByLogin(login);
		
		if(usuario == null)
			return new ResponseEntity<>("Usuário com esse login não existe", HttpStatus.BAD_REQUEST); ;
			
		usuario.getPets();
		if(!usuario.getPets().contains(pet))
			return new ResponseEntity<>("Pet não existe", HttpStatus.BAD_REQUEST); ;
		
		usuario.getPets().remove(pet);
		usuario.getPets().add(pet);
		usuarios.save(usuario);
		
		
		return new ResponseEntity<>(HttpStatus.OK) ;
	}
	
	@DeleteMapping(value = "/delete/{login}")
	public ResponseEntity<String> delete(@RequestBody Pet pet, @PathVariable("login") String login)
	{
		Usuario usuario = usuarios.findByLogin(login);
		
		if(usuario == null)
			return new ResponseEntity<>("Usuário com esse login não existe", HttpStatus.BAD_REQUEST); ;
			
		if(!usuario.getPets().contains(pet))
			return new ResponseEntity<>("Pet não existe", HttpStatus.BAD_REQUEST); ;
			
		usuario.getPets().remove(pet);
		
		usuarios.save(usuario);
		
		return new ResponseEntity<>(HttpStatus.OK) ;
	}
	
	
}
