/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.ParadigmDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Khanh
 */
public class ParadigmDAO implements Serializable {

    private static final String connectionString = "jdbc:sqlserver://localhost:1433;databaseName=TaoLao";
    private static final String user = "sa";
    private static final String password = "123456";
    private Connection conn;

    public ParadigmDAO() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conn = DriverManager.getConnection(connectionString, user, password);
    }

    public boolean createParadigm(ParadigmDTO dto) {
        String sql = "INSERT INTO Paradigm(name, size, brand, hard, price, link, image)"
                + "Values(?,?,?,?,?,?,?)";
        boolean check = false;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(connectionString, user, password);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, dto.getName());
            ps.setString(2, dto.getSize());
            ps.setString(3, dto.getBrand());
            ps.setString(4, dto.getHard());
            ps.setString(5, dto.getPrice());
            ps.setString(6, dto.getPrice());
            ps.setString(7, dto.getPrice());
            check = ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

}
