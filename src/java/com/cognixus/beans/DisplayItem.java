package com.cognixus.beans;
import com.cognixus.db.SSLConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author sinwei
 */
@ManagedBean(name = "DisplayItemBean")
@RequestScoped
public class DisplayItem {
    public void processDisplayItem() {
        SSLConnectionManager sslManager = new SSLConnectionManager();
        Connection connection = sslManager.getConnection();
        
        try {
            // Prepare the SQL select statement
            String sql = "SELECT * FROM TODO_ITEM";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Execute the select statement
            ResultSet resultSet = statement.executeQuery();

            // Iterate through the result set and display the TODO items
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                boolean completed = resultSet.getBoolean("completed");

                System.out.println("ID: " + id);
                System.out.println("Title: " + title);
                System.out.println("Description: " + description);
                System.out.println("Completed: " + completed);
                System.out.println("===========================");
            }
        } catch (SQLException e) {
            System.out.println("Failed to get TODO items: " + e.getMessage());
        }
    }
}
