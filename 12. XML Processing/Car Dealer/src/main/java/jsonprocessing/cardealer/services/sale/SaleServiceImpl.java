package jsonprocessing.cardealer.services.sale;

import jsonprocessing.cardealer.entity.binding.SaleCreateBindingModel;
import jsonprocessing.cardealer.entity.model.Car;
import jsonprocessing.cardealer.entity.model.Customer;
import jsonprocessing.cardealer.entity.model.Sale;
import jsonprocessing.cardealer.entity.view.Query6.CarViewModel;
import jsonprocessing.cardealer.entity.view.Query6.SaleDiscountViewModel;
import jsonprocessing.cardealer.repositories.CarRepository;
import jsonprocessing.cardealer.repositories.CustomerRepository;
import jsonprocessing.cardealer.repositories.SaleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {

    private final ModelMapper mapper = new ModelMapper();

    private final SaleRepository saleRepository;
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;

    public SaleServiceImpl(SaleRepository saleRepository, CustomerRepository customerRepository, CarRepository carRepository) {
        this.saleRepository = saleRepository;
        this.customerRepository = customerRepository;
        this.carRepository = carRepository;
    }

    @Override
    public void save(SaleCreateBindingModel model) {
        Sale sale = mapper.map(model,Sale.class);
        Car car = this.carRepository.findById((long)model.getCar());
        car.getSales().add(sale);
        sale.setCar(car);
        Customer customer = this.customerRepository.findById((long)model.getCustomer());
        customer.getSales().add(sale);
        sale.setCustomer(customer);
        this.saleRepository.saveAndFlush(sale);
    }

    @Override
    public void save(SaleCreateBindingModel[] models) {
        for(SaleCreateBindingModel model : models){
            this.save(model);
        }
    }

    @Override
    public List<SaleDiscountViewModel> salesWithDiscount() {
        List<SaleDiscountViewModel> sales = this.saleRepository.allSalesWithDiscount();
        for (SaleDiscountViewModel sale : sales){
            Car car = this.carRepository.findById(sale.getCarId());
            CarViewModel carModel = this.mapper.map(car, CarViewModel.class);
            sale.setCar(carModel);
        }
        return sales;
    }
}
