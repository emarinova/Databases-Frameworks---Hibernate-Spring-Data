package javadbfundamenals.user_system.runners;

import javadbfundamenals.user_system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class runner implements CommandLineRunner{

    private final UserService userService;

    @Autowired
    public runner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        //printAllUsersWithEmailProvider();
        removeInactiveUsers();
    }

    private void printAllUsersWithEmailProvider() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String provider = reader.readLine();
        for(String user: this.userService.usersWithEmailProvider(provider)){
            System.out.println(user);
        }
    }

    private void removeInactiveUsers() throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
        Date date = formatter.parse(reader.readLine());
        System.out.printf("%d users have been deleted%n", this.userService.updatedUsers(date));
    }
}
