package br.com.daione.pavan.model;

public class TypeModel {
    private String googleMapsNameType;
    private String nameTranslatedPortuguese;
    private String id;

    public String getGoogleMapsNameType() {
        return googleMapsNameType;
    }

    public void setGoogleMapsNameType(String googleMapsNameType) {
        this.googleMapsNameType = googleMapsNameType;
    }

    public String getNameTranslatedPortuguese() {
        return nameTranslatedPortuguese;
    }

    public void setNameTranslatedPortuguese(String nameTranslatedPortuguese) {
        this.nameTranslatedPortuguese = nameTranslatedPortuguese;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public TypeModel(){

    }

    public TypeModel(String googleMapsNameType, String nameTranslatedPortuguese, String id){
        this.googleMapsNameType = googleMapsNameType;
        this.id = id;
        this.nameTranslatedPortuguese = nameTranslatedPortuguese;
    }
}
