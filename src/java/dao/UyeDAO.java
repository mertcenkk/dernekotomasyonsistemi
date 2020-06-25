/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Dernek;
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
    public Uye getById(int id) {
        Uye d = null;
        try {
            PreparedStatement st = this.connect().prepareStatement("select * from uye where uyeId=?");
            ResultSet rs = st.executeQuery();
            rs.next();

            d = new Uye();
            d.setUyeId(rs.getInt("uyeId"));
            d.setTcNo(rs.getInt("tcNo")); 
            d.setAdiSoyadi(rs.getString("adiSoyadi"));
            d.setTelNo(rs.getInt("telNo"));
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
            PreparedStatement pst = this.connect().prepareStatement("insert into uye(tcNo,adiSoyadi,telNo,meslek,ogrenimDurumu) values(?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setInt(1, u.getTcNo());
            pst.setString(2, u.getAdiSoyadi());
            pst.setInt(3, u.getTelNo());
            pst.setString(4, u.getMeslek());
            pst.setString(5, u.getOgrenimDurumu());
            
            pst.executeUpdate(); 
            int uyeId=0;
            ResultSet gk = pst.getGeneratedKeys();
            
            if( gk.next()){
                uyeId = gk.getInt(1);
            }
            for(Dernek l : u.getUyeninDernekleri()){
                PreparedStatement pst2 = this.connect().prepareStatement("insert into kayit (dernekId,uyeId) values (?,?)");
                pst2.setInt(1, uyeId);
                pst2.setInt(2, l.getDernekId());
                pst2.executeUpdate();
                
            }
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
                tmp.setUyeId(rs.getInt("uyeId"));
                tmp.setTcNo(rs.getInt("tcNo"));
                tmp.setAdiSoyadi(rs.getString("adiSoyadi"));
                tmp.setTelNo(rs.getInt("telNo"));
                tmp.setMeslek(rs.getString("meslek"));
                tmp.setOgrenimDurumu(rs.getString("ogrenimDurumu"));
                tmp.setUyeninDernekleri(this.getdDao().getUyeninDernekleri(tmp.getUyeId()));
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
            PreparedStatement pst = this.connect().prepareStatement("update uye set tcNo=?, adiSoyadi=?, telNo=?, meslek=?, ogrenimDurumu=? where uyeId=?",PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setInt(1,u.getTcNo());
            pst.setString(2, u.getAdiSoyadi());
            pst.setInt(3, u.getTelNo());
            pst.setString(4, u.getMeslek());
            pst.setString(5, u.getOgrenimDurumu());
            pst.setInt(6, u.getUyeId());
            pst.executeUpdate();
            int uyeId=0;
            pst = this.connect().prepareStatement("delete from kayit uyeId=?");
            pst.setLong(1, u.getUyeId());
            pst.executeUpdate();
            for(Dernek l : u.getUyeninDernekleri()){
                PreparedStatement pst2 = this.connect().prepareStatement("insert into kayit (dernekId,uyeId) values (?,?)");
                pst2.setInt(1, uyeId);
                pst2.setInt(2, l.getDernekId());
                pst2.executeUpdate();
                
            }
            pst.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(Uye u) {
        try {
            PreparedStatement pst = this.connect().prepareStatement("delete from uye where uyeId=?");
            pst.setInt(1, u.getUyeId());
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
