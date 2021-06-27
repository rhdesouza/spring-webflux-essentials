package exemplo.webflux.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	public Flux<Localizacao> listAll() {
		System.out.println("passei");
		return localizacaoService.findAll();
	}
	
	@GetMapping("{id}")
	public Mono<Localizacao> findById(@PathVariable Long id) {
		return localizacaoService.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Localizacao> save(@Valid @RequestBody Localizacao localizacao) {
		return localizacaoService.save(localizacao);
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Mono<Void> update(@PathVariable Long id, @Valid @RequestBody Localizacao localizacao) {
		return localizacaoService.update(localizacao.withId(id));
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Mono<Void> delete(@PathVariable Long id) {
		return localizacaoService.delete(id);
	}

}
