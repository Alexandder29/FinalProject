package repository;

import dataTransferObject.DestinationsDTO;
import domain.Destinations;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InMemoryDestinationRepository implements DestinationRepository {
    private List<Destinations> history = new ArrayList<>();

    @Override
    public List<Destinations> findAll() {
        return Collections.unmodifiableList(history);
    }

    @Override
    public void save(DestinationsDTO dto) {
        history.add(new Destinations(history.size(), dto.destination(), dto.description(), dto.season(), dto.cost(), dto.isVisited(), dto.seatClass()));
    }
}
