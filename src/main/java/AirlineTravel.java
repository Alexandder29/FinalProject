import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/airlineTravel")
public class AirlineTravel extends HttpServlet {
    private int counter;

    private PostgresRepository repository = new PostgresRepository();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        counter++;

        String destination = req.getParameter("destination");
        String description = req.getParameter("description");
        String season = req.getParameter("season");
        String seatclass = req.getParameter("seatclass");
        int cost = Integer.parseInt(req.getParameter("cost"));
        boolean visited = Boolean.parseBoolean(req.getParameter("visited"));

        try {
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
        counter++;
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<head><title>Destinations</title></head>");

        try {
            out.println("<h3>Destination wishes</h3>");
            out.println("<table>");
            out.println("<tr><th>Id</th><th>Destination</th><th>Description</th><th>Season</th><th>SeatClass</th><th>Cost</th><th>Visited</th></tr>");

            List<Location> locationList = repository.read();
            for (Location location : locationList) {
                out.println("<tr>");
                out.println("<td>" + location.getDestination() + "</td>");
                out.println("<td>" + location.getDescription() + "</td>");
                out.println("<td>" + location.getSeason() + "</td>");
                out.println("<td>" + location.getSeatClass() + "</td>");
                out.println("<td>" + location.getCost() + "</td>");
                out.println("<td>" + location.isVisited() + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        out.close();
    }

    @Override
    public void init() throws ServletException {
        System.out.println("init() called. Counter is: " + counter);
        super.init();
    }

    @Override
    public void destroy() {
        System.out.println("Destroying Servlet! Counter is: " + counter);
        super.destroy();
    }
}