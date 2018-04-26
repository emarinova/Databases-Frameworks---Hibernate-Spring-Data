package jsonprocessing.cardealer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jsonprocessing.cardealer.entity.binding.*;
import jsonprocessing.cardealer.entity.view.Query1.CustomerOrderedViewModel;
import jsonprocessing.cardealer.entity.view.Query2.CarViewModel;
import jsonprocessing.cardealer.entity.view.Query3.SupplierLocalViewModel;
import jsonprocessing.cardealer.entity.view.Query4.CarWithPartsViewModel;
import jsonprocessing.cardealer.entity.view.Query5.CustomerSalesViewModel;
import jsonprocessing.cardealer.entity.view.Query6.SaleDiscountViewModel;
import jsonprocessing.cardealer.repositories.CustomerRepository;
import jsonprocessing.cardealer.services.car.CarService;
import jsonprocessing.cardealer.services.customer.CustomerService;
import jsonprocessing.cardealer.services.part.PartService;
import jsonprocessing.cardealer.services.sale.SaleService;
import jsonprocessing.cardealer.services.supplier.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;

@Component
@Transactional
public class Runner implements CommandLineRunner {

    private final ModelMapper mapper = new ModelMapper();
    private final Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .excludeFieldsWithoutExposeAnnotation()
            .setPrettyPrinting()
            .create();

    private static final String SUPPLIER_JSON_FILE_LOCATION = "/inputJson/suppliers.json";
    private static final String PART_JSON_FILE_LOCATION = "/inputJson/parts.json";
    private static final String CAR_JSON_FILE_LOCATION = "/inputJson/cars.json";
    private static final String CUSTOMER_JSON_FILE_LOCATION = "/inputJson/customers.json";

    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;

    @Autowired
    public Runner(SupplierService supplierService, PartService partService, CarService carService, CustomerRepository customerRepository, CustomerService customerService, SaleService saleService) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
    }

    @Override
    public void run(String... args) throws Exception {

        seedSuppliers();
        seedParts();
        seedCars();
        seedCustomers();
        seedSales();

        //1
        orderedCustomers();

        //2
        carsFromMakeToyota();

        //3
        localSuppliers();

        //4
        carsWithTheirListOfParts();

        //5
        totalSalesByCustomer();

        //6
        salesWithAppliedDiscount();


    }

    private void salesWithAppliedDiscount() {
        List<SaleDiscountViewModel> sales = this.saleService.salesWithDiscount();
        String salesJson = this.gson.toJson(sales);
        this.writeToFile("/outputJson/sales-with-applied-discount.json", salesJson);
    }

    private void totalSalesByCustomer() {
      List<CustomerSalesViewModel> customers = this.customerService.customersTotalSales();
      String cutomersJson = this.gson.toJson(customers);
      this.writeToFile("/outputJson/total-sales-by-customer.json", cutomersJson);
    }

    private void carsWithTheirListOfParts() {
        List<CarWithPartsViewModel> cars = this.carService.carsWithParts();
        String carsJson = this.gson.toJson(cars);
        this.writeToFile("/outputJson/cars-with-their-parts.json", carsJson);
    }

    private void localSuppliers() {
        List<SupplierLocalViewModel> suppliers = this.supplierService.localSuppliers();
        String supplierJson = this.gson.toJson(suppliers);
        this.writeToFile("/outputJson/local-suppliers.json", supplierJson);
    }

    private void carsFromMakeToyota() {
        List<CarViewModel> cars = this.carService.carsByMake("Toyota");
        String carsJson = this.gson.toJson(cars);
        this.writeToFile("/outputJson/cars-by-toyota.json", carsJson);
    }

    private void orderedCustomers() {
        List<CustomerOrderedViewModel> customers = this.customerService.findAllOrdered();
        String customersJson = this.gson.toJson(customers);
        this.writeToFile("/outputJson/customers-ordered.json", customersJson);
    }

    private void writeToFile(String fileName, String src) {
        try {
            String mainPath = System.getProperty("user.dir") + "/src/main/resources";
            FileWriter writer = new FileWriter(new File(mainPath + fileName));
            writer.write(src);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void seedSales() {
        Random random = new Random();
        int[] discounts = {0, 5, 10, 15, 20, 30, 40, 50};
        SaleCreateBindingModel[] sales = new SaleCreateBindingModel[100];
        for(int i = 0; i < 100; i++){
            Long car = (long)(random.nextInt((int)this.carService.count())+1);
            Long customer = (long)(random.nextInt((int)this.customerService.count())+1);
            int discount = discounts[random.nextInt(discounts.length)];
            sales[i] = new SaleCreateBindingModel(car, customer, discount);
        }
        this.saleService.save(sales);
    }

    private void seedCustomers() throws IOException {
        InputStream customersStream = this.loadData(CUSTOMER_JSON_FILE_LOCATION);
        String loaded = this.readAllData(customersStream);
        CustomerCreateBindingModel[] customers = this.gson.fromJson(loaded, CustomerCreateBindingModel[].class);
        this.customerService.save(customers);
    }

    private void seedSuppliers() throws IOException {
        InputStream suppliersStream = this.loadData(SUPPLIER_JSON_FILE_LOCATION);
        String loaded = this.readAllData(suppliersStream);
        SupplierCreateBindingModel[] suppliers = this.gson.fromJson(loaded, SupplierCreateBindingModel[].class);
        this.supplierService.save(suppliers);
    }

    private void seedParts() throws IOException {
        InputStream partsStream = this.loadData(PART_JSON_FILE_LOCATION);
        String loaded = this.readAllData(partsStream);
        PartCreateBindingModel[] parts = this.gson.fromJson(loaded, PartCreateBindingModel[].class);
        setRandomSupplier(parts);
        this.partService.save(parts);
    }

    private void seedCars() throws IOException {
        InputStream carsStream = this.loadData(CAR_JSON_FILE_LOCATION);
        String loaded = this.readAllData(carsStream);
        CarCreateBindingModel[] cars = this.gson.fromJson(loaded, CarCreateBindingModel[].class);
        this.carService.save(cars);
    }

    private InputStream loadData(String fileLocation){
        return Runner.class.getResourceAsStream(fileLocation);
    }

    private String readAllData(InputStream stream) throws IOException {
        return StreamUtils.copyToString(stream, Charset.defaultCharset());
    }

    private void setRandomSupplier(PartCreateBindingModel[] models){
        Random random = new Random();
        long count = this.supplierService.count();
        for(PartCreateBindingModel model : models){
            int supplier_id = random.nextInt((int)count)+1;
            model.setSupplier_id(supplier_id);
        }
    }

}
