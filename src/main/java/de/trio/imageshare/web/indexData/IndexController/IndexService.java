package de.trio.imageshare.web.indexData.IndexController;

import de.trio.imageshare.web.indexData.IndexModel.Data;
import de.trio.imageshare.web.indexData.IndexModel.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexService {
    @Autowired
    private DataRepository dataInterface;

    public List<Data> findAll(){
        return dataInterface.findAll();
    }
}
