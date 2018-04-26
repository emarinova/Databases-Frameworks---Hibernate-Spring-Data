package automappingobjects.gamestore.model.dto.view;

import com.sun.javafx.binding.StringFormatter;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GameDetailsViewModel {
    private String title;
    private BigDecimal price;
    private String description;
    private Date releaseDate;

    public GameDetailsViewModel() {
    }

    public GameDetailsViewModel(String title, BigDecimal price, String description, Date releaseDate) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return String.format("Title: %s%nPrice: %.2f%nDescription: %s%nRelease date: ", this.getTitle(), this.getPrice(), this.getDescription()) + formatter.format(this.getReleaseDate());
    }
}
