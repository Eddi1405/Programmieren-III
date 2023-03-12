package de.trio.imageshare.web.Service;

import de.trio.imageshare.web.entities.PictureDaten;
import de.trio.imageshare.web.Repository.PictureRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;


@Service
public class IndexService {
    private final PictureRepository dataInterface;
    private final PlatformTransactionManager transactionManager;
    private final Timer timer;

    public IndexService(PictureRepository dataInterface, PlatformTransactionManager transactionManager) {
        this.dataInterface = dataInterface;
        this.transactionManager = transactionManager;
        this.timer = new Timer();
    }

    public void saveData(String name, MultipartFile file, String title, String beschreibung, String kategorie, int zeit,String benutzer) throws IOException {
        PictureDaten data = new PictureDaten();
        data.setBildname(name);
        data.setBildpfad("/indexShow/" + name);
        data.setBild(file.getBytes());
        data.setTitle(title);
        data.setBeschreibung(beschreibung);
        data.setKategorie(kategorie);
        data.setZeit(zeit);
        if(!benutzer.isEmpty()){
            data.setBenutzer(benutzer);
        }
        dataInterface.save(data);
        if(zeit != 99){
            deleteDataAfter(name,zeit*60,dataInterface);
        }
    }

    public PictureDaten getNamebybildname(String bildname, PictureRepository pictureRepository) {
        return pictureRepository.findBybildname(bildname).orElse(null);
    }

    public List<PictureDaten> getUserbybenutzer(String benutzer, PictureRepository pictureRepository) {
        List<PictureDaten> data = pictureRepository.findBybenutzer(benutzer);
        return data;
    }

    public void deleteDataAfter(String bildname, int delay, PictureRepository pictureRepository) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //wird verwendet, um die Löschoperation in einer Transaktion zu kapseln und sicherzustellen
                TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
                transactionTemplate.execute(status -> {
                    PictureDaten pictureDaten = pictureRepository.findBybildname(bildname).orElse(null);
                    if (pictureDaten != null) {
                        pictureRepository.delete(pictureDaten);
                    }
                    return null;
                });
            }
        };
        timer.schedule(task, delay * 1000);
    }
}
