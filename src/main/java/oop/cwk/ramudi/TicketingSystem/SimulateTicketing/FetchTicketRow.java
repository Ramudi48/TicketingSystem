package oop.cwk.ramudi.TicketingSystem.SimulateTicketing;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class FetchTicketRow {
    private int ticketRowValue;

    public FetchTicketRow() {
        // Default constructor
    }

    public int fetchTicketRow(String columnName) {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new RuntimeException("Properties file not found.");
            }

            Properties prop = new Properties();
            prop.load(input);

            String url = prop.getProperty("spring.datasource.url");
            String username = prop.getProperty("spring.datasource.username");
            String password = prop.getProperty("spring.datasource.password");

            try (Connection connection = DriverManager.getConnection(url, username, password);
                 Statement statement = connection.createStatement()) {

                String sql = "SELECT " + columnName + " FROM ticket_table ORDER BY id DESC LIMIT 1";
                ResultSet resultSet = statement.executeQuery(sql);

                if (resultSet.next()) {
                    ticketRowValue = resultSet.getInt(columnName);
                }else{
                    System.out.println("No ticket row found");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error loading properties file.", e);
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching ticket row.", e);
        }
        return ticketRowValue;
    }

    public int fetchTicketRowID() {
        fetchTicketRow("id");
        return ticketRowValue;
    }

    public int fetchLastRowReleseRate(){
        fetchTicketRow("ticket_relese_rate");
        return ticketRowValue;
    }

    public int fetchLastRowRetrievelRate(){
        fetchTicketRow("ticket_retrievel_rate");
        return ticketRowValue;
    }

    public int fetchLastRowMaxCapacity(){
        fetchTicketRow("ticket_max_capacity");
        return ticketRowValue;
    }
}

