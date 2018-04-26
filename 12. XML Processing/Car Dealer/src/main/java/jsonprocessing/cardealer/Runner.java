package jsonprocessing.cardealer;

import jsonprocessing.cardealer.entity.binding.*;
import jsonprocessing.cardealer.entity.view.Query1.CustomerOrderedViewModel;
import jsonprocessing.cardealer.entity.view.Query1.CustomersView;
import jsonprocessing.cardealer.entity.view.Query2.CarViewModel;
import jsonprocessing.cardealer.entity.view.Query2.CarsView;
import jsonprocessing.cardealer.entity.view.Query3.SupplierLocalViewModel;
import jsonprocessing.cardealer.entity.view.Query3.SuppliersView;
import jsonprocessing.cardealer.entity.view.Query4.CarWithPartsViewModel;
import jsonprocessing.cardealer.entity.view.Query4.CarsWithPartsView;
import jsonprocessing.cardealer.entity.view.Query5.CustomerSalesViewModel;
import jsonprocessing.cardealer.entity.view.Query5.CustomersSalesView;
import jsonprocessing.cardealer.entity.view.Query6.SaleDiscountViewModel;
import jsonprocessing.cardealer.entity.view.Query6.SalesViewModel;
import jsonprocessing.cardealer.io.interfaces.FileIO;
import jsonprocessing.cardealer.parser.interfaces.Parser;
import jsonprocessing.cardealer.repositories.CustomerRepository;
import jsonprocessing.cardealer.services.car.CarService;
import jsonprocessing.cardealer.services.customer.CustomerService;
import jsonprocessing.cardealer.services.part.PartService;
import jsonprocessing.cardealer.services.sale.SaleService;
import jsonprocessing.cardealer.services.supplier.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

@Component
@Transactional
public class Runner implements CommandLineRunner {

    private static final String SUPPLIER_XML_FILE_LOCATION = "/inputXML/suppliers.xml";
    private static final String PART_XML_FILE_LOCATION = "/inputXML/parts.xml";
    private static final String CAR_XML_FILE_LOCATION = "/inputXML/cars.xml";
    private static final String CUSTOMER_XML_FILE_LOCATION = "/inputXML/customers.xml";

    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;

    @Autowired
    @Qualifier(value = "XMLParser")
    private Parser xmlParser;

    @Autowired
    private FileIO fileIOUtil;

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

        //seedSuppliers();
        //seedParts();
        //seedCars();
        //seedCustomers();
        //seedSales();

        //1
        //orderedCustomers();

        //2
        //carsFromMakeToyota();

        //3
        //localSuppliers();

        //4
        //carsWithTheirListOfParts();

        //5
        //totalSalesByCustomer();

        //6
        //salesWithAppliedDiscount();


    }

    private void salesWithAppliedDiscount() throws IOException, JAXBException {
        SalesViewModel sales = new SalesViewModel(this.saleService.salesWithDiscount());
        this.writeToFile("/outputXML/sales-with-discount.xml", this.xmlParser.write(sales));
    }

    private void totalSalesByCustomer() throws IOException, JAXBException {
        CustomersSalesView customers = new CustomersSalesView(this.customerService.customersTotalSales());
        this.writeToFile("/outputXML/total-sales-by-customer.xml", this.xmlParser.write(customers));
    }

    private void carsWithTheirListOfParts() throws IOException, JAXBException {
        CarsWithPartsView cars = new CarsWithPartsView(this.carService.carsWithParts());
        this.writeToFile("/outputXML/cars-with-their-list-of-parts.xml", this.xmlParser.write(cars));
    }

    private void localSuppliers() throws IOException, JAXBException {
        SuppliersView suppliers = new SuppliersView();
        suppliers.setSuppliers(this.supplierService.localSuppliers());
        this.writeToFile("/outputXML/local-suppliers.xml", this.xmlParser.write(suppliers));
    }

    private void carsFromMakeToyota() throws IOException, JAXBException {
        CarsView cars = new CarsView(this.carService.carsByMake("Toyota"));
        this.writeToFile("/outputXML/cars-from-toyota.xml", this.xmlParser.write(cars));
    }

    private void orderedCustomers() throws IOException, JAXBException {
        CustomersView customers = new CustomersView();
        customers.setCustomers(this.customerService.findAllOrdered());
        this.writeToFile("/outputXML/ordered-customers.xml", this.xmlParser.write(customers));
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
        CustomersCreate customers = new CustomersCreate();
        try {
            customers = xmlParser.read(CustomersCreate.class, this.fileIOUtil.read(CUSTOMER_XML_FILE_LOCATION));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        this.customerService.save(customers.getCustomers());
    }

    private void seedSuppliers() throws IOException {
        SuppliersCreate suppliers = new SuppliersCreate();
        try {
            suppliers = xmlParser.read(SuppliersCreate.class, this.fileIOUtil.read(SUPPLIER_XML_FILE_LOCATION));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        this.supplierService.save(suppliers.getSuppliers());
    }

    private void seedParts() throws IOException {
        PartsCreate parts = new PartsCreate();
        try {
            parts = xmlParser.read(PartsCreate.class, this.fileIOUtil.read(PART_XML_FILE_LOCATION));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        this.setRandomSupplier(parts.getParts());
        this.partService.save(parts.getParts());
    }

    private void seedCars() throws IOException {
        CarsCreate cars = new CarsCreate();
        try {
            cars = xmlParser.read(CarsCreate.class, this.fileIOUtil.read(CAR_XML_FILE_LOCATION));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        this.carService.save(cars.getCars());
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
