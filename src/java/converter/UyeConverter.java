/*
 * To change this license heauye, choose License Heauyes in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;
import dao.UyeDAO;
import entity.Uye;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
/**
 *
 * @author kmert
 */
@FacesConverter(value="uyeConverter")
public class UyeConverter implements Converter{
    private UyeDAO uyeDao;

    public UyeDAO getUyeDao() {
        if(this.uyeDao==null)
            this.uyeDao = new UyeDAO();
        return uyeDao;
    }

    public void setUyeDao(UyeDAO uyeDao) {
        this.uyeDao = uyeDao;
    }
    

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uıc, String string) {
        return this.getUyeDao().getById(Long.valueOf(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uıc, Object t) {
        
        Uye d = (Uye)t;
        return String.valueOf(d.getUyeId());
    }
    
}
