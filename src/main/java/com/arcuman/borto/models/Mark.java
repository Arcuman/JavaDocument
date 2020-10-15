package com.arcuman.borto.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Mark {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer idMark;

    @ElementCollection(targetClass = TypeMark.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "mark_type",joinColumns = @JoinColumn(name = "mark_id"))
    @Enumerated(EnumType.STRING)
    private Set<TypeMark> roles;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User owner;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "document_id")
    private Document document;

    public Mark() {
    }

    public Mark(Set<TypeMark> roles, User owner, Document document) {
        this.roles = roles;
        this.owner = owner;
        this.document = document;
    }

    public Integer getIdMark() {
        return idMark;
    }

    public void setIdMark(Integer idMark) {
        this.idMark = idMark;
    }

    public Set<TypeMark> getRoles() {
        return roles;
    }

    public void setRoles(Set<TypeMark> roles) {
        this.roles = roles;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}
