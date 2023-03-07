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

import java.io.IOException;


@Controller
public class IndexController {
    @Autowired
    private DataRepository dataRepository;
    @Autowired
    private IndexService is;
    String randomname;
    private final RandomString rs = new RandomString();
    @PostMapping("/index")
    public String addData(@RequestParam("bildpfad")MultipartFile bild, @RequestParam String title, @RequestParam String beschreibung, @RequestParam String kategorie, @RequestParam String urltext, @RequestParam Integer zeit)throws IOException {
        if(urltext.isEmpty()){
            do{
                randomname = rs.RandomString(6);
            }while (dataRepository.existsBybildname(randomname));
            is.saveImage(randomname, bild, title, beschreibung, kategorie, zeit);
            return "redirect:/" + randomname;
        }else {
            if(dataRepository.existsBybildname(urltext)){
                return "index";
            }else {
                randomname = urltext;
                is.saveImage(randomname, bild, title, beschreibung, kategorie, zeit);
                return "redirect:/" + randomname;
            }



        }



    }

    @GetMapping  (value = "/{randomname}")
    public String getIndexShowPage(Model model) {
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


