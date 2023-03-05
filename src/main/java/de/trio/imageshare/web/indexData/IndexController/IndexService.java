package de.trio.imageshare.web.indexData.IndexController;

import de.trio.imageshare.web.indexData.IndexModel.Data;
import de.trio.imageshare.web.indexData.IndexModel.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
public class IndexService {
    @Autowired
    private DataRepository dataInterface;

    public List<Data> findAll(){
        return dataInterface.findAll();
    }

    public void saveImage(String name, MultipartFile file, String title, String beschreibung, String kategorie, int zeit) throws IOException {
        Data data = new Data();
        data.setBildname(name);
        data.setBildpfad("/indexShow/" + name);
        data.setBild(file.getBytes());
        data.setTitle(title);
        data.setBeschreibung(beschreibung);
        data.setKategorie(kategorie);
        data.setZeit(zeit);
        dataInterface.save(data);
    }

    public Data getImageByName(String bildname,DataRepository dataInterface) {
        return dataInterface.findBybildname(bildname).orElse(null);
    }

}
