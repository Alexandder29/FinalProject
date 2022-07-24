package domain;

public record Destinations(
        long id,
        String destination,
        String description,
        String season,
        int cost,
        boolean isVisited,
        String seatClass
){}
