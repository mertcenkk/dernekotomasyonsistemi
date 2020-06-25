/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import dao.KayitDAO;
import entity.Dernek;
import entity.Kayit;
import entity.Uye;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author kmert
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


@Named
@SessionScoped
public class KayitBean implements Serializable {

    private KayitDAO dao;
    private Kayit entity;

    private int page = 1;
    private int pageSize = 5;
    private int pageCount;

    
    public void create(Dernek dernek, Uye uye, Kayit kayit) {
        this.setEntity(kayit);
        this.entity.setDernek(dernek);
        this.entity.setUye(uye);
        
        this.getDao().create(entity);
        this.entity = null;
    }

    public List<Integer> getScoresList(){
        List<Integer> list;
        list = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            list.add((Integer) i);
        }

        return list;
    }
    
    public List<Kayit> getScoreList() {
        return this.getDao().read(this.page, this.pageSize);
    }

    public String updateForm(Kayit k) {
        this.entity = k;

        return "update?faces-redirect=true";
    }

    public void next() {
        if (this.page == this.getPageCount()) {
            this.page = 1;
        } else {
            this.page++;
        }
    }

    public void previous() {
        if (this.page == 1) {
            this.page = this.getPageCount();
        } else {
            this.page--;
        }
    }

    public List<Integer> getPageList() {
        List<Integer> list;
        list = new ArrayList<>();

        for (int i = 1; i < this.getPageCount() + 1; i++) {
            list.add((Integer) i);
        }

        return list;
    }

    public String update() {
        this.getDao().update(entity);
        this.entity = null;

        return "list?faces-redirect=true";
    }
    public void deleteConfirmation(Kayit d) {
        this.entity = d;
    }

    public void delete(Kayit k) {
        this.getDao().delete(this.entity);
        this.entity = null;
    }

    public KayitDAO getDao() {
        if (this.dao == null) {
            this.dao = new KayitDAO();
        }
        return dao;
    }

    public void setDao(KayitDAO dao) {
        this.dao = dao;
    }

    public Kayit getEntity() {
        if (this.entity == null) {
            this.entity = new Kayit();
        }
        return entity;
    }

    public void setEntity(Kayit entity) {
        this.entity = entity;
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
        this.pageCount = (int) Math.ceil(this.getDao().count() / (double) this.pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

}

