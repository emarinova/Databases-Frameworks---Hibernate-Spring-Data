package automappingobjects.gamestore.model.dto.view;

public class GameViewModel {
    private int id;
    private String title;

    public GameViewModel() {
    }

    public GameViewModel(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
