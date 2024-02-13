package dev.higormorais.utils;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.persistence.EntityNotFoundException;

public class RemovalById {

    public static void deleteAbstract(Integer id, PanacheRepositoryBase repository, String messageError) {
        boolean deleted = repository.deleteById(id);

        if(!deleted) {
            throw new EntityNotFoundException(messageError);
        }
    }

}
