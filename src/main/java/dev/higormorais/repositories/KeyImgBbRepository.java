package dev.higormorais.repositories;

import dev.higormorais.entities.KeyImgBb;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class KeyImgBbRepository implements PanacheRepositoryBase<KeyImgBb, String> {

    public String returnKey() {
        List<KeyImgBb> keys = findAll().list();

        int size = keys.size();

        return keys.get(size - 1).getValue();

    }

}
