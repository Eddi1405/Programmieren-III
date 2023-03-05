package de.trio.imageshare.web.indexData.IndexController;

import org.springframework.ui.Model;
import de.trio.imageshare.web.indexData.IndexModel.Data;
import de.trio.imageshare.web.indexData.IndexModel.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.AttributedString;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private DataRepository dataRepository;
    @Autowired
    private IndexService is;
    Path pfad;
    int nr = 0;
    String randomname;
    private RandomString rs = new RandomString();
    @PostMapping("/index")
    public String addData(@RequestParam("bildpfad")MultipartFile bild, @RequestParam String title, @RequestParam String beschreibung, @RequestParam String kategorie, @RequestParam Integer zeit ,Model model )throws IOException {
        Data data = new Data();
        randomname = rs.RandomString(6);
        is.saveImage(randomname, bild, title, beschreibung, kategorie, zeit);
        Data dataList = is.getImageByName(randomname, dataRepository);
        model.addAttribute("dataList", dataList);
        return "redirect:/" + randomname;
    }

    @GetMapping  (value = "/{randomname}")
    public String getIndexShowPage(Model model, @PathVariable("randomname") String name) {
        Data dataList = is.getImageByName(randomname, dataRepository);
        model.addAttribute("dataList", dataList);
        return "indexShow";
    }

    @GetMapping("indexShow/{name}")
    public ResponseEntity<byte[]> getName(@PathVariable("name") String name) {
        IndexService indexService = new IndexService();
        Data data = indexService.getImageByName(name,dataRepository);
        if (data != null) {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(data.getBild());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}


