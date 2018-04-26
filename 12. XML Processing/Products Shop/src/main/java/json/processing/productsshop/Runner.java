package json.processing.productsshop;

import json.processing.productsshop.io.interfaces.FileIO;
import json.processing.productsshop.models.binding.category.CategoriesCreate;
import json.processing.productsshop.models.binding.category.CategoryCreateBindingModel;
import json.processing.productsshop.models.binding.product.ProductCreateBindingModel;
import json.processing.productsshop.models.binding.product.ProductsCreate;
import json.processing.productsshop.models.binding.user.UserCreateBindingModel;
import json.processing.productsshop.models.binding.user.UsersCreate;
import json.processing.productsshop.models.view.category.Query3.CategoriesViewModel;
import json.processing.productsshop.models.view.product.Query1.ProductInRange;
import json.processing.productsshop.models.view.user.Query2.UsersSoldItems;
import json.processing.productsshop.models.view.user.Query4.UsersProducts;
import json.processing.productsshop.parser.interfaces.Parser;
import json.processing.productsshop.services.category.CategoryService;
import json.processing.productsshop.services.product.ProductService;
import json.processing.productsshop.services.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Random;

@Component
@Transactional
public class Runner implements CommandLineRunner {

    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    @Qualifier(value = "XMLParser")
    private Parser xmlParser;

    @Autowired
    private FileIO fileIOUtil;

    //@Autowired
    //private ConsoleIO consoleIO;

    private static final String USER_XML_FILE_LOCATION = "/inputXML/users.xml";
    private static final String CATEGORIES_XML_FILE_LOCATION = "/inputXML/categories.xml";
    private static final String PRODUCTS_XML_FILE_LOCATION = "/inputXML/products.xml";

    @Autowired
    public Runner(UserService userService, ProductService productService, CategoryService categoryService, ModelMapper modelMapper) {
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {

        //seedUsers();
        //seedCategories();
        //seedProducts();

        //1
        //productsInRange(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));

        //2
        //usersSoldItems();

        //3
        //categoriesByProductsCount();

        //4
        //usersAndProducts();

}

    private void usersAndProducts() throws IOException, JAXBException {
        UsersProducts usersProducts = this.userService.usersAndProducts();
        this.writeToFile("/outputXML/users-products.xml", this.xmlParser.write(usersProducts));
    }

    private void categoriesByProductsCount() throws IOException, JAXBException {
        CategoriesViewModel categories = this.categoryService.categoriesInfo();
        this.writeToFile("/outputXML/categories-by-product-count.xml", this.xmlParser.write(categories));
    }

    private void usersSoldItems() throws IOException, JAXBException {
        UsersSoldItems users = this.userService.usersWithSoldItems();
        this.writeToFile("/outputXML/successfully-sold-products.xml", this.xmlParser.write(users));
    }

    private void productsInRange(BigDecimal from, BigDecimal to) throws IOException, JAXBException {
        ProductInRange products = this.productService.getProductsInRangeWithNoBuyer(from, to);
        this.writeToFile("/outputXML/products-in-range.xml", this.xmlParser.write(products));
    }

    private void seedUsers() throws IOException, JAXBException {
        UsersCreate users = new UsersCreate();
        try {
            users = this.xmlParser.read(UsersCreate.class, this.fileIOUtil.read(USER_XML_FILE_LOCATION));
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (UserCreateBindingModel userBindingModel : users.getUsers()) {
            this.userService.save(userBindingModel);
        }
    }

    private void seedCategories() throws IOException {
        CategoriesCreate categories = new CategoriesCreate();
        try {
            categories = this.xmlParser.read(CategoriesCreate.class, this.fileIOUtil.read(CATEGORIES_XML_FILE_LOCATION));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        for (CategoryCreateBindingModel cateogoryModel : categories.getCategories()){
            this.categoryService.save(cateogoryModel);
        }
    }

    private void seedProducts() throws IOException {
        ProductsCreate products = new ProductsCreate();
        try {
            products = this.xmlParser.read(ProductsCreate.class, this.fileIOUtil.read(PRODUCTS_XML_FILE_LOCATION));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        for (ProductCreateBindingModel product : products.getProducts()){
            this.randomizeData(product);
            this.productService.save(product);
        }
    }

    private void randomizeData(ProductCreateBindingModel model){
        Random random = new Random();
        int buyer = random.nextInt(60);
        if (buyer <= 56) model.setBuyer_id(new Long(buyer));
        int seller = random.nextInt(56)+1;
        model.setSeller_id(new Long(seller));
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
}
