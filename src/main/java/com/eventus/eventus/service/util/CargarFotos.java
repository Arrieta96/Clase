package com.eventus.eventus.service.util;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileOutputStream;

@Service
public class CargarFotos {

    private static final String TABLE_NAME = "User";

    public boolean uploadFileToS3(MultipartFile file) {

        if (file.isEmpty()) {
            throw new RuntimeException("El file esta vacio");
        } else {
            try {
                File sourceFile = new File("src/main/resources/static/fotoEventos/" + file.getOriginalFilename());
                sourceFile.createNewFile();

                try(FileOutputStream fout = new FileOutputStream(sourceFile)){
                    fout.write(file.getBytes());
                }catch (Exception e){
                    e.printStackTrace();
                }
                return true;
            } catch (Exception e) {
                e.getMessage();
            }
        return true;

        }
    }
}
