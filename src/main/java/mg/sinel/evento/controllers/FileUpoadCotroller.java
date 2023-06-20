package mg.sinel.evento.controllers;

import lombok.RequiredArgsConstructor;
import mg.sinel.evento.services.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static custom.springutils.util.ControllerUtil.returnSuccess;

@RestController
@RequestMapping("file-upload")
@RequiredArgsConstructor
public class FileUpoadCotroller {
    private final StorageService service;

    @PostMapping("")
    public ResponseEntity<?> saveFile(@RequestParam("file") MultipartFile file) throws Exception {
        return returnSuccess(service.store(file), HttpStatus.OK);
    }
}
