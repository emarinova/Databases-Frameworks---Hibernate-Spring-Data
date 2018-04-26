package automappingobjects.gamestore.services.user;

import automappingobjects.gamestore.model.dto.binding.UserLogInBindingModel;
import automappingobjects.gamestore.model.dto.binding.UserRegisterBindingModel;
import automappingobjects.gamestore.model.dto.view.OwnedGamesByUser;
import automappingobjects.gamestore.model.dto.view.UserLogedInViewModel;

public interface UserService {

    boolean register(UserRegisterBindingModel model);

    UserLogedInViewModel successfullLogIn(UserLogInBindingModel model);

    OwnedGamesByUser ownedGamesByUser(int id);

    void updateListOfGames(OwnedGamesByUser user);
}
