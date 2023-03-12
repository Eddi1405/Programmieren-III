package de.trio.imageshare.web.Controller;

import de.trio.imageshare.web.Repository.PictureRepository;
import de.trio.imageshare.web.Service.IndexService;
import de.trio.imageshare.web.entities.PictureDaten;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@Controller
public class DashboardController {
    private final PictureRepository pictureRepository;
    private final IndexService indexService;
    LoginController loginController;

    public DashboardController(PictureRepository pictureRepository, IndexService indexService,LoginController loginController) {
        this.pictureRepository = pictureRepository;
        this.indexService = indexService;
        this.loginController = loginController;
    }

    @GetMapping(value = "dashboard")
    public String getDashboardPage(Model model) {
        if(loginController.user != null){
            List<PictureDaten> data = indexService.getUserbybenutzer("test", pictureRepository);
            model.addAttribute("dataList", data);
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
