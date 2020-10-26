/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.CategoryDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Khanh
 */
public class CategoryDAO implements Serializable {

    private static final String connectionString = "jdbc:sqlserver://localhost:1433;databaseName=TaoLao";
    private static final String user = "sa";
    private static final String password = "123456";
    private Connection conn;

    public CategoryDAO() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conn = DriverManager.getConnection(connectionString, user, password);
    }

    public boolean createCategory(CategoryDTO dto) {
        String sql = "INSERT INTO Categories(category, link)"
                + "Values(?,?)";
        boolean check = false;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(connectionString, user, password);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, dto.getCategory());
            ps.setString(2, dto.getLink());
            check = ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public boolean truncate() {
        String sql = "TRUNCATE TABLE Categories;";
        boolean check = false;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(connectionString, user, password);
            PreparedStatement ps = conn.prepareStatement(sql);
            check = ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
}
