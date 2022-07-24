package web;

import repository.PostgresRepository;
import dataTransferObject.DestinationsDTO;
import service.Location;
import service.LocationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/airlineTravel")
public class AirlineTravel extends HttpServlet {

    private LocationService locationService = new LocationService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String destination = req.getParameter("destination");
        String description = req.getParameter("description");
        String season = req.getParameter("season");
        String seatclass = req.getParameter("seatclass");
        int cost = Integer.parseInt(req.getParameter("cost"));
        boolean visited = Boolean.parseBoolean(req.getParameter("isVisited"));

        try {
            //String history = locationService.printHistory(destination, description, season, cost, visited, seatclass);

            Location location = new Location(destination, description, season, seatclass, cost, visited);
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter out = resp.getWriter();
            out.println("<h2>=-=Destination=-=</h2>");

            out.println("Destination is: <b>" + location + "</b><br />");
            out.println("<a href='/AirlineTravel_war_exploded'>Insert another destination</a>");

            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<head><title>Destinations</title></head>");

        out.println("<body>");
        out.println("Destination wishes<br />");
        out.println("<table>");
        out.println("<tr><th>Id</th><th>Destination</th><th>Description</th><th>Season</th><th>SeatClass</th><th>Cost</th><th>Visited</th></tr>");

        for (DestinationsDTO values : locationService.listDestinations()) {
            out.println("<tr>");
            out.println("<td>" + values.destination() + "</td>");
            out.println("<td>" + values.description() + "</td>");
            out.println("<td>" + values.season() + "</td>");
            out.println("<td>" + values.seatClass() + "</td>");
            out.println("<td>" + values.cost() + "</td>");
            out.println("<td>" + values.isVisited() + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");

        out.println("</body>");
        out.close();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        getServletContext().log("init() called");
    }

    @Override
    public void destroy() {
        System.out.println("Destroying Servlet!");
        super.destroy();
    }
}