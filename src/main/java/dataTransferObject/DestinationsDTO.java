package dataTransferObject;

public record DestinationsDTO(
        String destination,
        String description,
        String season,
        int cost,
        boolean visited,
        String seatClass
) {}
