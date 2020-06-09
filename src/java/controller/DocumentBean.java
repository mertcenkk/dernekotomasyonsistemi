/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DocumentDAO;
import entity.Document;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

/**
 *
 * @author kmert
 */
@Named
@SessionScoped
public class DocumentBean implements Serializable {
    private Document entity;
    private List<Document> entityList;
    private DocumentDAO dao;
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
    
    private Part doc;
    private String uploadTo="C:\\Users\\kmert\\Documents\\NetBeansProjects\\Dernek Otomasyonu\\web\\resources\\upload\\";
    public String upload(){
        try{
            InputStream input = doc.getInputStream();
            File f = new File(uploadTo+doc.getSubmittedFileName());
            Files.copy(input, f.toPath());
            
            entity=this.getEntity();
            entity.setFilePath(f.getParent());
            entity.setFileName(f.getName());
            entity.setFileType(doc.getContentType());
            
            this.getDao().create(entity);
            
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        
        return "/document/list";
        
        
    }

    public String getUploadTo() {
        return uploadTo;
    }
    
    

    public Document getEntity() {
        if(this.entity==null)
            this.entity= new Document();
        return entity;
    }

    public void setEntity(Document entity) {
        this.entity = entity;
    }

    public List<Document> getRead() {
        this.entityList = this.getDao().read(page,pageSize);
        return entityList;
    }


    public DocumentDAO getDao() {
        if(this.dao==null)
            this.dao=new DocumentDAO();
        return dao;
    }

    public void setDao(DocumentDAO dao) {
        this.dao = dao;
    }

    public Part getDoc() {
        return doc;
    }

    public void setDoc(Part doc) {
        this.doc = doc;
    }


    
}
