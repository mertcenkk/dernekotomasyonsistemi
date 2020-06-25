/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DernekDAO;
import entity.Dernek;
import java.io.Serializable;
import java.util.ArrayList;
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
    private int page=1;
    private int pageSize=5;
    private int pageCount;
    public List<Integer> getPageList() {
        List<Integer> list;
        list = new ArrayList<>();

        for (int i = 1; i < this.getPageCount() + 1; i++) {
            list.add((Integer) i);
        }

        return list;
    }
    
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
    
    public Dernek getById(int id) {
        return this.getDao().getById(id);
    }

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        this.pageCount = (int) Math.ceil(this.getDao().count()/(double)pageSize);
        return this.pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    
    

    //CRUD
    public String create() {
        this.getDao().create(entity);
        this.entity = new Dernek();
        return "/admin/dernek/list";
    }

    public List<Dernek> getRead() {
        return this.getDao().read(this.page, this.pageSize);
    }

    public List<Dernek> getReadIndex() {
        return this.getDao().read(this.page, 3);
    }

    public String updateForm(Dernek u) {
        this.entity = u;
        return "/admin/dernek/update";
    }

    public String update() {
        this.getDao().update(entity);
        this.entity = new Dernek();
        return "/admin/dernek/list";
    }
    public void deleteConfirmation(Dernek d) {
        this.entity = d;
    }

    public void delete() {
        this.getDao().delete(this.entity);
        this.entity = null;
    }

    public DernekBean() {
    }

    public DernekDAO getDao() {
        if (this.dao == null) {
            this.dao = new DernekDAO();
        }
        return this.dao;
    }

    public void setDao(DernekDAO dernek) {
        this.dao = dernek;
    }

    public Dernek getEntity() {
        if (this.entity == null) {
            this.entity = new Dernek();
        }
        return this.entity;
    }

    public void setEntity(Dernek entity) {
        this.entity = entity;
    }

}
