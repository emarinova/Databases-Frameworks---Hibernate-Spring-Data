package automappingobjects.gamestore.model.dto.view;

import automappingobjects.gamestore.model.entity.Game;

import java.util.HashSet;
import java.util.Set;

public class OwnedGamesByUser {
    private int id;
    private Set<GameViewModel> games;

    public OwnedGamesByUser() {
        this.games = new HashSet<>();
    }

    public OwnedGamesByUser(int id, Set<GameViewModel> games) {
        this.id = id;
        this.games = games;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<GameViewModel> getGames() {
        return games;
    }

    public void setGames(Set<GameViewModel> games) {
        this.games = games;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(GameViewModel game: this.games){
            sb.append(game.getTitle()).append(String.format("%n"));
        }
        return sb.toString();
    }
}

