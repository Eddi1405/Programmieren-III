package de.trio.imageshare.web.Controller;

import de.trio.imageshare.web.Repository.PictureRepository;
import de.trio.imageshare.web.entities.PictureDaten;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Dieser Controller ist dafür da, um ein Dashboard für die des Nutzers hochgeladenen Bilder zu erstellen.
 */
@Slf4j
@Controller
public class DashboardController {
    private final PictureRepository pictureRepository;
    LoginController loginController;

    /**
     * Die unten genannten Parameter werden im Konstruktor übergeben.
     *
     * @param pictureRepository
     * @param loginController
     */
    public DashboardController(PictureRepository pictureRepository, LoginController loginController) {
        this.pictureRepository = pictureRepository;
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
    public String showDashboard(Model model, HttpSession session) {
        loginController.navbar(model, session);
        if (session.getAttribute("user")!= null) {
            List<PictureDaten> data = pictureRepository.findBybenutzer(loginController.user);
            model.addAttribute("dataList", data);
            return "dashboard";
        }
        return "NoData";
    }

    /**
     * Ist für den Filter von der Dashboard Seite. Je nachdem welche Parameter gegeben werden wird eine andere Abfrage ausgeführt.
     * @param session
     * @param model
     * @param title
     * @param kategorie
     * @param datum1
     * @param datum2
     * @return
     */
    @PostMapping(value = "dashboard")
    public String showDashboardFilter(HttpSession session, Model model, @RequestParam("title") String title, @RequestParam("kategorie") String kategorie, @RequestParam("datum1") String datum1, @RequestParam("datum2") String datum2) {
        if (session.getAttribute("user") != null) {

            if (!kategorie.isEmpty() && !title.isEmpty() && !datum1.isEmpty() && !datum2.isEmpty()) {
                List<PictureDaten> data = pictureRepository.findByKategorieTitleDatum(kategorie, title, datum1, datum2, loginController.user);
                model.addAttribute("dataList", data);

            } else if (!kategorie.isEmpty() && !datum1.isEmpty() && !datum2.isEmpty()) {
                List<PictureDaten> data = pictureRepository.findByKategorieDatum(kategorie, datum1, datum2, loginController.user);
                model.addAttribute("dataList", data);

            } else if (!title.isEmpty() && !datum1.isEmpty() && !datum2.isEmpty()) {
                List<PictureDaten> data = pictureRepository.findByTitleDatum(title, datum1, datum2, loginController.user);
                model.addAttribute("dataList", data);

            } else if (!kategorie.isEmpty() && !title.isEmpty()) {
                List<PictureDaten> data = pictureRepository.findByKategorieTitle(title, kategorie, loginController.user);
                model.addAttribute("dataList", data);
            } else if (!title.isEmpty()) {
                List<PictureDaten> data = pictureRepository.findByTitle(title, loginController.user);
                model.addAttribute("dataList", data);

            } else if (!kategorie.isEmpty()) {
                List<PictureDaten> data = pictureRepository.findByKategorie(kategorie, loginController.user);
                model.addAttribute("dataList", data);

            } else if (!datum1.isEmpty() && !datum2.isEmpty()) {
                List<PictureDaten> data = pictureRepository.findByDatum(datum1, datum2, loginController.user);
                model.addAttribute("dataList", data);
            }

            return "dashboard";
        } else {
            return "NoData";
        }

    }
}
