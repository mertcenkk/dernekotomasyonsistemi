/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Dernek;
import entity.Sube;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

/**
 *
 * @author kmert
 */
public class SubeDAO extends DBConnection {
    
    private DernekDAO dDao;

    public DernekDAO getdDao() {
        if (dDao== null)
            this.dDao = new DernekDAO();
        return dDao;
    }

    public void setdDao(DernekDAO dDao) {
        this.dDao = dDao;
    }
    

    //CRUD
    public void create(Sube u) {
        try {
            PreparedStatement pst = this.connect().prepareStatement("insert into sube(ad,adres,dernekId) values(?,?,?)");
            pst.setString(1, u.getAd());
            pst.setString(2, u.getAdres());
            pst.setLong(3, u.getDernek().getDernekId());
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Sube> read(int page, int pageSize) {
        List<Sube> list = new ArrayList<>();
        int start = (page-1)*pageSize;
        try {
            PreparedStatement pst = this.connect().prepareStatement("select * from sube order by id asc limit "+start+","+pageSize);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Dernek d = this.getdDao().getById(rs.getLong("dernekId"));
                Sube tmp = new Sube();
                tmp.setId(rs.getLong("id")); 
                tmp.setAd(rs.getString("ad"));
                tmp.setTarih(rs.getDate("tarih")); 
                tmp.setAdres(rs.getString("adres"));
                tmp.setDernek(d);
                list.add(tmp);
            }
            pst.close();
            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void update(Sube u) {
        try {
            PreparedStatement pst = this.connect().prepareStatement("update sube set ad=?,adres=?,dernekId=? where id=?");
            pst.setString(1, u.getAd());
            pst.setString(2, u.getAdres());
            pst.setLong(3, u.getDernek().getDernekId());
            pst.setLong(4, u.getId());
            pst.executeUpdate();
            pst.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(Sube u) {
        try {
            PreparedStatement pst = this.connect().prepareStatement("delete from sube where id=?");
            pst.setLong(1, u.getId());
            pst.executeUpdate();
            pst.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public int count() {
            int count=0;
            try {
            PreparedStatement pst = this.connect().prepareStatement("select count(id) as subeCount from sube");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count=rs.getInt("subeCount");
            pst.close();
            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return count;
    }

}
