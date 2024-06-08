package dev.higormorais.services.impl;

import dev.higormorais.configs.jwt.TokenGenerator;
import dev.higormorais.dto.mappers.UserMapper;
import dev.higormorais.dto.requests.CredentialsRequest;
import dev.higormorais.dto.requests.UserRequest;
import dev.higormorais.dto.responses.TokenResponse;
import dev.higormorais.entities.User;
import dev.higormorais.exceptions.InvalidPasswordException;
import dev.higormorais.repositories.UserRepository;
import dev.higormorais.services.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import org.mindrot.jbcrypt.BCrypt;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserMapper userMapper;

    @Inject
    private TokenGenerator tokenGenerator;

    // ----------------------------------------------------------------------------------------

    @Override
    public TokenResponse login(CredentialsRequest credentialsRequest) {
        User user = userRepository
                .findByEmail(credentialsRequest.getEmail())
                .orElseThrow(this::throwsNotFoundException);

        if(!checkPassword(credentialsRequest.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException("Senha inválida.");
        }

        String tokenJWT = tokenGenerator.createToken(user);

        var tokenResponse = new TokenResponse(tokenJWT);

        return tokenResponse;
    }

    @Override
    public void create(UserRequest userRequest) {

        User user = userMapper.toEntitie(userRequest);
        user.setPassword(generateEncryptedPassword(userRequest.getPassword()));

        userRepository.persist(user);
    }

    @Override
    public void update(Integer id, UserRequest userRequest) {

        User user = userMapper.toEntitie(userRequest);
        user.setId(id);
        user.setPassword(generateEncryptedPassword(userRequest.getPassword()));

        userRepository.update(user);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    private EntityNotFoundException throwsNotFoundException() {
        return new EntityNotFoundException("Email incorreto.");
    }

    private String generateEncryptedPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private boolean checkPassword(String password, String encriptedPassword) {
        return BCrypt.checkpw(password, encriptedPassword);
    }

}
