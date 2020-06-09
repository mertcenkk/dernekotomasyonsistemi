/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import util.DBConnection;
import entity.Uye;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kmert
 */
public class UyeDAO extends DBConnection {

    //CRUD
    private DernekDAO dDao;
    public Uye getById(Long id) {
        Uye d = null;
        try {
            PreparedStatement st = this.connect().prepareStatement("select * from uye where uyeId=?");
            ResultSet rs = st.executeQuery();
            rs.next();

            d = new Uye();
            d.setUyeId(rs.getLong("uyeId"));
            d.setTcNo(rs.getLong("tcNo")); 
            d.setAdiSoyadi(rs.getString("adiSoyadi"));
            d.setTelNo(rs.getLong("telNo"));
            d.setMeslek(rs.getString("meslek"));
            d.setOgrenimDurumu(rs.getString("ogrenimDurumu"));
            st.close();
            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return d;
    }
    public void create(Uye u) {
        try {
            PreparedStatement pst = this.connect().prepareStatement("insert into uye(adiSoyadi) values=?");
            pst.setString(1, u.getAdiSoyadi());
            pst.executeUpdate();
            pst.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Uye> read(int page, int pageSize) {
        List<Uye> list = new ArrayList<>();
        //pagination limit
        int start = (page-1)*pageSize;
        try {
            PreparedStatement pst = this.connect().prepareStatement("select * from uye order by uyeId asc limit "+start+","+pageSize);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Uye tmp = new Uye();
                tmp.setUyeId(rs.getLong("uyeId"));
                tmp.setTcNo(rs.getLong("tcNo"));
                tmp.setAdiSoyadi(rs.getString("adiSoyadi"));
                tmp.setTelNo(rs.getLong("telNo"));
                tmp.setMeslek(rs.getString("meslek"));
                tmp.setOgrenimDurumu(rs.getString("ogrenimDurumu"));
                list.add(tmp);
                
            }
            pst.close();
            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void update(Uye u) {
        try {
            PreparedStatement pst = this.connect().prepareStatement("update uye set adiSoyadi=? where uyeId=?");
            pst.setString(1, u.getAdiSoyadi());
            pst.setLong(2, u.getUyeId());
            pst.executeUpdate();
            pst.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(Uye u) {
        try {
            PreparedStatement pst = this.connect().prepareStatement("delete from uye where uyeId=?");
            pst.setLong(1, u.getUyeId());
            pst.executeUpdate();
            pst.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

        public int count() {
            int count=0;
            try {
            PreparedStatement pst = this.connect().prepareStatement("select count(uyeId) as uyeCount from uye");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count=rs.getInt("uyeCount");
            pst.close();
            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return count;
    }

    

    public DernekDAO getdDao() {
        if(this.dDao==null)
            this.dDao=new DernekDAO();
        return dDao;
    }

    public void setdDao(DernekDAO dDao) {
        this.dDao = dDao;
    }
    

}
