package br.com.daione.pavan.service;

import br.com.daione.pavan.Util.UtilType;
import br.com.daione.pavan.entity.TypeEntity;
import br.com.daione.pavan.model.TypeModel;
import br.com.daione.pavan.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Stream;

@Service
public class TypeService {
    @Autowired
    private TypeRepository repository;

    public Flux<TypeModel> listTypes() {
        Flux<TypeModel> flux = this.repository
                .findAll()
                .map(UtilType::typeEntityToTypeModel)
                .collectSortedList((a, b) -> a.getNameTranslatedPortuguese().compareTo(b.getNameTranslatedPortuguese()))
                .flatMapMany(Flux::fromIterable);
        flux.subscribe();
        return flux;
    }

    public Mono<TypeModel> getType(String typeId) {
        return this.repository
                .findById(typeId)
                .map(UtilType::typeEntityToTypeModel);
    }
    
    public Mono<TypeEntity> patchType(TypeModel newTypeModel, String typeId) {
        Mono<TypeEntity> map = this.getType(typeId)
                .map(oldModel -> UtilType.updateModel(oldModel, newTypeModel))
                .map(UtilType::typeModelToTypeEntity);
                map.subscribe();
        return map;
    }

    public Mono<TypeEntity> save(TypeEntity entity) {
        Mono<TypeEntity> save = this.repository.save(entity);
        save.subscribe();
        return save;
    }

    public Mono<TypeModel> createType(TypeModel typeModel) {
        TypeEntity typeEntity = new TypeEntity();
        typeEntity.setNameType(typeModel.getGoogleMapsNameType());
        typeEntity.setTranslateNameType(typeModel.getNameTranslatedPortuguese());
        Mono<TypeModel> map = this.repository
                .save(typeEntity).map(x -> UtilType.typeEntityToTypeModel(x));

        map.subscribe();
        return map;
    }

    public Mono<Void> deleteType(String typeId) {

        Mono<Void> monovoid = this.repository.deleteById(typeId);
        monovoid.subscribe();
        return monovoid;
    }

    public void saveAll(Stream<TypeModel> models){

        Flux<TypeModel> flux = Flux.fromStream(models);

        Flux<TypeEntity> map = flux.map(x-> UtilType.typeModelToTypeEntity(x));

        this.repository.saveAll(map).subscribe();


    }

}
