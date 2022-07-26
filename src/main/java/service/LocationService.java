package service;


import dataTransferObject.DestinationsDTO;
import domain.Destinations;
import repository.PostgresRepository;
import repository.DestinationRepository;

import java.util.ArrayList;
import java.util.List;


public class LocationService {

    private DestinationRepository destinationRepository = new PostgresRepository();

    public List<DestinationsDTO> listDestinations() {
        List<DestinationsDTO> destinations = new ArrayList<>();
        for (Destinations dest : destinationRepository.findAll()) {
            destinations.add(new DestinationsDTO(
                    dest.destination(),
                    dest.description(),
                    dest.season(),
                    dest.cost(),
                    dest.isVisited(),
                    dest.seatClass()
            ));
        }
        return destinations;
    }

    public void saveDestinations(DestinationsDTO dto) {
        DestinationRepository repo = new PostgresRepository();
        repo.save(dto);
    }
}