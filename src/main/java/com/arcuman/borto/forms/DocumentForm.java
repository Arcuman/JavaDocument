package com.arcuman.borto.forms;

public class DocumentForm {

    private String Title;

    private String Description;

    private String Link;

    public DocumentForm() {
    }

    public DocumentForm(String title, String description, String link) {
        Title = title;
        Description = description;
        Link = link;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }
}
