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
@ManagedBean(name = "AddItemBean")
@RequestScoped
public class AddItem {
    private String title = "";
    private String desc = "";
    private String completed = "";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
    
    public void processAddItem() {
        // Check input data
        if (!validateData()) {
            System.err.println(errMsg);
            return;
        }
        
        insertDB();
    }
    
    private void insertDB() {
        SSLConnectionManager sslManager = new SSLConnectionManager();
        Connection connection = sslManager.getConnection();
        
        try {
            // Prepare the SQL insert statement
            String sql = "INSERT INTO TODO_ITEM (title, description, completed) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, title);
            statement.setString(2, desc);
            statement.setBoolean(3, Boolean.parseBoolean(completed));

            // Execute the insert statement
            statement.executeUpdate();
            System.out.println("TODO item added successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to add TODO item: " + e.getMessage());
        } finally {
            sslManager.closeConnection();
        }
    }
    
    private String errMsg = "";
    private boolean validateData(){
        if (CommonUtils.isStringNullOrEmpty(title)) {
            errMsg = "Title is null/empty!";
            return false;
        }
        
        if (CommonUtils.isStringNullOrEmpty(desc)) {
            errMsg = "Description is null/empty!";
            return false;
        }
        
        if (CommonUtils.isStringNullOrEmpty(completed) || !"true".equals(completed.toLowerCase())) {
            completed = "false";
        }
        return true;
    }
}
