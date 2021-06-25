package exemplo.webflux.service;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import exemplo.webflux.domain.Localizacao;
import exemplo.webflux.repository.LocalizacaoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class LocalizacaoService {

	private final LocalizacaoRepository localizacaoRepository;

	public Flux<Localizacao> findAll() {
		return localizacaoRepository.findAll();
	}

	public Mono<Localizacao> findById(Long id) {
		return localizacaoRepository.findById(id).switchIfEmpty(
				Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Localizacao não encontrada")));
	}

	public Mono<Localizacao> save(@Valid Localizacao localizacao) {
		return localizacaoRepository.save(localizacao);
	}

	public Mono<Void> update(Localizacao localizacao) {
		return findById(localizacao.getId()).map(localFound -> localizacao.withId(localFound.getId()))
				.flatMap(localizacaoRepository::save).then();
	}

	public Mono<Void> delete(Long id) {
		return findById(id)
				.flatMap(localizacaoRepository::delete);
	}

}
