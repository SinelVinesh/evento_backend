package custom.springutils.controller;

import static custom.springutils.util.ControllerUtil.returnSuccess;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import custom.springutils.model.HasId;
import custom.springutils.service.Service;

/*
* How to use:
*   1- Create a controller class that extends this class
*   2- create a service that extends CrudService
*   3- Add @RequestMapping annotation to the class
* Then you are good for CRUD operations
* */

public class CrudController<E extends HasId, S extends Service<E>, C> {

    protected final S service;

    public CrudController(S service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody E obj) throws Exception {
        return returnSuccess(service.create(obj), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) throws Exception {
        return returnSuccess(service.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("")
    public ResponseEntity<?> findAll(C filter, @RequestParam(required = false) Integer page) throws Exception {
        return returnSuccess(service.search(filter, page), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody E obj) throws Exception {
        obj.setId(id);
        return returnSuccess(service.update(obj), HttpStatus.OK);
    }

    @GetMapping("/pdf")
    public ResponseEntity<?> createPDF () throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        String filename = "liste.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return new ResponseEntity<>(service.createPDF(), headers, HttpStatus.OK);
    }

}
