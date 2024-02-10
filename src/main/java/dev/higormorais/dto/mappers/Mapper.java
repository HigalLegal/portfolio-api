package dev.higormorais.dto.mappers;

public interface Mapper<E, I, O> {

    E toEntitie(I request);

    O toResponse(E entitie);

}
