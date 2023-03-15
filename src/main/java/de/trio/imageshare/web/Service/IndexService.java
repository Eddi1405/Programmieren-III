package de.trio.imageshare.web.Service;

import de.trio.imageshare.web.Repository.PictureRepository;
import de.trio.imageshare.web.entities.PictureDaten;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Dieser Code definiert den Service "IndexService", der für die Verarbeitung von Bildern in der Anwendung verantwortlich ist.
 * Der Service verwendet das "PictureRepository", um mit der Datenbank zu interagieren, und das "PlatformTransactionManager",
 * um Transaktionen für das Löschen von Bildern nach einer bestimmten Zeit zu verwalten.
 * <p>
 * Der Service hat die folgenden Methoden:
 * <p>
 * saveData(): speichert die Metadaten eines Bildes (Name, Pfad, Titel, Beschreibung, Kategorie, Zeit, Benutzer)
 * und das Bild selbst in der Datenbank. Wenn die Zeit auf 99 gesetzt wird, wird das Bild nicht automatisch gelöscht.
 * <p>
 * getNamebybildname(): gibt das Bild zurück, das mit dem gegebenen Namen gespeichert ist.
 * <p>
 * getUserbybenutzer(): gibt eine Liste von Bildern zurück, die von einem bestimmten Benutzer gespeichert wurden.
 * <p>
 * deleteDataAfter(): plant eine Aufgabe, die das Bild mit dem gegebenen Namen nach einer bestimmten Zeit aus der Datenbank löscht.
 */

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

    public void saveData(String name, MultipartFile file, String title, String beschreibung, String kategorie, int zeit, String benutzer) throws IOException {
        PictureDaten data = new PictureDaten();
        data.setBildname(name);
        data.setBildpfad("/indexShow/" + name);
        data.setBild(file.getBytes());
        data.setTitle(title);
        data.setBeschreibung(beschreibung);
        data.setKategorie(kategorie);
        data.setZeit(zeit);
        data.setDatum(Date.valueOf(LocalDate.now()));
        if (!benutzer.isEmpty()) {
            data.setBenutzer(benutzer);
        }
        dataInterface.save(data);
        if (zeit != 99) {
            deleteDataAfter(name, zeit * 60, dataInterface);
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
