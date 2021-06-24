package exemplo.webflux.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import exemplo.webflux.domain.Localizacao;

public interface LocalizacaoRepository extends ReactiveCrudRepository<Localizacao, Long>{

}
