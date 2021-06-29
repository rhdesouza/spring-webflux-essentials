package exemplo.webflux.util;

import exemplo.webflux.domain.Localizacao;

public class LocalizacaoCreator {

    public static Localizacao localizacaoToBeSaved(){
        return Localizacao.builder()
                .latitude("11555")
                .longitude("99994")
                .build();
    }

    public static Localizacao createValidLocalizacao(){
        return Localizacao.builder()
                .id(1L)
                .latitude("11555")
                .longitude("99994")
                .build();
    }

    public static Localizacao updateValidLocalizacao(){
        return Localizacao.builder()
                .id(1L)
                .latitude("333333")
                .longitude("22222")
                .build();
    }

}
