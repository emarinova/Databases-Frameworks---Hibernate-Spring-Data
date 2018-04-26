package jsonprocessing.cardealer.services.sale;

import jsonprocessing.cardealer.entity.binding.SaleCreateBindingModel;
import jsonprocessing.cardealer.entity.view.Query6.SaleDiscountViewModel;

import java.util.List;

public interface SaleService {
    void save(SaleCreateBindingModel model);
    void save(SaleCreateBindingModel[] models);
    List<SaleDiscountViewModel> salesWithDiscount();
}
