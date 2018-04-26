package json.processing.productsshop;

import com.google.gson.Gson;
import json.processing.productsshop.models.binding.category.CategoryCreateBindingModel;
import json.processing.productsshop.models.binding.product.ProductCreateBindingModel;
import json.processing.productsshop.models.binding.user.UserCreateBindingModel;
import json.processing.productsshop.models.view.category.CategoryProductsCountViewModel;
import json.processing.productsshop.models.view.product.ProductInPriceRangeViewModel;
import json.processing.productsshop.models.view.user.UserProductsViewModel;
import json.processing.productsshop.models.view.user.UserSoldItemsViewModel;
import json.processing.productsshop.services.category.CategoryService;
import json.processing.productsshop.services.product.ProductService;
import json.processing.productsshop.services.user.UserService;
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
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
@Transactional
public class Runner implements CommandLineRunner {

    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;

    private final Gson gson;

    private static final String USER_JSON_FILE_LOCATION = "/inputJson/users.json";
    private static final String PRODUCT_JSON_FILE_LOCATION = "/inputJson/products.json";
    private static final String CATEGORY_JSON_FILE_LOCATION = "/inputJson/categories.json";

    @Autowired
    public Runner(UserService userService, ProductService productService, CategoryService categoryService, Gson gson, ModelMapper modelMapper) {
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.gson = gson;
    }

    @Override
    public void run(String... args) throws Exception {

        seedUsers();
        seedCategories();
        seedProducts();

        //1
         List<ProductInPriceRangeViewModel> products = this.productService.getProductsInRangeWithNoBuyer(new BigDecimal(500), new BigDecimal(1000));
        String productsJson = gson.toJson(products);
        this.writeToFile("/outputJson/products-in-range.json", productsJson);

        //2
         List<UserSoldItemsViewModel> users = this.userService.usersWithSoldItems();
        String usersJson = gson.toJson(users);
        this.writeToFile("/outputJson/successfully-sold-products.json", usersJson);

        //3
        List<CategoryProductsCountViewModel> categories = this.categoryService.categoriesInfo();
        String categoriesJson = gson.toJson(categories);
        this.writeToFile("/outputJson/categories-by-product-count.json", categoriesJson);

        //4
        List<UserProductsViewModel> usersProducts = this.userService.usersAndProducts();
        String usersProductsJson = gson.toJson(usersProducts);
        this.writeToFile("/outputJson/users-products.json", usersProductsJson);
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

    private void seedUsers() throws IOException {
        InputStream userStream = this.loadData(USER_JSON_FILE_LOCATION);
        String loaded = readAllData(userStream);
        UserCreateBindingModel[] users = this.gson.fromJson(loaded, UserCreateBindingModel[].class);
        this.userService.save(users);
    }

    private void seedCategories() throws IOException {
        InputStream categoryStream = this.loadData(CATEGORY_JSON_FILE_LOCATION);
        String loaded = readAllData(categoryStream);
        String d = "";
        CategoryCreateBindingModel[] categories = this.gson.fromJson(loaded, CategoryCreateBindingModel[].class);
        this.categoryService.save(categories);
    }

    private void seedProducts() throws IOException {
        InputStream productStream = this.loadData(PRODUCT_JSON_FILE_LOCATION);
        String loaded = readAllData(productStream);
        ProductCreateBindingModel[] products = this.gson.fromJson(loaded, ProductCreateBindingModel[].class);
        Arrays.stream(products).forEach(this::randomizeData);
        this.productService.save(products);
    }

    private InputStream loadData(String fileLocation){
        return Runner.class.getResourceAsStream(fileLocation);
    }

    private String readAllData(InputStream stream) throws IOException {
        return StreamUtils.copyToString(stream, Charset.defaultCharset());
    }

    private void randomizeData(ProductCreateBindingModel model){
        Random random = new Random();
        int buyer = random.nextInt(60);
        if (buyer <= 56) model.setBuyer_id(new Long(buyer));

        int seller = random.nextInt(56)+1;
        model.setSeller_id(new Long(seller));
    }
}
