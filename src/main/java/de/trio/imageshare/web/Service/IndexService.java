package de.trio.imageshare.web.Service;

import de.trio.imageshare.web.entities.PictureDaten;
import de.trio.imageshare.web.Repository.PictureRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
public class IndexService {
    private final PictureRepository dataInterface;

    public IndexService(PictureRepository dataInterface) {
        this.dataInterface = dataInterface;
    }

    public void saveData(String name, MultipartFile file, String title, String beschreibung, String kategorie, int zeit) throws IOException {
        PictureDaten data = new PictureDaten();
        data.setBildname(name);
        data.setBildpfad("/indexShow/" + name);
        data.setBild(file.getBytes());
        data.setTitle(title);
        data.setBeschreibung(beschreibung);
        data.setKategorie(kategorie);
        data.setZeit(zeit);
        dataInterface.save(data);
    }

    public PictureDaten getNamebybildname(String bildname, PictureRepository pictureRepository) {
        return pictureRepository.findBybildname(bildname).orElse(null);
    }

}
