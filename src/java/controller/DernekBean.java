/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DernekDAO;
import entity.Dernek;
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
public class DernekBean implements Serializable {

    private DernekDAO dao;
    private Dernek entity;

    //CRUD
    public String create() {
        this.getDao().create(entity);
        this.entity = new Dernek();
        return "/dernek/list";
    }

    public Dernek getById(Long id) {
        return this.getDao().getById(id);
    }

    
    public List<Dernek> getRead() {
        return this.getDao().read();
    }

    public String updateForm(Dernek u) {
        this.entity = u;
        return "/dernek/update";
    }

    public String update() {
        this.getDao().update(entity);
        this.entity = new Dernek();
        return "/dernek/list";
    }

    public void delete(Dernek u) {
        this.getDao().delete(u);
    }

    public DernekBean() {
    }

    public DernekDAO getDao() {
        if (this.dao == null) {
            this.dao = new DernekDAO();
        }
        return dao;
    }

    public void setDao(DernekDAO dernek) {
        this.dao = dernek;
    }

    public Dernek getEntity() {
        if (this.entity == null) {
            this.entity = new Dernek();
        }
        return entity;
    }

    public void setEntity(Dernek entity) {
        this.entity = entity;
    }

}
