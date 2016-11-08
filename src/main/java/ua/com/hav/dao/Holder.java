package ua.com.hav.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.hav.domain.Kind;
import ua.com.hav.domain.Pet;

import ua.com.hav.service.KindService;


import javax.persistence.Id;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by Юля on 13.10.2016.
 */
public class Holder<T> {
    private Logger logger = Logger.getLogger(Holder.class);

//    @Autowired
//    KindService kindService;

    private Map<Long, T> map;
    private Class clazz;
    private long index = 1L;
    private Method setId;
    private Method getId;
    private Field[] fields;

    public Holder(Class clazz) {
        this.clazz = clazz;
        map = new HashMap<>();
        try {
            getId = clazz.getDeclaredMethod("getId");
            Class[] args = new Class[1];
            args[0] = Long.class;
            setId = clazz.getDeclaredMethod("setId", args);
            fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void add(T el) {
        logger.info("pet to add: " + el);
        try {
            setId.invoke(el, index);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        map.put(index, el);
        logger.info("Pet has been added: " + el);
//        kindService.add((Kind) el);
//        logger.info("Pet has been added to the DB!");
        index++;
    }

    public int size() {
        return map.size();
    }

    public List<T> getList() {
        return new ArrayList<>(map.values());
    }

    public Map<Long, String> asMap() {
        Map<Long, String> res = new HashMap<>();
        for (Long key : map.keySet()) {
            res.put(key, ((Kind)map.get(key)).getName());
        }
        return res;
    }

    public T find(Long id) {
        return (T) map.get(id);
    }

    public void remove(Long id) {
        map.remove(id);
    }

    public void update(T el) {
        Long id = null;
        try {
            id = (Long) getId.invoke(el);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        T elToUpdate = find(id);
        if (elToUpdate != null) {
            for (Field field : fields) {
                if (!field.isAnnotationPresent(Id.class)) {
                    try {
                        field.set(elToUpdate, field.get(el));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
