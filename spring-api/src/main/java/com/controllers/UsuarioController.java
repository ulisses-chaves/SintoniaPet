package com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.core.joran.conditional.ElseAction;

import java.util.Random;

import com.models.ChangeWrapper;
import com.models.SenhaWrapper;
import com.models.Token;
import com.models.Usuario;
import com.models.UsuarioWrapper;
import com.repository.TokenRepository;
import com.repository.UsuarioRepository;
import com.service.ServicesFoto;
import com.service.Constants;

@RestController
@RequestMapping(path="/usuario")
@CrossOrigin(origins = "*")

public class UsuarioController
{

	@Autowired
	private UsuarioRepository repositorioUsuario;	
	@Autowired
	private TokenRepository repositorioToken;
	
	@Autowired
	private JavaMailSender sender;
	

	@GetMapping(value="/adm/{login}")
	public @ResponseBody String setAdmin(@PathVariable("login")  String login)
	{
		Usuario usuario = repositorioUsuario.findByLogin(login);

		if(usuario == null)
			return "Errou";
		
		usuario.setAdmin(true);
		repositorioUsuario.delete(usuario);
		repositorioUsuario.save(usuario);
		return "ok";
	}
	
	@GetMapping(value="/")
	public @ResponseBody String getRgTeste()
	{
		
		return "Compilou";

	}
	

