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
    private int page=1;
    private int pageSize=10;
    private int pageCount;
    
    public void next(){
        if(this.page== this.getPageCount())
            this.page=1;
        else
            this.page++;
    }
    
    public void previous(){
        if(this.page==1)
            this.page=this.getPageCount();
        else
            this.page--;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        this.pageCount = (int) Math.ceil(this.getDao().count()/(double)pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    //CRUD
    public String create() {
        this.getDao().create(entity);
        this.entity = new Sube();
        return "/sube/list";
    }

    public List<Sube> getRead() {
        return this.getDao().read(page,pageSize);
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
