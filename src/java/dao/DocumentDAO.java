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
public class  DocumentDAO extends DBConnection {
    public List<Document> read(int page, int pageSize){
        List<Document> dList=new ArrayList<>();
        //pagination limit
        int start = (page-1)*pageSize;
        try {
            PreparedStatement pst = this.connect().prepareStatement("select * from document order by id");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Document tmp = new Document();
                tmp.setId(rs.getLong("id"));
                tmp.setFilePath(rs.getString("path"));
                tmp.setFileName(rs.getString("name"));
                tmp.setFileType(rs.getString("type"));
                dList.add(tmp);
            }
            pst.close();;
            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return dList;
    }
    public void create(Document d){
        String query="insert into document(path,name,type) values (?,?,?)";
        try {
            PreparedStatement pst = this.connect().prepareStatement(query);
            pst.setString(1, d.getFilePath());
            pst.setString(2, d.getFileName());
            pst.setString(3, d.getFileType());
            pst.executeUpdate();
            pst.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public double count() {
            int count=0;
            try {
            PreparedStatement pst = this.connect().prepareStatement("select count(id) as documentCount from document");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count=rs.getInt("documentCount");
            pst.close();
            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return count;
    }
}
