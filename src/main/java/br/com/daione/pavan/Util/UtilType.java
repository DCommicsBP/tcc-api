package br.com.daione.pavan.Util;

import br.com.daione.pavan.entity.TypeEntity;
import br.com.daione.pavan.model.TypeModel;
import io.netty.util.internal.StringUtil;
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

        oldModel.setGoogleMapsNameType(StringUtil.isNullOrEmpty(newModel.getGoogleMapsNameType())? oldModel.getGoogleMapsNameType(): newModel.getGoogleMapsNameType());
        oldModel.setNameTranslatedPortuguese(StringUtil.isNullOrEmpty(newModel.getNameTranslatedPortuguese())? oldModel.getNameTranslatedPortuguese(): newModel.getNameTranslatedPortuguese());
        validate(oldModel);
        return  oldModel;
    }

    public static void validate(TypeModel typeModel){
        if(StringUtil.isNullOrEmpty(typeModel.getNameTranslatedPortuguese()))
            UtilErrors.badRequest("Você não pode inserir um novo registro sem a tradução em português no registro");

        if(StringUtil.isNullOrEmpty(typeModel.getGoogleMapsNameType()))
            UtilErrors.badRequest("Você não pode inserir o registro sem o tipo.");
    }
}
