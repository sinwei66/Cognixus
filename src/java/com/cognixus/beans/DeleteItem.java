package com.cognixus.beans;

import com.cognixus.db.SSLConnectionManager;
import com.cognixus.utils.CommonUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author sinwei
 */
@ManagedBean(name = "DeleteItemBean")
@RequestScoped
public class DeleteItem {
    private String id = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public void processDeleteItem() {
        SSLConnectionManager sslManager = new SSLConnectionManager();
        Connection connection = sslManager.getConnection();
        
        // Check if ID is null/empty from the input
        if (CommonUtils.isStringNullOrEmpty(id) || Integer.parseInt(id) < 1) {
            System.err.println("Invalid ID");
            return;
        }
        
        try {
            // Prepare the SQL delete statement
            String sql = "DELETE FROM TODO_ITEM WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(id));

            // Execute the delete statement
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("TODO item deleted successfully.");
            } else {
                System.out.println("No matching TODO item found.");
            }
        } catch (SQLException e) {
            System.out.println("Failed to delete TODO item: " + e.getMessage());
        }
    }
}
