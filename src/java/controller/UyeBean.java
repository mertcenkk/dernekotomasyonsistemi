/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UyeDAO;
import entity.Dernek;
import entity.Uye;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
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
    private int pageSize=5;
    private int pageCount;
    
    @Inject
    private DernekBean dernekBean;
    
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
    
    public Uye getById(int id) {
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
        this.entity = new Uye();
        return "/admin/uye/list";
    }

    public List<Uye> getRead() {
        return this.getDao().read(this.page, this.pageSize);
    }
    public List<Uye> getReadIndex() {
        return this.getDao().read(this.page, 3);
    }    

    public String updateForm(Uye u) {
        this.entity = u;
        return "/admin/uye/update";
    }

    public String update() {
        this.getDao().update(entity);
        this.entity = new Uye();
        return "/admin/uye/list";
    }
    
    public void deleteConfirmation(Uye d) {
        this.entity = d;
    }

    public void delete() {
        this.getDao().delete(this.entity);
        this.entity = null;
    }

    public UyeBean() {
    }

    public UyeDAO getDao() {
        if (this.dao == null) {
            this.dao = new UyeDAO();
        }
        return this.dao;
    }

    public void setDao(UyeDAO uye) {
        this.dao = uye;
    }

    public Uye getEntity() {
        if (this.entity == null) {
            this.entity = new Uye();
        }
        return this.entity;
    }

    public void setEntity(Uye entity) {
        this.entity = entity;
    }

    public DernekBean getDernekBean() {
        return dernekBean;
    }

    public void setDernekBean(DernekBean dernekBean) {
        this.dernekBean = dernekBean;
    }

    
    

}
