package de.trio.imageshare.web.Service;

import de.trio.imageshare.web.Repository.PictureRepository;
import de.trio.imageshare.web.entities.PictureDaten;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class DashboardService {
    @Autowired
    public PictureRepository pictureRepository;


}
