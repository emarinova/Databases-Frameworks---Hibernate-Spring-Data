package automappingobjects.gamestore.model.dto.view;

import java.math.BigDecimal;

public class GamePriceViewModel {
    private String title;
    private BigDecimal price;

    public GamePriceViewModel() {
    }

    public GamePriceViewModel(String title, BigDecimal price) {
        this.title = title;
        this.price = price;
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

    @Override
    public String toString() {
        return title + " " + price;
    }
}
