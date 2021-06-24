package exemplo.webflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exemplo.webflux.domain.Localizacao;
import exemplo.webflux.service.LocalizacaoService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("localizacao")
public class LocalizacaoController {
	
	private final LocalizacaoService localizacaoService;
	
	
	@GetMapping
	public Flux<Localizacao> listAll(){
		return localizacaoService.findAll();	
	}
	
	@GetMapping("{id}")
	public Mono<Localizacao> findById(@PathVariable Long id){
		return localizacaoService.findById(id);
	}

}
