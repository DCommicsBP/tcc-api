package br.com.daione.pavan.entity;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "TYPE")
public class TypeEntity {

    @Indexed
    private String id;

    private String nameType;

    private String translateNameType;

    public TypeEntity(){}

    public TypeEntity(String googleMapsNameType, String nameTranslatedPortuguese, String id) {
        this.translateNameType = nameTranslatedPortuguese;
        this.nameType = googleMapsNameType;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    public String getTranslateNameType() {
        return translateNameType;
    }

    public void setTranslateNameType(String translateNameType) {
        this.translateNameType = translateNameType;
    }
}
