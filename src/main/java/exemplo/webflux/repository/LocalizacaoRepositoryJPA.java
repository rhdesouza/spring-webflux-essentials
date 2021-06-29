package exemplo.webflux.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import exemplo.webflux.domain.Localizacao;

@Repository
public interface LocalizacaoRepositoryJPA extends JpaRepository<Localizacao, Long>{

}
