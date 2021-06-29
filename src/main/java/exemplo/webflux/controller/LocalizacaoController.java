package exemplo.webflux.controller;

import exemplo.webflux.domain.Localizacao;
import exemplo.webflux.service.LocalizacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("localizacao")
public class LocalizacaoController {

	private final LocalizacaoService localizacaoService;

	@GetMapping
	public Flux<Localizacao> listAll() {
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
