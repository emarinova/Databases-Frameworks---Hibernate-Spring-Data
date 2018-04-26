package jsonprocessing.cardealer.services.customer;

import jsonprocessing.cardealer.entity.binding.CustomerCreateBindingModel;
import jsonprocessing.cardealer.entity.view.Query1.CustomerOrderedViewModel;
import jsonprocessing.cardealer.entity.view.Query5.CustomerSalesViewModel;

import java.util.List;

public interface CustomerService {
    void save(CustomerCreateBindingModel model);
    void save(CustomerCreateBindingModel[] models);
    long count();
    List<CustomerOrderedViewModel> findAllOrdered();
    List<CustomerSalesViewModel> customersTotalSales();
}
