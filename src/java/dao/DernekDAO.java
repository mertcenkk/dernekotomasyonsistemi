/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Statement;
import java.sql.ResultSet;
import util.DBConnection;
import entity.Dernek;
import entity.Uye;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kmert
 */
public class DernekDAO extends DBConnection {

    //CRUD
    private UyeDAO uDao;
    public Dernek getById(Long id) {
        Dernek d = null;
        try {
            PreparedStatement pst = this.connect().prepareStatement("select * from dernek where dernekId=?");
            ResultSet rs = pst.executeQuery();
            rs.next();

            d = new Dernek();
            d.setDernekId(rs.getLong("dernekId"));
            d.setAdi(rs.getString("adi")); 
            d.setKurulusTipi(rs.getString("kurulusTipi"));
            d.setAdresi(rs.getString("adresi"));
            d.setAmaci(rs.getString("amaci"));
            d.setTelNo(rs.getLong("telNo"));
            d.setKurulusTarihi(rs.getDate("kurulusTarihi"));
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return d;
    }

    public void create(Dernek u) {
        try {
            PreparedStatement pst = this.connect().prepareStatement("insert into dernek (adi,kurulusTipi,adresi,amaci,telNo) values(?,?,?,?,?)");
            pst.setString(1, u.getAdi());
            pst.setString(2, u.getKurulusTipi());
            pst.setString(3, u.getAdresi());
            pst.setString(4, u.getAdresi());
            pst.setLong(5, u.getTelNo());
            pst.executeUpdate();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Dernek> read() {
        List<Dernek> list = new ArrayList<>();
        try {
            PreparedStatement pst = this.connect().prepareStatement("select * from dernek order by dernekId");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Dernek tmp = new Dernek();
                tmp.setDernekId(rs.getLong("dernekId"));
                tmp.setAdi(rs.getString("adi"));
                tmp.setKurulusTipi(rs.getString("kurulusTipi"));
                tmp.setAdresi(rs.getString("adresi"));
                tmp.setAmaci(rs.getString("amaci"));
                tmp.setTelNo(rs.getLong("telNo"));
                tmp.setKurulusTarihi(rs.getDate("kurulusTarihi"));
                list.add(tmp);
            }
            pst.close();
            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void update(Dernek u) {
        try {
            Statement st = this.connect().createStatement();
            st.executeUpdate("update dernek set adi='" + u.getAdi() + "', kurulusTipi='" + u.getKurulusTipi() + "', adresi='" + u.getAdresi() + "', amaci='" + u.getAmaci() + "', telNo='" + u.getTelNo() + "' where dernekId=" + u.getDernekId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(Dernek u) {
        try {
            PreparedStatement st = this.connect().prepareStatement("delete from dernek where dernekId=" + u.getDernekId());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }

    public UyeDAO getuDao() {
        if(this.uDao==null)
            this.uDao=new UyeDAO();
        return uDao;
    }

    public List<Dernek> getUyeDernekleri(Long uyeId) {
        List<Dernek> uyeDernekleri = new ArrayList<>();
        try{
            PreparedStatement pst = this.connect().prepareStatement("select * from kayit where uyeId="+uyeId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                uyeDernekleri.add(this.getById(rs.getLong("uyeId")));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return uyeDernekleri;
    }


}
