package de.trio.imageshare.web.indexData.IndexController;

import de.trio.imageshare.web.indexData.IndexModel.Data;
import de.trio.imageshare.web.indexData.IndexModel.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class IndexController {
    @Autowired
    private DataRepository dataRepository;

    Path pfad;

    @PostMapping("/index")
    public String addPicture(@RequestParam("bildpfad")MultipartFile bildpfad, @RequestParam String title, @RequestParam String beschreibung, @RequestParam String kategorie, @RequestParam Integer zeit)throws IOException{
        Data data = new Data();
        if(!bildpfad.isEmpty()){
            byte[] bildpfadBytes = bildpfad.getBytes();
            pfad = Paths.get("src/main/resources/pictures_save/" + bildpfad.getOriginalFilename());


            try{
                BufferedImage image = ImageIO.read(new ByteArrayInputStream(bildpfadBytes));
                ImageIO.write(image,"png",pfad.toFile());
            }catch (IOException e){

            }

        }
        data.setBildpfad(String.valueOf(pfad.toFile()));
        data.setTitle(title);
        data.setBeschreibung(beschreibung);
        data.setKategorie(kategorie);
        data.setZeit(zeit);
        dataRepository.save(data);
        return "/index";
    }
    }


