package controller;

import dao.UserDAO;
import entity.Privilege;
import entity.User;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped
public class LoginBean implements Serializable {

    private User user;
    
    private Privilege currentPrivilege;

    private String username;
    private String password;

    private UserDAO userDAO;

    public String login() {
        User u = this.getUserDAO().login(this.user.getUsername(), this.user.getPassword());
        PrivilegeBean privilegeBean = new PrivilegeBean();
        if (u != null) {
            this.currentPrivilege = privilegeBean.getById(u.getPrivilegeId());
            if ("admin".equals(this.currentPrivilege.getTypeName())) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("valid_user", u);
                return "/admin/index.xhtml";
            } else if ("visitor".equals(this.currentPrivilege.getTypeName())) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("valid_user", u);
                return "/index.xhtml";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Hatalı kullanıcı adı veya şifre", "Hata"));
                return "/login.xhtml";
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Hatalı kullanıcı adı veya şifre", "Hata"));
            return "/login.xhtml";
        }
    }

    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("valid_user");
        return "/index";
    }
    
    public User getSessionUser() {
        return (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("valid_user");
    }

    public boolean isSessionSet() {
        User u = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("valid_user");
        if (u != null) {
            return true;
        } else {
            return false;
        }
    }

    public User getUser() {
        if(this.user==null)
           this.user=new User();
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDAO getUserDAO() {
        if (this.userDAO == null) {
            this.userDAO = new UserDAO();
        }
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Privilege getCurrentPrivilege() {
        return currentPrivilege;
    }

    public void setCurrentPrivilege(Privilege currentPrivilege) {
        this.currentPrivilege = currentPrivilege;
    }
}
