package dev.higormorais.utils;

import dev.higormorais.client.ApiImgBB;
import dev.higormorais.client.model.Root;
import dev.higormorais.exceptions.InvalidKeyApiException;

import java.io.File;

public class ExternalRequest {

    public static String imageUpload(ApiImgBB imageAPI, File image, String key) {

        if(image == null) {
            return null;
        }

        if(key == null || key.isBlank()) {
            throw new InvalidKeyApiException("Chave de autenticação da API externa inválida.");
        }

        Root responseAPI = imageAPI.uploadImage(key, image);

        return responseAPI.urlImage();

    }

}
