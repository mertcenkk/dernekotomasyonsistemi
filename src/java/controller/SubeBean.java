/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.SubeDAO;
import entity.Sube;
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
public class SubeBean implements Serializable{
    private SubeDAO dao;
    private Sube entity;

    //CRUD
    public String create() {
        this.getDao().create(entity);
        this.entity = new Sube();
        return "/sube/list";
    }

    public List<Sube> getRead() {
        return this.getDao().read();
    }

    public String updateForm(Sube u) {
        this.entity = u;
        return "/sube/update";
    }

    public String update() {
        this.getDao().update(entity);
        this.entity = new Sube();
        return "/sube/list";
    }

    public void delete(Sube u) {
        this.getDao().delete(u);
    }

    public SubeBean() {
    }

    public SubeDAO getDao() {
        if (this.dao == null) {
            this.dao = new SubeDAO();
        }
        return dao;
    }

    public void setDao(SubeDAO uye) {
        this.dao = uye;
    }

    public Sube getEntity() {
        if (this.entity == null) {
            this.entity = new Sube();
        }
        return entity;
    }

    public void setEntity(Sube entity) {
        this.entity = entity;
    }

}
