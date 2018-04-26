package automappingobjects.gamestore.services.game;

import automappingobjects.gamestore.model.dto.binding.GameAddBindingModel;
import automappingobjects.gamestore.model.dto.binding.GameEditBindingModel;
import automappingobjects.gamestore.model.dto.view.GameDetailsViewModel;
import automappingobjects.gamestore.model.dto.view.GamePriceViewModel;
import automappingobjects.gamestore.model.dto.view.GameViewModel;
import automappingobjects.gamestore.model.entity.Game;
import automappingobjects.gamestore.repositories.GameRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public boolean addGame(GameAddBindingModel model) {
        boolean isAdded = false;
        if (model != null) {
            Game game = this.modelMapper.map(model, Game.class);
            this.gameRepository.saveAndFlush(game);
            if (game.getId() != 0) {
                isAdded = true;
            }
        }
        return isAdded;
    }

    @Override
    @Modifying
    public GameEditBindingModel editGame(String[] input) throws ParseException {
        Game game = this.gameRepository.findById(Integer.parseInt(input[1]));
        GameEditBindingModel model = this.modelMapper.map(game, GameEditBindingModel.class);
        if(model==null){
            return null;
        } else {
            for(int i = 2; i < input.length; i++){
                String[] values = input[i].split("=");
                model = this.changeProperty(values[0], values[1], model);
            }
            game.setTitle(model.getTitle());
            game.setDescription(model.getDescription());
            game.setImageThumbnail(model.getImageThumbnail());
            game.setPrice(model.getPrice());
            game.setReleaseDate(model.getReleaseDate());
            game.setSize(model.getSize());
            game.setTrailer(model.getTrailer());
            this.gameRepository.saveAndFlush(game);

        }
        return model;
    }

    @Override
    @Modifying
    public void deleteGame(int id) {
        Game game = this.gameRepository.findById(id);
        this.gameRepository.delete(game);
    }

    @Override
    public GameAddBindingModel findById(int id) {
        Game game = this.gameRepository.findById(id);
        if(game==null){
            return null;
        } else {
            return this.modelMapper.map(game, GameAddBindingModel.class);
        }
    }

    @Override
    public GameEditBindingModel changeProperty(String property, String value, GameEditBindingModel model) throws ParseException {
        switch (property){
            case "title":
                model.setTitle(value);
                break;
            case "price":
                model.setPrice(new BigDecimal(value));
                break;
            case "size":
                model.setSize(Double.parseDouble(value));
                break;
            case "trailer":
                model.setTrailer(value);
                break;
            case "imageThumbnail":
                model.setImageThumbnail(value);
                break;
            case "description":
                model.setDescription(value);
                break;
            case "releaseDate":
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                model.setReleaseDate(formatter.parse(value));
                break;
        }
        return model;
    }

    @Override
    public GameViewModel gameToDelete(int id){
        return this.modelMapper.map(this.gameRepository.findById(id), GameViewModel.class);
    }

    @Override
    public List<GamePriceViewModel> allGames() {
        List<Game> games = this.gameRepository.findAll();
        List<GamePriceViewModel> gamesViewModels = new ArrayList<>();
        for(Game game:games){
            gamesViewModels.add(modelMapper.map(game, GamePriceViewModel.class));
        }
        return gamesViewModels;
    }

    @Override
    public GameDetailsViewModel findByTitle(String title) {
        Game game = this.gameRepository.findByTitle(title);
        if (game== null) return null;
        else {
            return modelMapper.map(game, GameDetailsViewModel.class);
        }
    }

    @Override
    public GameViewModel gameByTitle(String title) {
        Game game = this.gameRepository.findByTitle(title);
        if (game == null) return null;
        else {
            return modelMapper.map(game, GameViewModel.class);
        }
    }
}
