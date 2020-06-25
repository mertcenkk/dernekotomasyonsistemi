/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controller.LoginBean;
import entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

/**
 *
 * @author kmert
 */
public class UserDAO extends DBConnection {

    public User getById(int id) {
        User u = new User();

        try {
            String query = "select * from user where id = ?";
            PreparedStatement st = this.connect().prepareStatement(query);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                u.setId(rs.getInt("id"));
                u.setPrivilegeId(rs.getInt("privilege_id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setName(rs.getString("name"));
                u.setSurname(rs.getString("surname"));
                u.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return u;
    }

    public void create(User u) {
        try {
            String query = "insert into user(privilege_id, username, password, name, surname, email) values(?, ?, ?, ?, ?, ?)";
            PreparedStatement st = this.connect().prepareStatement(query);

            st.setInt(1, 1);//privilege_id
            st.setString(2, u.getUsername());
            st.setString(3, u.getPassword());
            st.setString(4, u.getName());
            st.setString(5, u.getSurname());
            st.setString(6, u.getEmail());

            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> read(int page, int pageSize) {
        List<User> uList = new ArrayList<>();

        int start = (page - 1) * pageSize;

        try {
            String query = "select * from user order by id asc limit ?, ?";
            PreparedStatement st = this.connect().prepareStatement(query);
            st.setInt(1, start);
            st.setInt(2, pageSize);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setPrivilegeId(rs.getInt("privilege_id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setName(rs.getString("name"));
                u.setSurname(rs.getString("surname"));
                u.setEmail(rs.getString("email"));

                uList.add(u);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return uList;
    }

    public int count() {
        int count = 0;

        try {
            String query = "select count(id) as user_count from user";
            PreparedStatement st = this.connect().prepareStatement(query);
            ResultSet rs = st.executeQuery();
            rs.next();

            count = rs.getInt("user_count");

            st.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return count;
    }

    public User login(String username, String password) {
        User u = null;
        try {
            String query = "select * from user where username =? and password =?";
            PreparedStatement st = this.connect().prepareStatement(query);
            st.setString(1, username);
            st.setString(2, password);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setEmail(rs.getString("email"));
                u.setPrivilegeId(rs.getInt("privilege_id"));
                u.setSurname(rs.getString("surname"));
                u.setName(rs.getString("name"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return u;
    }

    public void delete(Object obj) {
        User u = (User) obj;
        try {
            String query = "delete from user where id = ?";
            PreparedStatement st = this.connect().prepareStatement(query);
            st.setInt(1, u.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(Object obj) {
        User u = (User) obj;
        LoginBean loginBean = new LoginBean();
        try {
            String query = "update user set username = ?, password = ?, name = ?, surname= ?, email = ? where id = ?";
            PreparedStatement st = this.connect().prepareStatement(query);
            st.setString(1, u.getUsername());
            st.setString(2, u.getPassword());
            st.setString(3, u.getName());
            st.setString(4, u.getSurname());
            st.setString(5, u.getEmail());
            st.setInt(6, loginBean.getSessionUser().getId());

            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
