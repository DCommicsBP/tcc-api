package br.com.daione.pavan.controller;

import br.com.daione.pavan.Util.UtilErrors;
import br.com.daione.pavan.Util.UtilType;
import br.com.daione.pavan.entity.TypeEntity;
import br.com.daione.pavan.model.TypeModel;
import br.com.daione.pavan.service.TypeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Stream;

@RestController
public class TypesController {

    @Autowired
    private TypeService service;

    Log logger = LogFactory.getLog(TypesController.class);

    @GetMapping("")
    public ResponseEntity<?> getAllTypes(){
        Flux<TypeModel> typeModelFlux = this.service.listTypes().doOnError(x -> {
            UtilErrors.badRequest("Não foi possível encontrar um registro " + x);
        });

        return ResponseEntity.status(HttpStatus.CREATED).body(typeModelFlux );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getType(@PathVariable String id){
        Mono<TypeModel> typeModelMono = this.service.getType(id).doOnError(x -> {
            UtilErrors.badRequest("Não foi possível encontrar o registro solicitado. ");
        });
        return ResponseEntity.status(HttpStatus.OK).body(typeModelMono);
    }


    @PostMapping
    public ResponseEntity<?> newTypeModel(@RequestBody TypeModel typeModel){
        UtilType.validate(typeModel);
        Mono<TypeModel> typeModelMono = this.service.createType(typeModel)
                .doOnError(x -> {
                    UtilErrors.internalError("Não foi possível salvar o novo registro.");
                });

        return ResponseEntity.status(HttpStatus.CREATED).body(typeModel);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateTypeModel(@PathVariable String id, @RequestBody TypeModel typeModel){
        UtilType.validate(typeModel);
        Mono<TypeModel> typeModelMono = this.service.patchType(typeModel, id)
                .map(x-> {
                    this.service.save(x).subscribe();
                    return UtilType.typeEntityToTypeModel(x);})
                .doOnError(x -> {
                    UtilErrors.internalError("Não foi possível atualizar o registro.");
                });
        return  ResponseEntity.ok(typeModelMono);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> newTypeModel(@PathVariable String id, @RequestBody TypeModel typeModel){
        UtilType.validate(typeModel);
        this.service.deleteType(id)
                .doOnError(x-> { UtilErrors.internalError("Não foi possível atualizar o registro.");});
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PostMapping("/post-list")
    public ResponseEntity<?> saveList( @RequestBody List<TypeModel> typeModel){
        this.service.saveAll(typeModel.stream());
        return ResponseEntity.status(HttpStatus.CREATED).body("Tudo ok");
    }
}
