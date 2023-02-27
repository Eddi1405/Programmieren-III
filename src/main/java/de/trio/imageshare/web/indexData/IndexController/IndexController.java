package de.trio.imageshare.web.indexData.IndexController;

import de.trio.imageshare.web.indexData.IndexModel.Data;
import de.trio.imageshare.web.indexData.IndexModel.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    @Autowired
    private DataRepository dataRepository;

    @PostMapping("/index")
    public String addPicture(@RequestParam String bildpfad, @RequestParam String title, @RequestParam String beschreibung, @RequestParam String kategorie, @RequestParam Integer zeit){
        Data data = new Data();
        data.setBildpfad(bildpfad);
        data.setTitle(title);
        data.setBeschreibung(beschreibung);
        data.setKategorie(kategorie);
        data.setZeit(zeit);
        dataRepository.save(data);
        return "/index";
    }

}
