package jsonprocessing.cardealer.services.customer;

import jsonprocessing.cardealer.entity.binding.CustomerCreateBindingModel;
import jsonprocessing.cardealer.entity.model.Customer;
import jsonprocessing.cardealer.entity.view.Query1.CustomerOrderedViewModel;
import jsonprocessing.cardealer.entity.view.Query5.CustomerSalesViewModel;
import jsonprocessing.cardealer.entity.view.Query6.SaleDiscountViewModel;
import jsonprocessing.cardealer.parser.interfaces.ModelParser;
import jsonprocessing.cardealer.repositories.CustomerRepository;
import jsonprocessing.cardealer.repositories.SaleRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final SaleRepository saleRepository;
    private final ModelParser mapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, SaleRepository saleRepository, ModelParser mapper) {
        this.customerRepository = customerRepository;
        this.saleRepository = saleRepository;
        this.mapper = mapper;
    }

    @Override
    public void save(CustomerCreateBindingModel model) {
        Customer customer = mapper.convert(model, Customer.class);
        this.customerRepository.saveAndFlush(customer);
    }

    @Override
    public void save(CustomerCreateBindingModel[] models) {
        for(CustomerCreateBindingModel model : models){
            this.save(model);
        }
    }

    @Override
    public long count() {
        return this.customerRepository.count();
    }

    @Override
    public List<CustomerOrderedViewModel> findAllOrdered() {
        List<CustomerOrderedViewModel> customers = this.customerRepository.findAllOrdered();
        return  customers;
    }

    @Override
    public List<CustomerSalesViewModel> customersTotalSales() {
        List<CustomerSalesViewModel> customers = this.customerRepository.customersTotalSales();
        List<SaleDiscountViewModel> salePrices = saleRepository.allSalesWithDiscount();
        for(CustomerSalesViewModel customer : customers){
            for(SaleDiscountViewModel salePrice : salePrices){
                if(customer.getFullName().equals(salePrice.getCustomerName())){
                    customer.setSpentMoney(customer.getSpentMoney()+salePrice.getPriceWithDiscount());
                }
            }
        }
        Comparator<CustomerSalesViewModel> compByTotalMoneySPent = Comparator.comparing(customerSalesViewModel -> customerSalesViewModel.getSpentMoney());
        Comparator<CustomerSalesViewModel> compByTotalSales = Comparator.comparing(customerSalesViewModel -> customerSalesViewModel.getBoughtCars());
        customers = customers.stream().sorted(compByTotalMoneySPent.reversed().thenComparing(compByTotalSales)).collect(Collectors.toList());
        return customers;
    }
}
