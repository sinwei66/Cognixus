package com.cognixus.beans;

import com.cognixus.db.SSLConnectionManager;
import com.cognixus.utils.CommonUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author sinwei
 */
@ManagedBean(name = "MarkItemBean")
@RequestScoped
public class MarkItem {
    private String id = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public void updateMarkItem() {
        SSLConnectionManager sslManager = new SSLConnectionManager();
        Connection connection = sslManager.getConnection();
        
        // Check if ID is null/empty from the input
        if (CommonUtils.isStringNullOrEmpty(id) || Integer.parseInt(id) < 1) {
            System.err.println("Invalid ID");
            return;
        }
        
        try {
            // Create an SQL update statement
            String sql = "UPDATE todos SET completed = true WHERE id = " + id;

            // Create a statement object
            Statement statement = connection.createStatement();

            // Execute the update statement
            int rowsAffected = statement.executeUpdate(sql);

            if (rowsAffected > 0) {
                System.out.println("TODO item completion status updated successfully.");
            } else {
                System.out.println("No matching TODO item found.");
            }
        } catch (SQLException e) {
            System.out.println("Failed to update TODO item completion status: " + e.getMessage());
        }
    }
}
