package de.trio.imageshare.web.Controller;

import de.trio.imageshare.web.Repository.PictureRepository;
import de.trio.imageshare.web.Service.IndexService;
import de.trio.imageshare.web.entities.PictureDaten;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

/**
 * Dieser Controller ist dafür da, um ein Dashboard für die des Nutzers hochgeladenen Bilder zu erstellen.
 *
 */
@Controller
public class DashboardController {
    private final PictureRepository pictureRepository;
    private final IndexService indexService;
    LoginController loginController;

    /**
     * Die unten genannten Parameter werden im Konstruktor übergeben.
     * @param pictureRepository
     * @param indexService
     * @param loginController
     */
    public DashboardController(PictureRepository pictureRepository, IndexService indexService,LoginController loginController) {
        this.pictureRepository = pictureRepository;
        this.indexService = indexService;
        this.loginController = loginController;
    }

    /**
     * Http Get Anforderung für den Pfad dashboard.
     * Wenn loginController.user nicht null ist, ruft die Methode getUserbybenutzer des
     * IndexService auf und gibt eine Liste von PictureDaten zurück, die dann an das Model übergeben wird.
     * @param model
     * Bei Erfolg wird das Dashboard returned und bei misserfolg die NoData html.
     * @return
     */
    @GetMapping(value = "dashboard")
    public String getDashboardPage(Model model) {
        if(loginController.user != null){
            List<PictureDaten> data = indexService.getUserbybenutzer(loginController.user, pictureRepository);
            model.addAttribute("dataList", data);
            model.addAttribute("log", loginController.log);
            if(data == null){
                return "NoData";
            }else{
                return "dashboard";
            }
        }else{
            return "NoData";
        }


    }
}
