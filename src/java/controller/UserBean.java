/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDAO;
import entity.User;

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
public class UserBean implements Serializable {

    private UserDAO dao;
    private User entity;
    
    private LoginBean loginBean;

    private int page = 1;
    private int pageSize = 5;
    private int pageCount;

    public LoginBean getLoginBean() {
        if(this.loginBean == null){
            this.loginBean = new LoginBean();
        }
        return this.loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }
    
    public UserBean() {
    }

    public User getById(int id) {
        return this.getDao().getById(id);
    }

    public String create() {
        this.getDao().create(entity);

        this.entity = null;

        return "/login.xhtml";
    }

    public List<User> getUserList() {
        return this.getDao().read(this.page, this.pageSize);
    }

    public List<User> getUserListIndex() {
        return this.getDao().read(this.page, 3);
    }
    
    public String updateUser(){
        this.setEntity(this.getLoginBean().getSessionUser());
        return "/update_user";
    }
    
    public String updateForm(User c) {
        this.entity = c;

        return "/admin/user/update";
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
    
    public List<Integer> getPageList(){
        List<Integer> list;
        list = new ArrayList<Integer>();
        
        for (int i = 1; i < this.getPageCount() + 1; i++) {
            list.add((Integer)i);
        }
        
        return list;
    }

    public String update() {
        this.getDao().update(entity);
        this.entity = null;

        return "/index";
    }

    public void update(User c) {
        this.getDao().update(c);
        this.entity = null;
    }

    public void delete(User c) {
        this.getDao().delete(c);
    }

    public UserDAO getDao() {
        if (this.dao == null) {
            this.dao = new UserDAO();
        }
        return this.dao;
    }

    public void setDao(UserDAO dao) {
        this.dao = dao;
    }

    public User getEntity() {
        if (this.entity == null) {
            this.entity = new User();
        }
        return this.entity;
    }

    public void setEntity(User entity) {
        this.entity = entity;
    }

    public int getPage() {
        return this.page;
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
        return this.pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

}
