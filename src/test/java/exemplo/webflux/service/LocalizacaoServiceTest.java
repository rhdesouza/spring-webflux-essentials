package exemplo.webflux.service;

import exemplo.webflux.domain.Localizacao;
import exemplo.webflux.repository.LocalizacaoRepository;
import exemplo.webflux.util.LocalizacaoCreator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;
import reactor.blockhound.BlockHound;
import reactor.blockhound.BlockingOperationError;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

@ExtendWith(SpringExtension.class)
public class LocalizacaoServiceTest {

    @InjectMocks
    private LocalizacaoService localizacaoService;

    @Mock
    private LocalizacaoRepository localizacaoRepository;

    private final Localizacao localizacao = LocalizacaoCreator.createValidLocalizacao();

    @BeforeAll
    public static void blockHoundSetup() {
        BlockHound.install();
    }

    @BeforeEach
    public void setup() {
        BDDMockito.when(localizacaoRepository.findAll())
                .thenReturn(Flux.just(localizacao));

        BDDMockito.when(localizacaoRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Mono.just(localizacao));

        BDDMockito.when(localizacaoRepository.save(localizacao))
                .thenReturn(Mono.just(localizacao));

        BDDMockito.when(localizacaoRepository.delete(ArgumentMatchers.any(Localizacao.class)))
                .thenReturn(Mono.empty());

        BDDMockito.when(localizacaoRepository.save(LocalizacaoCreator.updateValidLocalizacao()))
                .thenReturn(Mono.just(localizacao));
    }

    @Test
    public void blockHoundWorks() {
        try {
            FutureTask<?> task = new FutureTask<>(() -> {
                Thread.sleep(0);
                return "";
            });
            Schedulers.parallel().schedule(task);
            task.get(10, TimeUnit.SECONDS);
            Assertions.fail("Should fail");
        } catch (Exception e) {
            Assertions.assertTrue(e.getCause() instanceof BlockingOperationError);
        }
    }


    @Test
    @DisplayName("Buscar todas as localizações")
    public void findAll_ReturnFluxLocalizacoes_WhenSuccessful() {
        StepVerifier.create(localizacaoService.findAll())
                .expectSubscription()
                .expectNext(localizacao)
                .verifyComplete();

    }

    @Test
    @DisplayName("Buscar uma localização Mono existente")
    public void findById_ReturnMonoLocalizacao_WhenSuccessful() {
        StepVerifier.create(localizacaoService.findById(1L))
                .expectSubscription()
                .expectNext(localizacao)
                .verifyComplete();

    }

    @Test
    @DisplayName("Buscar uma localização Mono não existente")
    public void findById_ReturnMonoLocalizacao_WhenError() {
        BDDMockito.when(localizacaoRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Mono.empty());

        StepVerifier.create(localizacaoService.findById(1L))
                .expectSubscription()
                .expectError(ResponseStatusException.class)
                .verify();

    }

    @Test
    @DisplayName("Salva uma localização")
    public void save_ReturnMonoLocalizacao_WhenSuccessful() {
        Localizacao localizacaoSave = LocalizacaoCreator.createValidLocalizacao();

        StepVerifier.create(localizacaoService.save(localizacaoSave))
                .expectSubscription()
                .expectNext(localizacao)
                .verifyComplete();

    }

    @Test
    @DisplayName("Deleta uma localização")
    public void delete_Localizacao_WhenSuccessful() {
        StepVerifier.create(localizacaoService.delete(1L))
                .expectSubscription()
                .verifyComplete();
    }

    @Test
    @DisplayName("Tenta deletar uma localização inexistente ")
    public void delete_ReturnMonoError_WhenError() {
        BDDMockito.when(localizacaoRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Mono.empty());

        StepVerifier.create(localizacaoService.delete(1L))
                .expectSubscription()
                .expectError(ResponseStatusException.class)
                .verify();
    }

    @Test
    @DisplayName("Atualiza uma localização")
    public void update_ReturnMonoLocalizacao_WhenSuccessful() {
        StepVerifier.create(localizacaoService.update(LocalizacaoCreator.createValidLocalizacao()))
                .expectSubscription()
                .verifyComplete();

    }

    @Test
    @DisplayName("Atualiza uma localização que não existe")
    public void update_ReturnMonoLocalizacao_WhenError() {
        BDDMockito.when(localizacaoRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Mono.empty());

        StepVerifier.create(localizacaoService.update(LocalizacaoCreator.createValidLocalizacao()))
                .expectSubscription()
                .expectError(ResponseStatusException.class)
                .verify();

    }

}
