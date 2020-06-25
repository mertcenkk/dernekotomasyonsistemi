/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controller.DernekBean;
import controller.UyeBean;
import entity.Kayit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class KayitDAO extends DBConnection {

    private UyeBean uyeController;
    private DernekBean dernekController;

    public UyeBean getUyeBean() {
        if (this.uyeController == null) {
            this.uyeController = new UyeBean();
        }
        return uyeController;
    }

    public void setUyeBean(UyeBean uyeController) {
        this.uyeController = uyeController;
    }

    public DernekBean getDernekBean() {
        if (this.dernekController == null) {
            this.dernekController = new DernekBean();
        }
        return dernekController;
    }

    public void setDernekBean(DernekBean dernekController) {
        this.dernekController = dernekController;
    }


    public void create(Kayit s) {
        try {
            String query = "insert into kayit (hygiene, culture, cost_of_living) values(?,?,?) ";
            PreparedStatement st = this.connect().prepareStatement(query);

            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            String query = "select * from kayit "
                    + "order by id desc "
                    + "limit 1";
            PreparedStatement st = this.connect().prepareStatement(query);
            ResultSet rs = st.executeQuery();

            rs.next();
            s.setId(rs.getInt("id"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            String query = "insert into "
                    + "dernek_kayit (uye_id, dernek_id, kayit_id) "
                    + "values(?,?,?)";
            PreparedStatement st = this.connect().prepareStatement(query);
            st.setInt(1, s.getUye().getUyeId());
            st.setInt(2, s.getDernek().getDernekId());
            st.setInt(3, s.getId());

            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Kayit> read(int page, int pageSize) {
        List<Kayit> sList = new ArrayList<>();

        int start = (page - 1) * pageSize;

        try {
            String query = "select * from kayit order by id desc limit ?,?";
            PreparedStatement st = this.connect().prepareStatement(query);

            st.setInt(1, start);
            st.setInt(2, pageSize);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Kayit s = new Kayit();
                s.setId(rs.getInt("id"));
                try {
                    query = "select * from kayit "
                            + "where id = ? "
                            + "order by kayit_id desc";
                    PreparedStatement st2 = this.connect().prepareStatement(query);
                    st2.setInt(1, s.getId());

                    ResultSet rs2 = st2.executeQuery();

                    rs2.next();

                    s.setId(rs2.getInt("id"));
                    s.setUye(this.getUyeBean().getById(rs2.getInt("uye_id")));
                    s.setDernek(this.getDernekBean().getById(rs2.getInt("dernek_id")));

                    st2.close();
                    rs2.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                sList.add(s);
            }

            st.close();
            rs.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return sList;
    }

    public int count() {
        int count = 0;

        try {
            String query = "select count(id) as kayit_count from kayit";
            PreparedStatement st = this.connect().prepareStatement(query);
            ResultSet rs = st.executeQuery();

            rs.next();

            count = rs.getInt("kayit_count");

            st.close();
            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return count;
    }

    public void delete(Object obj) {
        Kayit s = (Kayit) obj;
        try {
            String query = "delete from kayit where id=?";
            PreparedStatement st = this.connect().prepareStatement(query);
            st.setInt(1, s.getId());

            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(Object obj) {
        Kayit s = (Kayit) obj;
        try {
            String query = "update kayit "
                    + "set uye_id = ?, dernek_id = ?";
            PreparedStatement st = this.connect().prepareStatement(query);
            st.setInt(1, s.getUye().getUyeId());
            st.setInt(2, s.getDernek().getDernekId());

            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
