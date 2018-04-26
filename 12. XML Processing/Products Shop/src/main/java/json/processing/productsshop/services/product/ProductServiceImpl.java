package json.processing.productsshop.services.product;

import json.processing.productsshop.models.binding.product.ProductCreateBindingModel;
import json.processing.productsshop.models.entity.Category;
import json.processing.productsshop.models.entity.Product;
import json.processing.productsshop.models.entity.User;
import json.processing.productsshop.models.view.product.Query1.ProductInPriceRangeViewModel;
import json.processing.productsshop.models.view.product.Query1.ProductInRange;
import json.processing.productsshop.repositories.CategoryRepository;
import json.processing.productsshop.repositories.ProductRepository;
import json.processing.productsshop.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository, CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(ProductCreateBindingModel model) {
        User buyer = this.userRepository.findById(model.getBuyer_id());
        User seller = this.userRepository.findById(model.getSeller_id());
        Product product = modelMapper.map(model, Product.class);
        product.setBuyer(buyer);
        product.setSeller(seller);
        product.setCategories(this.setCategories(product));
        this.productRepository.saveAndFlush(product);
    }

    @Override
    public void save(ProductCreateBindingModel[] models) {
        for(ProductCreateBindingModel model: models){
            this.save(model);
        }
    }

    private Set<Category> setCategories(Product product) {
       int categoriesCount = (int)this.categoryRepository.count();
        Set<Category> categories = new HashSet<>();
        Random random = new Random();
        for (int i =0; i < random.nextInt(2)+1; i++) {
            int categoryIdInt = random.nextInt(categoriesCount) + 1;
            long categoryId = new Long(categoryIdInt);
            Category category = this.categoryRepository.findById(categoryId);
            category.getProducts().add(product);
            categories.add(category);
        }
        return categories;
    }

    public ProductInPriceRangeViewModel getProductView(Product product){
        ProductInPriceRangeViewModel productView = modelMapper.map(product, ProductInPriceRangeViewModel.class);
        User seller = product.getSeller();
        productView.setSeller(seller.getFirstName() + " " + seller.getLastName());
        return productView;
    }

    @Override
    public ProductInRange getProductsInRangeWithNoBuyer(BigDecimal from, BigDecimal to){
        ProductInRange products = new ProductInRange();
        List<Product> allProducts = this.productRepository.findAllByPriceBetweenAndBuyerIsNull(from, to);
        List<ProductInPriceRangeViewModel> productsView = new ArrayList<>();
        for (Product p : allProducts){
            productsView.add(this.getProductView(p));
        }
        productsView = productsView.stream().sorted(Comparator.comparing(product -> product.getPrice())).collect(Collectors.toList());
        products.setProducts(productsView);
        return products;
    }
}