	@PostMapping(value="/mudar")	
	public @ResponseBody ResponseEntity<String> mudarSenha( @RequestBody ChangeWrapper wrapper)
	{

		Usuario usuario = repositorioUsuario.findByRg(wrapper.getRg());

		if(usuario == null)
			return new ResponseEntity<>("Não existe", HttpStatus.BAD_REQUEST);
		

		usuario = repositorioUsuario.findByEmail(wrapper.getEmail());

		if(usuario == null)
			return new ResponseEntity<>("Não existe", HttpStatus.BAD_REQUEST);
	
		Random rand = new Random();
		String novaSenha = new String();
		
		for(int i = 0; i < 11; ++i)
		{
			novaSenha += Long.toString(rand.nextInt(9));
		}
			


		try
		{
			SimpleMailMessage message = new SimpleMailMessage();
	        message.setText("Aqui está sua nova senha. Use-a quando for logar a próxima vez: " + novaSenha + "\nApenas para lembrete, seu login é: " + usuario.getLogin());
			message.setFrom(Constants.emailFrom);
			message.setSubject("Nova senha Sintonia Pet");
			message.setTo(usuario.getEmail());
			sender.send(message);
			
			usuario.setSenha(novaSenha);
			this.repositorioUsuario.delete(usuario);
			this.repositorioUsuario.save(usuario);
	        
	       
			return new ResponseEntity<>("Sucesso", HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>("Error: " +e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	
	@PostMapping(value="/mudarSenha/{login}")
	public ResponseEntity<String> alterarSenha(@PathVariable("login") String login, @RequestBody SenhaWrapper senhaWrapper)
	{

		Usuario usuario = repositorioUsuario.findByLogin(login);
		
		if(usuario == null)
		{
			return new ResponseEntity<>("Usuário com esse login não existe", HttpStatus.BAD_REQUEST); 
				
		}

		if(!usuario.getSenha().equals(senhaWrapper.getAntigaSenha()))
		{
			return new ResponseEntity<>("Senhas incompativeis", HttpStatus.BAD_REQUEST); 
			
		}

		usuario.setSenha(senhaWrapper.getNovaSenha());

		this.repositorioUsuario.delete(usuario);
		this.repositorioUsuario.save(usuario);

		return new ResponseEntity<>("", HttpStatus.OK);
	
	}
	
	@PostMapping(value="/token/pegar/{rg}")
	public ResponseEntity<String> getToken(@PathVariable("rg") String rg)
	{
		Token token = repositorioToken.findByRg(rg);

		if(token == null)
			return new ResponseEntity<>("Token com esse rg não existe", HttpStatus.BAD_REQUEST); 
			

			return new ResponseEntity<>(token.getToken(), HttpStatus.BAD_REQUEST); 
			
	}
	
	@PostMapping(value="/token/usar/{login}/{tokenValor}")
	public ResponseEntity<String> usarPremmium(@PathVariable("login") String login, @PathVariable("tokenValor") String tokenValor)
	{
		
		Usuario usuario = repositorioUsuario.findByLogin(login);
		
		if(usuario == null)
		{
			return new ResponseEntity<>("Usuário com esse login não existe", HttpStatus.BAD_REQUEST); 
				
		}
		
		Token token = repositorioToken.findByRg(usuario.getRg());
		
		if(token == null)
		{
			return new ResponseEntity<>("Usuário não possui um token", HttpStatus.BAD_REQUEST);
				
		}
		
		if(!token.getToken().equals(tokenValor))
		{
			return new ResponseEntity<>("Não é o token gerado para o usuário", HttpStatus.BAD_REQUEST); 
		}
		
		if(token.isUsado())
		{
			return new ResponseEntity<>("Esse token já foi utilizado", HttpStatus.BAD_REQUEST); 
		}
		
		token.setUsado(true);
		repositorioToken.delete(token);
		repositorioToken.save(token);
		
		usuario.setIsPremmium(true);
		
		repositorioUsuario.delete(usuario);
		repositorioUsuario.save(usuario);
		
		return new ResponseEntity<>("", HttpStatus.OK);
		
		
	}
	
	
	@PostMapping(value="/token/{rg}")
	public ResponseEntity<String> gerarPremmium(@PathVariable("rg") String rg)
	{
		String tokenPremium = new String();
		
		
				
		if(repositorioToken.findByRg(rg) != null)
		{
			return new ResponseEntity<>("Usuário já possui um token", HttpStatus.BAD_REQUEST);
				
		}
		
		Random rand = new Random();
		while(true)
		{
			for(int i = 0; i < 11; ++i)
			{
				tokenPremium += Long.toString(rand.nextInt(9));
			}
			
			
			if(repositorioToken.findByToken(tokenPremium) == null)
				break;
			
			tokenPremium = new String();
			
		}
		
		
		repositorioToken.save(new Token(rg, tokenPremium));
		
		return new ResponseEntity<>(tokenPremium, HttpStatus.OK); 
		
	}
	
	@PostMapping(value="/add")
	public @ResponseBody ResponseEntity<String> add(@RequestBody UsuarioWrapper usuarioWrapper)
	{
		
		
		Usuario usuario = usuarioWrapper.getUsuario();

		 
		Usuario usuarioBusca =
		repositorioUsuario.findByRgAndLoginAndCpfAndEmail(((Usuario)usuario).getRg(),
		((Usuario)usuario).getLogin(), ((Usuario)usuario).getCpf(), usuario.getEmail());
		
		
		if(usuarioBusca != null)
		 return new ResponseEntity<>("Usuário já existe", HttpStatus.BAD_REQUEST);
		

		try
		{
			ServicesFoto.saveFoto(usuarioWrapper.getImagem(), "fotos/usuarios/" +usuario.getLogin());
			usuario.setCaminhoFoto("fotos/usuarios/");	
		}
		catch(Exception e)
		{
			return new ResponseEntity<>("Erro salvando a foto. Contate	o suporte", HttpStatus.BAD_REQUEST);
						
		}

		usuario.setIsPremmium(false);
		usuario.setAdmin(false);
		
		repositorioUsuario.save(usuario);
		  
		return new ResponseEntity<>(HttpStatus.OK); 
		 
		
	}
	
	@GetMapping(value="/get/{login}")
	public @ResponseBody ResponseEntity<UsuarioWrapper> get(@PathVariable("login") String login)
	{
		Usuario usuarioBusca = repositorioUsuario.findByLogin(login);
		
		 if(usuarioBusca == null)
			 return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); ;
		
		usuarioBusca.setSenha("");
		
		String imagem = new String();
		try
		{
			imagem = ServicesFoto.readFoto(usuarioBusca.getCaminhoFoto(), usuarioBusca.getLogin());
			
		}
		catch(Exception e)
		{
			imagem = e.getMessage();
		}
		
		return new ResponseEntity<>(new UsuarioWrapper(imagem, usuarioBusca), HttpStatus.OK) ;
				
	}
	
	@PostMapping(value="/update/{login}")
	public  @ResponseBody ResponseEntity<String> update(@RequestBody UsuarioWrapper usuarioWrapper, @PathVariable String login)
	{

		Usuario usuarioRequisicao = usuarioWrapper.getUsuario();
		Usuario usuarioBusca = repositorioUsuario.findByLogin(login);
		  
		 if(usuarioBusca == null)
			 return new ResponseEntity<>("Não existe um usuário com os dados cadastrados", HttpStatus.BAD_REQUEST); 


		 if(usuarioRequisicao.getLogin() != null)
		 {
			 if(repositorioUsuario.findByLogin(usuarioRequisicao.getLogin()) != null)
			 	return new ResponseEntity<>("Já existe um usuário com os dados cadastrados", HttpStatus.BAD_REQUEST); 
		
			 usuarioBusca.setLogin(login);
			 
		 }

		 	if(usuarioRequisicao.getSenha() != null)
		 {
			usuarioBusca.setSenha(usuarioRequisicao.getSenha());	
		 }
			 
		 if(usuarioRequisicao.getUf() != null)
		 {
			usuarioBusca.setUf(usuarioRequisicao.getUf());	
		 }

		 if(usuarioRequisicao.getNome() != null)
		 {
			usuarioBusca.setNome(usuarioRequisicao.getNome());	
		 }

		 if(usuarioRequisicao.getSobrenome() != null)
		 {
			usuarioBusca.setSobrenome(usuarioRequisicao.getSobrenome());	
		 }
		
		 if(usuarioRequisicao.getCpf() != null)
		 {
			if(repositorioUsuario.findByCpf(usuarioRequisicao.getCpf()) != null)
			return new ResponseEntity<>("Já existe um usuário com os dados cadastrados", HttpStatus.BAD_REQUEST); 
   
			usuarioBusca.setCpf(usuarioRequisicao.getCpf());	
		 }

		 if(usuarioRequisicao.getRg() != null)
		 {
			if(repositorioUsuario.findByRg(usuarioRequisicao.getRg()) != null)
			return new ResponseEntity<>("Já existe um usuário com os dados cadastrados", HttpStatus.BAD_REQUEST); 
   
			usuarioBusca.setRg(usuarioRequisicao.getRg());	
		 }
		
		 if(usuarioRequisicao.getData_nascimento() != null)
		 {
			usuarioBusca.setData_nascimento(usuarioRequisicao.getData_nascimento());	
		 }
		
		 if(usuarioRequisicao.getSexo() != 'z')
		 {
			usuarioBusca.setSexo(usuarioRequisicao.getSexo());	
		 }

		 if(usuarioRequisicao.getEstadoCivil() != 'z')
		 {
			usuarioBusca.setEstadoCivil(usuarioRequisicao.getEstadoCivil());	
		 }
			 
		 if(usuarioRequisicao.getRua() != null)
		 {
			usuarioBusca.setRua(usuarioRequisicao.getRua());	
		 }
	
		 if(usuarioRequisicao.getBairro() != null)
		 {
			usuarioBusca.setBairro(usuarioRequisicao.getBairro());	
		 }

		 if(usuarioRequisicao.getCidade() != null)
		 {
			usuarioBusca.setCidade(usuarioRequisicao.getCidade());	
		 }

		 if(usuarioRequisicao.getCep() != null)
		 {
			usuarioBusca.setCep(usuarioRequisicao.getCep());	
		 }

		 if(usuarioRequisicao.getPais() != null)
		 {
			usuarioBusca.setPais(usuarioRequisicao.getPais());	
		 }

		 if(usuarioRequisicao.getNumeroTelefone() != null)
		 {
			usuarioBusca.setNumeroTelefone(usuarioRequisicao.getNumeroTelefone());	
		 }

		 if(usuarioRequisicao.getNumeroFixo() != null)
		 {
			usuarioBusca.setNumeroFixo(usuarioRequisicao.getNumeroFixo());	
		 }

			 
		if(usuarioRequisicao.getEmail() != null)
		{
			if(repositorioUsuario.findByEmail(usuarioRequisicao.getEmail()) != null)
			return new ResponseEntity<>("Já existe um usuário com os dados cadastrados", HttpStatus.BAD_REQUEST); 
	   
			usuarioBusca.setEmail(usuarioRequisicao.getEmail());	
		}
		 
		
		if(usuarioWrapper.getImagem() != null & !usuarioWrapper.getImagem().equals("")) 
		{
			
			try
			{
				
				ServicesFoto.saveFoto(usuarioWrapper.getImagem(), "fotos/usuarios/" + usuarioBusca.getLogin());
				usuarioBusca.setCaminhoFoto("fotos/usuarios/");	
			}
			catch(Exception e)
			{
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
									
			}
	}
		
		repositorioUsuario.delete(usuarioBusca);
		repositorioUsuario.save(usuarioBusca);
		
	
		return new ResponseEntity<>(HttpStatus.OK);
	}
	

}
