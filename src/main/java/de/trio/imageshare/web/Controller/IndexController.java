package de.trio.imageshare.web.Controller;

import de.trio.imageshare.web.Repository.PictureRepository;
import de.trio.imageshare.web.Service.IndexService;
import de.trio.imageshare.web.entities.PictureDaten;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Random;

/**
 * Die Klasse ist dazu da die Einträge zu erstellen und die Einträge danach anzuzeigen oder über eine bestimmte URL aufzurufen.
 */

@Controller
public class IndexController {
    private final PictureRepository pictureRepository;
    private final IndexService indexService;
    private final String VALUES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    LoginController loginController;
    String urlname;

    /**
     * In dem Constructor wird pictureRepository und indexService initialisiert.
     *
     * @param pictureRepository
     * @param indexService
     */
    public IndexController(PictureRepository pictureRepository, IndexService indexService, LoginController loginController) {
        this.pictureRepository = pictureRepository;
        this.indexService = indexService;
        this.loginController = loginController;
    }

    /**
     * Ist dafür da um /index aufzurufen.
     *
     * @return
     */

    @GetMapping(value = "/")
    public String getPage(Model model, HttpSession session) {
        loginController.navbar(model, session);
        return "index";
    }

    @GetMapping(value = "/index")
    public String getIndexPage(Model model, HttpSession session) {
        loginController.navbar(model, session);
        return "index";
    }

    /**
     * Wenn nicht eine eigene Url ausgewählt wurde wird eine zufällige Url vergeben diese wird überprüft ob sie noch nicht vorhanden ist und danach werden die
     * eingebenen Daten gespeichert. Am Ende wird man auf die jeweilige Seite weitergeleitet.
     *
     * @param bild
     * @param title
     * @param beschreibung
     * @param kategorie
     * @param urltext
     * @param zeit
     * @return
     * @throws IOException
     */
    @PostMapping("/index")
    public String addData(@RequestParam("bildpfad") MultipartFile bild, @RequestParam String title, @RequestParam String beschreibung, @RequestParam String kategorie, @RequestParam String urltext, @RequestParam int zeit) throws IOException {
        if (urltext.isEmpty()) {
            do {
                urlname = RandomValue(6);
            } while (pictureRepository.existsBybildname(urlname));
            if (loginController.user == null) {
                indexService.saveData(urlname, bild, title, beschreibung, kategorie, zeit, "null");
            } else {
                indexService.saveData(urlname, bild, title, beschreibung, kategorie, zeit, loginController.user);
            }
            return "redirect:/" + urlname;
        } else {
            if (pictureRepository.existsBybildname(urltext)) {
                return "index";
            } else {
                urlname = urltext;
                if (loginController.user == null) {
                    indexService.saveData(urlname, bild, title, beschreibung, kategorie, zeit, "null");
                } else {
                    indexService.saveData(urlname, bild, title, beschreibung, kategorie, zeit, loginController.user);
                }
                return "redirect:/" + urlname;
            }
        }
    }

    /**
     * Übergibt die Daten wenn sie vorhanden sind an die HTML Seite.
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/{name}")
    public String getIndexShowPage(Model model, @PathVariable("name") String name, HttpSession session) {
        PictureDaten data = indexService.getNamebybildname(name, pictureRepository);
        model.addAttribute("dataList", data);
        loginController.navbar(model, session);
        if (data == null) {
            return "NoData";
        } else {
            return "indexShow";
        }

    }

    /**
     * Gibt dem Bild ein Pfad über den man das Bild aufrufen kann.
     *
     * @param name
     * @return
     */
    @GetMapping("indexShow/{name}")
    public ResponseEntity<byte[]> getName(@PathVariable("name") String name) {
        PictureDaten data = indexService.getNamebybildname(name, pictureRepository);
        if (data != null) {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(data.getBild());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    /**
     * Erstellt einen belibig lange Zeichenfolge
     *
     * @param length
     * @return
     */
    public String RandomValue(int length) {
        char[] value = new char[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(VALUES.length());
            value[i] = VALUES.charAt(index);
        }
        String url = new String(value);
        return url;
    }
}


