/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;
import dao.DernekDAO;
import entity.Dernek;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
/**
 *
 * @author kmert
 */
@FacesConverter(value="derConverter")
public class DernekConverter implements Converter{
    private DernekDAO derDao;

    public DernekDAO getDerDao() {
        if(this.derDao==null)
            this.derDao = new DernekDAO();
        return derDao;
    }

    public void setDerDao(DernekDAO derDao) {
        this.derDao = derDao;
    }
    

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uıc, String string) {
        return this.getDerDao().getById(Long.valueOf(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uıc, Object t) {
        
        Dernek d = (Dernek)t;
        return String.valueOf(d.getDernekId());
    }
    
}
