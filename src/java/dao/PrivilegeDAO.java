/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.PreparedStatement;

import java.util.ArrayList;
import java.util.List;

import entity.Privilege;
import util.DBConnection;

/**
 *
 * @author kmert
 */
public class PrivilegeDAO extends DBConnection {

    public Privilege getById(int id) {
        Privilege p = new Privilege();

        try {
            String query = "select * from privilege where id = ?";
            PreparedStatement st = this.connect().prepareStatement(query);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();

            p.setId(rs.getInt("id"));
            p.setType(rs.getInt("type"));
            p.setTypeName(rs.getString("type_name"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return p;
    }

    public void create(Privilege p) {
        Privilege temp = null;

        try {
            String query = "select * from privilege order by id desc limit 1";
            PreparedStatement st = this.connect().prepareStatement(query);
            ResultSet rs = st.executeQuery();
            rs.next();
            
            temp = new Privilege();
            temp.setId(rs.getInt("id"));
            temp.setType(rs.getInt("type"));
            temp.setTypeName(rs.getString("type_name"));

            temp.setType(temp.getType() + 1);

            try {
                query = "insert into privilege(type, type_name) values("
                        + "?, ?)";
                st = this.connect().prepareStatement(query);
                st.setInt(1, temp.getType());
                st.setString(2, p.getTypeName());
                st.executeUpdate();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Privilege> read(int page, int pageSize) {
        List<Privilege> pList = new ArrayList<>();

        int start = (page - 1) * pageSize;

        try {
            String query = "select * from privilege order by id asc limit ?, ?";
            PreparedStatement st = this.connect().prepareStatement(query);
            st.setInt(1, start);
            st.setInt(2, pageSize);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Privilege p = new Privilege();

                p.setId(rs.getInt("id"));
                p.setType(rs.getInt("type"));
                p.setTypeName(rs.getString("type_name"));

                pList.add(p);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return pList;
    }

    public int count() {
        int count = 0;

        try {
            String query = "select count(id) as privilege_count from privilege";
            PreparedStatement st = this.connect().prepareStatement(query);
            ResultSet rs = st.executeQuery();
            rs.next();
            count = rs.getInt("privilege_count");

            st.close();
            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return count;
    }


    public void update(Object obj) {
        Privilege p = (Privilege) obj;
        try {
            String query = "update privilege set type_name = ? where id = ?";
            PreparedStatement st = this.connect().prepareStatement(query);
            st.setString(1, p.getTypeName());
            st.setInt(2, p.getId());

            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(Object obj) {
        Privilege p = (Privilege) obj;
        try {
            String query = "delete from privilege where id = ?";
            PreparedStatement st = this.connect().prepareStatement(query);
            st.setInt(1, p.getId());

            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
