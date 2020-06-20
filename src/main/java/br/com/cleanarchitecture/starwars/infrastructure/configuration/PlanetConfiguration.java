package br.com.cleanarchitecture.starwars.infrastructure.configuration;

import br.com.cleanarchitecture.starwars.core.ports.repository.PlanetRepository;
import br.com.cleanarchitecture.starwars.core.ports.thirdpartyapi.PlanetThirdPartyApi;
import br.com.cleanarchitecture.starwars.core.usecases.GetPlanetMoviesUseCase;
import br.com.cleanarchitecture.starwars.core.usecases.GetPlanetMoviesUseCaseImpl;
import br.com.cleanarchitecture.starwars.core.usecases.ManagePlanetUseCase;
import br.com.cleanarchitecture.starwars.core.usecases.ManagePlanetUseCaseImpl;
import br.com.cleanarchitecture.starwars.infrastructure.persistence.converters.PlanetRepositoryConverter;
import br.com.cleanarchitecture.starwars.infrastructure.persistence.repositories.impl.PlanetRepositoryImpl;
import br.com.cleanarchitecture.starwars.infrastructure.persistence.repositories.mongodb.PlanetMongoDbRepository;
import br.com.cleanarchitecture.starwars.infrastructure.thirdpartyapi.PlanetThirdPartyApiImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlanetConfiguration {
    @Autowired
    private PlanetMongoDbRepository planetMongoDbRepository;

    @Bean
    public PlanetRepositoryConverter createPlanetRepositoryConverter() {
        return new PlanetRepositoryConverter();
    }

    @Bean
    public PlanetRepository createPlanetRepository() {
        return new PlanetRepositoryImpl(planetMongoDbRepository,createPlanetRepositoryConverter());
    }

    @Bean
    public ManagePlanetUseCase createManagePlanetUseCase() {
        return new ManagePlanetUseCaseImpl(createPlanetRepository());
    }

    @Bean
    public PlanetThirdPartyApi createPlanetThirdPartyApi() {
        return new PlanetThirdPartyApiImpl();
    }

    @Bean
    public GetPlanetMoviesUseCase createGetPlanetMoviesUseCase() {
        return new GetPlanetMoviesUseCaseImpl(createPlanetThirdPartyApi());
    }

}
