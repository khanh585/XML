/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.NewsDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Khanh
 */
public class NewsDAO implements Serializable {

    private static final String connectionString = "jdbc:sqlserver://localhost:1433;databaseName=TaoLao";
    private static final String user = "sa";
    private static final String password = "123456";
    
    public boolean createNews(NewsDTO dto){
        String sql =  "INSERT INTO News(title, description, link, pubDate)"
                + "Values(?,?,?,?)";
        boolean check = false;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(connectionString,user,password);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, dto.getTitle());
            ps.setString(2, dto.getDesc());
            ps.setString(3, dto.getLink());
            ps.setString(4, dto.getPubDate());
            check = ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
    public List<NewsDTO> findByTitle(String title){
         List<NewsDTO> result = new ArrayList<>();
        String sql =  "SELECT id, title, description, link, pubDate FROM News "
                + "WHERE Fullname LIKE ? ";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(connectionString,user,password);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, '%'+title+'%');
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                NewsDTO dto = new NewsDTO();
                dto.setId(Integer.parseInt(rs.getString("id")));                
                dto.setTitle(rs.getString("title"));
                dto.setLink(rs.getString("link"));
                dto.setDesc(rs.getString("description"));                
                dto.setPubDate(rs.getString("pubDate"));
                result.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
