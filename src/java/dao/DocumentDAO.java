/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Document;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

/**
 *
 * @author kmert
 */
public class DocumentDAO extends DBConnection{
    public List<Document> read(){
        List<Document> dList = new ArrayList<>();
        try {
            String query = "select * from document";
            PreparedStatement st = this.connect().prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Document d = new Document();
                
                d.setId(rs.getInt("id"));
                d.setFilePath(rs.getString("path"));
                d.setFileName(rs.getString("name"));
                d.setFileType(rs.getString("type"));
                
                dList.add(d);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return dList;
    }
    
    public void create(Document d){
        try {
            String query = "insert into document(path, name, type) values(?, ?, ?)";
            PreparedStatement st = this.connect().prepareStatement(query);
            st.setString(1, d.getFilePath());
            st.setString(2, d.getFileName());
            st.setString(3, d.getFileType());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public Document getById(int id){
        Document d = null;
        try {
            String query = "select * from document where id = ?";
            PreparedStatement st = this.connect().prepareStatement(query);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();
            
            d = new Document();
            d.setId(rs.getInt("id"));
            d.setFileName(rs.getString("name"));
            d.setFilePath(rs.getString("path"));
            d.setFileType(rs.getString("type"));
            return d;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }   
        return d;
    }

}
