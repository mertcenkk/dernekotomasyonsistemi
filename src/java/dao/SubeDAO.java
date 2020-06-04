/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Sube;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List; 
import util.DBConnection;

/**
 *
 * @author kmert
 */
public class SubeDAO extends DBConnection {

    //CRUD
    public void create(Sube u) {
        try {
            Statement st = this.connect().createStatement();
            st.executeUpdate("insert into sube(ad,adres) values ('" + u.getAd() + "','"+u.getAdres()+"')");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Sube> read() {
        List<Sube> list = new ArrayList<>();
        try {
            Statement st = this.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from sube order by id");
            while (rs.next()) {
                Sube tmp = new Sube(rs.getInt("id"),rs.getString("ad"), rs.getDate("tarih"), rs.getString("adres"));
                list.add(tmp);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void update(Sube u) {
        try {
            Statement st = this.connect().createStatement();
            st.executeUpdate("update sube set ad='" + u.getAd() + "',adres='"+u.getAdres()+"' where id=" + u.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(Sube u) {
        try {
            Statement st = this.connect().createStatement();
            st.executeUpdate("delete from sube where id=" + u.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
