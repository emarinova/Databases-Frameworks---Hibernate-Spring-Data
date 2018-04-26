package javadbfundamenals.user_system.services;

import javadbfundamenals.user_system.entities.User;
import javadbfundamenals.user_system.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<String> usersWithEmailProvider(String provider) {
        StringBuilder sb = new StringBuilder();
        sb.append("@").append(provider);
        return this.userRepository.findAllByEmailEndingWith(sb.toString()).stream().map(u -> u.getUsername() + " " + u.getEmail()).collect(Collectors.toList());
    }

    @Override
    public int updatedUsers(Date date) {
        List<User> users = this.userRepository.findAllByLastTimeLoggedInBefore(date);
        for(User u : users){
            u.setDeleted(true);
        }
        return users.size();
    }
}
