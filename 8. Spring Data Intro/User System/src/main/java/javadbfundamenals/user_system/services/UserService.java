package javadbfundamenals.user_system.services;

import java.util.Date;
import java.util.List;

public interface UserService {
    List<String> usersWithEmailProvider(String provider);
    int updatedUsers(Date date);
}
