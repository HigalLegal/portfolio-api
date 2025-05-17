package dev.higormorais.configs;

import dev.higormorais.dto.requests.UserRequest;
import dev.higormorais.entities.KeyImgBb;
import dev.higormorais.repositories.KeyImgBbRepository;
import dev.higormorais.services.UserService;
import io.quarkus.runtime.Startup;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Startup
@Singleton
public class AppInitializer {

    @Inject
    private UserService userService;

    @Inject
    private KeyImgBbRepository keyImgBbRepository;

    @PostConstruct
    public void init() {
        String datasLine = readUserTxt();
        String[] datas = datasLine.split(",");

        String email = datas[0];
        String password = datas[1];
        String key = datas[2];

        this.insertInitialUser(email, password);
        this.insertKeyApiImage(key);
    }

    @Transactional
    void insertInitialUser(String email, String password) {

        Long totalUsers = this.userService.total();

        if(totalUsers == 0) {
            UserRequest userRequest = UserRequest
                    .builder()
                    .email(email)
                    .password(password)
                    .admin(true)
                    .build();
            this.userService.create(userRequest);
        }
    }

    @Transactional
    void insertKeyApiImage(String key) {
        long totalKeys = this.keyImgBbRepository.count();

        if(totalKeys ==  0) {
            this.keyImgBbRepository.persist(new KeyImgBb(key));
        }
    }

    private String readUserTxt() {
        try (
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream("user.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
        ) {
            return reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


}
