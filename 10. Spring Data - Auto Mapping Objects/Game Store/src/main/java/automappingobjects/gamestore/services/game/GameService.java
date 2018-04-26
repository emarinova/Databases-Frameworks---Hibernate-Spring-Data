package automappingobjects.gamestore.services.game;

import automappingobjects.gamestore.model.dto.binding.GameAddBindingModel;
import automappingobjects.gamestore.model.dto.binding.GameEditBindingModel;
import automappingobjects.gamestore.model.dto.view.GameDetailsViewModel;
import automappingobjects.gamestore.model.dto.view.GamePriceViewModel;
import automappingobjects.gamestore.model.dto.view.GameViewModel;

import java.text.ParseException;
import java.util.List;

public interface GameService {
    boolean addGame(GameAddBindingModel model);
    GameAddBindingModel findById(int id);
    GameEditBindingModel changeProperty(String property, String value, GameEditBindingModel model) throws ParseException;
    GameEditBindingModel editGame(String[] input) throws ParseException;

    void deleteGame(int id);

    GameViewModel gameToDelete(int id);

    List<GamePriceViewModel> allGames();

    GameDetailsViewModel findByTitle(String title);

    GameViewModel gameByTitle(String title);
}
