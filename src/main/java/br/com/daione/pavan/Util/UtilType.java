package br.com.daione.pavan.Util;

import br.com.daione.pavan.entity.TypeEntity;
import br.com.daione.pavan.model.TypeModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public abstract class UtilType {

    public static TypeModel typeEntityToTypeModel(TypeEntity entity){
        return new TypeModel(entity.getNameType(), entity.getTranslateNameType(), entity.getId());
    }

    public static TypeEntity typeModelToTypeEntity(TypeModel model){
        return new TypeEntity(model.getGoogleMapsNameType(), model.getNameTranslatedPortuguese(), model.getId());
    }

    public static TypeModel updateModel(TypeModel oldModel,  TypeModel newModel){

        oldModel.setGoogleMapsNameType(StringUtils.isEmpty(newModel.getGoogleMapsNameType())? oldModel.getGoogleMapsNameType(): newModel.getGoogleMapsNameType());
        oldModel.setNameTranslatedPortuguese(StringUtils.isEmpty(newModel.getNameTranslatedPortuguese())? oldModel.getNameTranslatedPortuguese(): newModel.getNameTranslatedPortuguese());
        validate(oldModel);
        return  oldModel;
    }

    public static void validate(TypeModel typeModel){
        if(StringUtils.isEmpty(typeModel.getNameTranslatedPortuguese()))
            UtilErrors.badRequest("Você não pode inserir um novo registro sem a tradução em português no registro");

        if(StringUtils.isEmpty(typeModel.getGoogleMapsNameType()))
            UtilErrors.badRequest("Você não pode inserir o registro sem o tipo.");
    }
}
