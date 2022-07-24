package repository;

import dataTransferObject.DestinationsDTO;
import domain.Destinations;
import service.Location;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresRepository implements DestinationRepository {

    final static String URL = "jdbc:postgresql://localhost:5432/AirlineTravel";
    final static String USERNAME = "traveler";
    final static String PASSWORD = System.getenv("POSTGRES_PASSWORD");

    public PostgresRepository() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new AccessException(e);
        }
    }

    public void insert(Location location) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");

        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        PreparedStatement pSt = connection.prepareStatement("INSERT INTO travelhistory(destination, description, season, seatclass, cost, visited) VALUES (?, ?, ?, ?, ?, ?)");
        pSt.setString(1, location.getDestination());
        pSt.setString(2, location.getDescription());
        pSt.setString(3, location.getSeason());
        pSt.setString(4, location.getSeatClass());
        pSt.setInt(5, location.getCost());
        pSt.setBoolean(6, location.isVisited());

        int rowsInserted = pSt.executeUpdate();
        System.out.println("You have inserted " + rowsInserted + " locations.");

        pSt.close();
        connection.close();
    }

    public List<Destinations> findAll() {


        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

             Statement st = conn.createStatement();

             ResultSet rs = st.executeQuery("SELECT * FROM travelhistory");
        ) {

            List<Destinations> locations = new ArrayList<>();
            while (rs.next()) {
                long id = rs.getLong("id");
                String destination = rs.getString("destination");
                String description = rs.getString("description");
                String season = rs.getString("season");
                String seatclass = rs.getString("seatclass");
                int cost = rs.getInt("cost");
                boolean visited = rs.getBoolean("visited");
            }
            return locations;
        } catch (SQLException e) {
            throw new AccessException(e);
        }
    }

    @Override
    public void save(DestinationsDTO dto) {

        try (
                Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

             PreparedStatement pSt = conn.prepareStatement("INSERT INTO travelhistory (destination, description, season, seatclass, cost, visited) VALUES (?, ?, ?, ?, ?, ?)"
             )
        ) {
            pSt.setString(1, dto.destination());
            pSt.setString(2, dto.description());
            pSt.setString(3, dto.season());
            pSt.setString(4, dto.seatClass());
            pSt.setInt(5, dto.cost());
            pSt.setBoolean(6, dto.isVisited());

            int rowsInserted = pSt.executeUpdate();
            System.out.println("Inserted " + rowsInserted);

        } catch (SQLException e) {
            throw new AccessException(e);
        }
    }
}
