/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Statement;
import java.sql.ResultSet;
import util.DBConnection;
import entity.Uye;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kmert
 */
public class UyeDAO extends DBConnection {

    //CRUD
    public void create(Uye u) {
        try {
            Statement st = this.connect().createStatement();
            st.executeUpdate("insert into uye(adiSoyadi) values ('" + u.getAdiSoyadi() + "')");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Uye> read() {
        List<Uye> list = new ArrayList<>();
        try {
            Statement st = this.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from uye order by uyeId");
            while (rs.next()) {
                Uye tmp = new Uye(rs.getInt("uyeId"), rs.getInt("tcNo"), rs.getString("adiSoyadi"), rs.getInt("telNo"), rs.getString("meslek"), rs.getString("ogrenimDurumu"));
                list.add(tmp);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void update(Uye u) {
        try {
            Statement st = this.connect().createStatement();
            st.executeUpdate("update uye set adiSoyadi='" + u.getAdiSoyadi() + "' where uyeId=" + u.getUyeId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(Uye u) {
        try {
            Statement st = this.connect().createStatement();
            st.executeUpdate("delete from uye where uyeId=" + u.getUyeId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
