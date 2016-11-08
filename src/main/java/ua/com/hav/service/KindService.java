package ua.com.hav.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.hav.dao.KindDao;
import ua.com.hav.domain.Kind;

/**
 * Created by Юля on 23.10.2016.
 */
@Service
public class KindService {

    @Autowired
    KindDao kindDao;

    public void add(Kind kind) {
        System.out.println("service " + kind);
        kindDao.add(kind);
    }

}
