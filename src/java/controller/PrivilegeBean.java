/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.PrivilegeDAO;
import entity.Privilege;

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
public class PrivilegeBean implements Serializable {

    private PrivilegeDAO dao;
    private Privilege entity;

    private int page = 1;
    private int pageSize = 5;
    private int pageCount;

    public PrivilegeBean() {
    }

    public Privilege getById(int id) {
        return this.getDao().getById(id);
    }

    public String create() {
        this.getDao().create(entity);

        this.entity = null;

        return "/admin/privilege/list";
    }

    public List<Privilege> getPrivilegeList() {
        return this.getDao().read(this.page, this.pageSize);
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

        List<Integer> list = new ArrayList<Integer>();
        int temp = this.getPageCount();
        for (int i = 1; i < temp + 1; i++) {
            list.add((Integer) i);
        }

        return list;
    }

    public String updateForm(Privilege p) {
        this.entity = p;

        return "/admin/privilege/update";
    }

    public String update() {
        this.getDao().update(entity);
        this.entity = null;

        return "/admin/privilege/list";
    }

    public void update(Privilege p) {
        this.getDao().update(p);
        this.entity = null;
    }

    public void deleteConfirmation(Privilege p) {
        this.entity = p;
    }

    public void delete() {
        this.getDao().delete(this.entity);
        this.entity = null;
    }

    public PrivilegeDAO getDao() {
        if (this.dao == null) {
            this.dao = new PrivilegeDAO();
        }
        return dao;
    }

    public void setDao(PrivilegeDAO dao) {
        this.dao = dao;
    }

    public Privilege getEntity() {
        if (this.entity == null) {
            this.entity = new Privilege();
        }
        return entity;
    }

    public void setEntity(Privilege entity) {
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
