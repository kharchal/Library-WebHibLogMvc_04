package ua.com.hav.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.hav.domain.Kind;

/**
 * Created by Юля on 23.10.2016.
 */
@Transactional
@Repository
public class KindDao {

    @Autowired
    HibernateTemplate template;

    public void add(Kind kind) {
        System.out.println("dao " + kind);
        template.save(kind);
    }
}
