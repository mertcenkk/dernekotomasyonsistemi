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
        this.entity = new Uye();
        return "/uye/list";
    }

    public List<Uye> getRead() {
        return this.getDao().read(page,pageSize);
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
