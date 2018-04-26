package automappingobjects.gamestore.services.user;

import automappingobjects.gamestore.model.dto.binding.UserLogInBindingModel;
import automappingobjects.gamestore.model.dto.binding.UserRegisterBindingModel;
import automappingobjects.gamestore.model.dto.view.GameViewModel;
import automappingobjects.gamestore.model.dto.view.OwnedGamesByUser;
import automappingobjects.gamestore.model.dto.view.UserLogedInViewModel;
import automappingobjects.gamestore.model.entity.Game;
import automappingobjects.gamestore.model.entity.Role;
import automappingobjects.gamestore.model.entity.User;
import automappingobjects.gamestore.repositories.GameRepository;
import automappingobjects.gamestore.repositories.UserRepository;
import automappingobjects.gamestore.services.role.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, GameRepository gameRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
        this.roleService = roleService;
    }

    @Override
    public boolean register(UserRegisterBindingModel model) {
        User user = this.modelMapper.map(model, User.class);
        Set<Role> roles = this.setUserRole(user);
        if (model.isPasswordMatch()) {
            user = this.userRepository.saveAndFlush(user);
            for(Role role:roles) {
                role.getUsers().add(user);
                this.roleService.updateRole(role);
            }
        }
        return user.getId() != null;
    }

    @Override
    public UserLogedInViewModel successfullLogIn(UserLogInBindingModel model) {
        User user = this.userRepository.findByEmail(model.getEmail());
        if (user.getPassword().equals(model.getPassword())){
            return modelMapper.map(user, UserLogedInViewModel.class);
        } else return null;
    }

    @Override
    public OwnedGamesByUser ownedGamesByUser(int id) {
        User user = this.userRepository.findById(id);
        OwnedGamesByUser userModel = modelMapper.map(user, OwnedGamesByUser.class);
        return userModel;
    }

    @Modifying
    @Override
    public void updateListOfGames(OwnedGamesByUser user){
        User u = this.userRepository.findById(user.getId());
        Set<Game> games = new HashSet<>();
        for (GameViewModel g : user.getGames()){
            Game game = this.gameRepository.findById(g.getId());
            game.getUsers().add(u);
            this.gameRepository.saveAndFlush(game);
            games.add(game);
        }
        u.setGames(games);
        this.userRepository.saveAndFlush(u);
    }

    private Set<Role> setUserRole(User user) {
        Role role = this.roleService.getRoleByName(RoleService.Roles.ADMIN);
        Role role2 = this.roleService.getRoleByName(RoleService.Roles.USER);
        if (this.userRepository.count() > 0) {
            user.getRoles().add(role2);
        } else {
            user.getRoles().add(role);
            user.getRoles().add(role2);
        }
        return user.getRoles();
    }
}
