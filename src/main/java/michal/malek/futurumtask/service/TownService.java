package michal.malek.futurumtask.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import michal.malek.futurumtask.exception.GeoApiException;
import michal.malek.futurumtask.mapper.TownMapper;
import michal.malek.futurumtask.model.entity.Town;
import michal.malek.futurumtask.model.response.GeoApiTownsResponse;
import michal.malek.futurumtask.model.response.TownResponse;
import michal.malek.futurumtask.repository.TownRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TownService {
    private final TownRepository townRepository;
    private final GeoApiService geoApiService;
    private final TownMapper townMapper;

    @PostConstruct
    public void insertLargeTownsIntoDB(){
        String countryId = "PL";
        int minPopulation = 100_000;
        int offset = 0;
        int offsetStep = 5;
        int amountOfLargeCities = 45;

        try {
            List<Town> towns = new ArrayList<>();
            while (offset < amountOfLargeCities) {
                Thread.sleep(2000);  //Added to not get to many request response
                GeoApiTownsResponse cities = geoApiService.getCities(countryId, minPopulation, offset);
                List<TownResponse> townResponses = cities.getTowns();

                towns.addAll(
                        townResponses.stream()
                                .map(townMapper::ResponsetoTown)
                                .toList()
                );
                offset += offsetStep;
            }
            townRepository.saveAllAndFlush(towns);
            log.info("Api Towns Insertion Success");
        } catch (GeoApiException | InterruptedException | IllegalStateException e) {
            log.info("Basic Towns Insertion Instead");
            this.addBasicTowns();
        }
    }

    private void addBasicTowns() {
        Town lublin = new Town();
        lublin.setId(3542798L);
        lublin.setName("Lublin");
        lublin.setPopulation(334681);
        townRepository.save(lublin);

        Town mielec = new Town();
        mielec.setId(92623L);
        mielec.setName("Mielec");
        mielec.setPopulation(58213);
        townRepository.save(mielec);

        Town zamosc = new Town();
        zamosc.setId(92012L);
        zamosc.setName("Zamość");
        zamosc.setPopulation(59274);
        townRepository.save(zamosc);

        Town lodz = new Town();
        lodz.setId(94223L);
        lodz.setName("Łódź");
        lodz.setPopulation(655279);
        townRepository.save(lodz);

        Town wroclaw = new Town();
        wroclaw.setId(3884436L);
        wroclaw.setName("Wrocław");
        wroclaw.setPopulation(674132);
        townRepository.save(wroclaw);
    }

    public List<Town> getAll() {
        return townRepository.findAll();
    }
}
