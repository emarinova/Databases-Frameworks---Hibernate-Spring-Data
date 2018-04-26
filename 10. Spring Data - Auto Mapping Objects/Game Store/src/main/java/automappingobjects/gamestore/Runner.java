package automappingobjects.gamestore;

import automappingobjects.gamestore.model.dto.binding.GameAddBindingModel;
import automappingobjects.gamestore.model.dto.binding.GameEditBindingModel;
import automappingobjects.gamestore.model.dto.binding.UserLogInBindingModel;
import automappingobjects.gamestore.model.dto.binding.UserRegisterBindingModel;
import automappingobjects.gamestore.model.dto.view.*;
import automappingobjects.gamestore.services.game.GameService;
import automappingobjects.gamestore.services.role.RoleService;
import automappingobjects.gamestore.services.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Transactional
public class Runner implements CommandLineRunner {

    private final UserService userService;
    private final GameService gameService;
    private final ModelMapper modelMapper;
    private final RoleService roleService;

    @Autowired
    public Runner(UserService userService, GameService gameService, RoleService roleService) {
        this.userService = userService;
        this.gameService = gameService;
        this.roleService = roleService;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public void run(String... args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split("\\|");
        UserLogedInViewModel logedInUser = new UserLogedInViewModel();
        List<GameViewModel> shoppingCart = new ArrayList<>();

        while (!input[0].equals("Exit")) {
        switch (input[0]) {
            case "RegisterUser":
                UserRegisterBindingModel registerDto = new UserRegisterBindingModel(input[1], input[2], input[3], input[4]);
                boolean isRegistered = this.userService.register(registerDto);

                if (isRegistered) {
                    System.out.println(registerDto.getFullName() + " was registered");
                }
                break;
            case "LoginUser":
                UserLogInBindingModel loginDto = new UserLogInBindingModel(input[1], input[2]);
                UserLogedInViewModel model = this.userService.successfullLogIn(loginDto);
                if (model != null) {
                    logedInUser = model;
                    System.out.println("Successfully logged in " + model.getFullName());
                } else {
                    System.out.println("Incorrect username/password");
                }
                break;
            case "LogoutUser":
                if (logedInUser.getFullName() == null) {
                    System.out.println("Cannot log out. No user was logged in.");
                } else {
                    System.out.println("User " + logedInUser.getFullName() + " successfully logged out.");
                    logedInUser = new UserLogedInViewModel(0, null);
                }
                break;
            case "AddGame":
                if (logedInUser.getId() != 1) {
                    System.out.println("Admin is not logged in.");
                } else {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    Date date = formatter.parse(input[7]);
                    BigDecimal price = new BigDecimal(input[2]);
                    double size = Double.parseDouble(input[3]);
                    GameAddBindingModel addGameDto = new GameAddBindingModel(input[1], price, size, input[4], input[5], input[6], date);
                    boolean isAdded = this.gameService.addGame(addGameDto);
                    if (isAdded) {
                        System.out.println("Added " + addGameDto.getTitle());
                    } else {
                        System.out.println("Not Added");
                    }

                }
                break;
            case "EditGame":
                if (logedInUser.getId() != 1) {
                    System.out.println("Admin is not logged in.");
                } else {
                    System.out.println("Eddited " + this.gameService.editGame(input).getTitle());
                }
                break;
            case "DeleteGame":
                if (logedInUser.getId() != 1) {
                    System.out.println("Admin is not logged in.");
                } else {
                    GameViewModel gameToDelete = this.gameService.gameToDelete(Integer.parseInt(input[1]));

                    if (gameToDelete.getId() == 0) {
                        System.out.println("No game with that id");
                    } else {
                        this.gameService.deleteGame(gameToDelete.getId());
                        System.out.println("Deleted " + gameToDelete.getTitle());
                    }

                }
                break;
            case "AllGame":
                for(GamePriceViewModel game:this.gameService.allGames()){
                    System.out.println(game.toString());
                }
                break;
            case "DetailGame":
                String title = input[1];
                System.out.println(this.gameService.findByTitle(title).toString());
                break;
            case "OwnedGames":
                int id = logedInUser.getId();
                if (id == 0) {
                    System.out.println("No user was logged in.");
                } else {
                    OwnedGamesByUser user = this.userService.ownedGamesByUser(id);
                    System.out.println(user.toString());
                }
                break;

                //Shopping Cart
            case "AddItem":
                String gameTitle = input[1];
                if(logedInUser.getId()==0){
                    System.out.println("No user was logged in.");
                } else {
                    OwnedGamesByUser gamesByUser = this.userService.ownedGamesByUser(logedInUser.getId());
                    GameViewModel gameToBuy = this.gameService.gameByTitle(gameTitle);
                    if (gameToBuy==null) {
                        System.out.println("No such game.");
                    } else {
                        boolean isAlreadyBought = false;
                        for (GameViewModel g : gamesByUser.getGames()){
                            if (g.getId() == gameToBuy.getId()){
                                System.out.println(gameToBuy.getTitle() + " already bought.");
                                isAlreadyBought = true;
                            }
                        }
                        if (!isAlreadyBought){
                            shoppingCart.add(gameToBuy);
                            System.out.println(gameToBuy.getTitle() + " added to cart.");
                        }
                    }
                }
                break;
            case "RemoveItem":
                String gameTitle01 = input[1];
                if(logedInUser.getId()==0){
                    System.out.println("No user was logged in.");
                } else {
                    GameViewModel gameToBuy01 = this.gameService.gameByTitle(gameTitle01);
                    boolean isThereSuchGame = false;
                    for (GameViewModel g : shoppingCart) {
                        if (g.getId() == gameToBuy01.getId()) {
                            gameToBuy01=g;
                            isThereSuchGame = true;
                        }
                    }
                    if(isThereSuchGame){
                        shoppingCart.remove(gameToBuy01);
                        System.out.println(gameToBuy01.getTitle() + " removed from cart.");
                    }
                    if (!isThereSuchGame) {
                        System.out.println("No such game in cart.");
                    }
                }
                break;
            case "BuyItem":
                if(logedInUser.getId()==0){
                    System.out.println("No user was logged in.");
                } else {
                    if (shoppingCart.size()!=0) {
                        OwnedGamesByUser loggedInUserAndGames = this.userService.ownedGamesByUser(logedInUser.getId());
                        for (GameViewModel game : shoppingCart) {
                            loggedInUserAndGames.getGames().add(game);
                        }
                        this.userService.updateListOfGames(loggedInUserAndGames);
                        System.out.println("Successfully bought games: ");
                        for (GameViewModel game : shoppingCart) {
                            System.out.printf("- %s%n", game.getTitle());
                        }
                        shoppingCart = new ArrayList<>();
                    }
                }
                break;
        }
            input = reader.readLine().split("\\|");
        }

    }
}
