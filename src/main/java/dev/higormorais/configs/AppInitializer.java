package dev.higormorais.configs;

import dev.higormorais.dto.requests.UserRequest;
import dev.higormorais.entities.KeyImgBb;
import dev.higormorais.repositories.KeyImgBbRepository;
import dev.higormorais.services.UserService;
import io.quarkus.runtime.Startup;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

@Startup
@Singleton
public class AppInitializer {

    @Inject
    private UserService userService;

    @Inject
    private KeyImgBbRepository keyImgBbRepository;

    public void onStart(@Observes StartupEvent event) {
        String email = System.getenv("INITIAL_USER_EMAIL");
        String password = System.getenv("INITIAL_USER_PASSWORD");
        String apiKey = System.getenv("INITIAL_API_KEY");

        insertInitialUser(email, password);
        insertKeyApiImage(apiKey);
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

}
