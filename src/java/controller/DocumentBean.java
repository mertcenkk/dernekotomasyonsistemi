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
    private List<Document> documentList;
    private DocumentDAO dao;
    private Part doc;

    private final String uploadPath = "C:\\Users\\kmert\\Desktop\\Dernek Otomasyonu\\web\\upload/";

    public Document getById(int id) {
        return this.getDao().getById(id);
    }

    public String upload() {
        try {
            InputStream input = doc.getInputStream();
            File f = new File(uploadPath + doc.getSubmittedFileName());
            Files.copy(input, f.toPath());
            this.entity = this.getEntity();
            this.entity.setFilePath(f.getParent());
            this.entity.setFileName(f.getName());
            this.entity.setFileType(doc.getContentType());

            this.getDao().create(this.entity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "/admin/document/list";
    }

    public Document getEntity() {
        if (this.entity == null) {
            this.entity = new Document();
        }
        return entity;
    }

    public void setEntity(Document entity) {
        this.entity = entity;
    }

    public List<Document> getRead() {
        return this.getDao().read();
    }

    public void setRead(List<Document> documentList) {
        this.documentList = documentList;
    }

    public DocumentDAO getDao() {
        if (this.dao == null) {
            this.dao = new DocumentDAO();
        }
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

    public String getUploadTo() {
        return uploadPath;
    }
}
