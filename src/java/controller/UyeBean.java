/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UyeDAO;
import entity.Uye;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author kmert
 */
@Named
@SessionScoped
public class UyeBean implements Serializable {

    private UyeDAO dao;
    private Uye entity;

    //CRUD
    public String create() {
        this.getDao().create(entity);
        this.entity = new Uye();
        return "/uye/list";
    }

    public List<Uye> getRead() {
        return this.getDao().read();
    }

    public String updateForm(Uye u) {
        this.entity = u;
        return "/uye/update";
    }

    public String update() {
        this.getDao().update(entity);
        this.entity = new Uye();
        return "/uye/list";
    }

    public void delete(Uye u) {
        this.getDao().delete(u);
    }

    public UyeBean() {
    }

    public UyeDAO getDao() {
        if (this.dao == null) {
            this.dao = new UyeDAO();
        }
        return dao;
    }

    public void setDao(UyeDAO uye) {
        this.dao = uye;
    }

    public Uye getEntity() {
        if (this.entity == null) {
            this.entity = new Uye();
        }
        return entity;
    }

    public void setEntity(Uye entity) {
        this.entity = entity;
    }

}
