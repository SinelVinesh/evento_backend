package mg.sinel.evento.controllers;

import lombok.RequiredArgsConstructor;
import mg.sinel.evento.services.StorageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static custom.springutils.util.ControllerUtil.returnSuccess;

@RestController
@RequestMapping("files")
@RequiredArgsConstructor
public class FileUpoadController {
    private final StorageService service;

    @PostMapping("")
    public ResponseEntity<?> saveFile(@RequestParam("file") MultipartFile file) throws Exception {
        return returnSuccess(service.store(file), HttpStatus.OK);
    }

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) throws Exception {
        return new ResponseEntity<>(service.loadAsResource(filename), HttpStatus.OK);
    }
}
