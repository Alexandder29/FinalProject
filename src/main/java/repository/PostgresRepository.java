package repository;

import dataTransferObject.DestinationsDTO;
import domain.Destinations;
import service.Location;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresRepository {

    final static String URL = "jdbc:postgresql://localhost:5432/AirlineTravel";
    final static String USERNAME = "traveler";
    final static String PASSWORD = "POSTGRES_PASSWORD";

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

    public List<Location> read() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");

        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        Statement st = conn.createStatement();

        ResultSet rs = st.executeQuery("SELECT id, destination, description, season, seatclass, cost, visited FROM travelhistory");

        List<Location> locations = new ArrayList<>();
        while (rs.next()) {
            Location location = new Location(
                    rs.getString("destination"),
                    rs.getString("description"),
                    rs.getString("season"),
                    rs.getString("seaclass"),
                    rs.getInt("cost"),
                    rs.getBoolean("visited"));

            location.setId(rs.getLong("id"));
            locations.add(location);
        }

        rs.close();
        st.close();
        conn.close();
        return locations;
    }
}
