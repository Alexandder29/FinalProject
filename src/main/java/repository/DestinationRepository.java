package repository;

import dataTransferObject.DestinationsDTO;
import domain.Destinations;

import java.util.List;

public interface DestinationRepository {
    List<Destinations> findAll();

    void save(DestinationsDTO dto);

}
