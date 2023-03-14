package de.trio.imageshare.web.Controller;

import de.trio.imageshare.web.Repository.PictureRepository;
import de.trio.imageshare.web.Service.IndexService;
import de.trio.imageshare.web.entities.PictureDaten;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.sql.Date;
import java.util.List;

/**
 * Dieser Controller ist dafür da, um ein Dashboard für die des Nutzers hochgeladenen Bilder zu erstellen.
 */
@Controller
public class DashboardController {
    private final PictureRepository pictureRepository;
    private final IndexService indexService;
    String user;
    LoginController loginController;

    /**
     * Die unten genannten Parameter werden im Konstruktor übergeben.
     *
     * @param pictureRepository
     * @param indexService
     * @param loginController
     */
    public DashboardController(PictureRepository pictureRepository, IndexService indexService, LoginController loginController) {
        this.pictureRepository = pictureRepository;
        this.indexService = indexService;
        this.loginController = loginController;
    }

    /**
     * Http Get Anforderung für den Pfad dashboard.
     * Wenn loginController.user nicht null ist, ruft die Methode getUserbybenutzer des
     * IndexService auf und gibt eine Liste von PictureDaten zurück, die dann an das Model übergeben wird.
     *
     * @param model Bei Erfolg wird das Dashboard returned und bei misserfolg die NoData html.
     * @return
     */
    @GetMapping(value = "dashboard")
    public String getDashboardPage(Model model,HttpSession session) {
        loginController.navbar(model, session);
        if (loginController.user == null) {
            return "NoData";
        }
        return "dashboard";
    }

    @PostMapping(value = "dashboard")
    public String getDashboard(Model model, @RequestParam("title") String title, @RequestParam("kategorie") String kategorie) {

        if (loginController.user != null) {

            if(!title.isEmpty()){
                List<PictureDaten> data = pictureRepository.findByTitle(title, loginController.user);
                model.addAttribute("dataList", data);
            }
            if(!kategorie.isEmpty()) {
                List<PictureDaten> data = pictureRepository.findByKategorie(kategorie, loginController.user);
                model.addAttribute("dataList", data);

            }
            if(!kategorie.isEmpty() && !title.isEmpty()) {
                List<PictureDaten> data = pictureRepository.findByTitleKategorie(title, kategorie, loginController.user);
                model.addAttribute("dataList", data);
            }

            return "dashboard";
        } else {
            return "NoData";
        }

    }
}
