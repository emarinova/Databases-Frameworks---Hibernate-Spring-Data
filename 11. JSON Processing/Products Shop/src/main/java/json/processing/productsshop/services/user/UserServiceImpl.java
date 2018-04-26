package json.processing.productsshop.services.user;

import json.processing.productsshop.models.binding.user.UserCreateBindingModel;
import json.processing.productsshop.models.entity.Product;
import json.processing.productsshop.models.entity.User;
import json.processing.productsshop.models.view.product.ProductBuyerViewModel;
import json.processing.productsshop.models.view.product.ProductSoldViewModel;
import json.processing.productsshop.models.view.product.ProductsSold;
import json.processing.productsshop.models.view.user.UserProductsViewModel;
import json.processing.productsshop.models.view.user.UserSoldItemsViewModel;
import json.processing.productsshop.repositories.ProductRepository;
import json.processing.productsshop.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository, ProductRepository productRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public void save(UserCreateBindingModel model) {
        User u = this.mapper.map(model, User.class);
        this.userRepository.saveAndFlush(u);
    }

    @Override
    public void save(UserCreateBindingModel[] models) {
        User[] users = this.mapper.map(models, User[].class);
        for(User u : users){
            this.userRepository.saveAndFlush(u);
        }
    }

    @Override
    public List<UserSoldItemsViewModel> usersWithSoldItems(){
        List<UserSoldItemsViewModel> usersWithSoldItems = new ArrayList<>();
        List<User> allWithSoldItems = this.userRepository.findAllDistinctByProductsSoldIsNotNull();
        for (User u : allWithSoldItems){
            List<Product> products = this.productRepository.findAllBySellerAndBuyerNotNull(u);
            if (products.size()>0) {
                ProductBuyerViewModel[] productsWithBuyer = mapper.map(products, ProductBuyerViewModel[].class);
                UserSoldItemsViewModel userWithSoldItems = new UserSoldItemsViewModel(u.getFirstName(), u.getLastName(), productsWithBuyer);
                usersWithSoldItems.add(userWithSoldItems);
            }
        }
        return usersWithSoldItems;
    }

    @Override
    public List<UserProductsViewModel> usersAndProducts(){
        List<User> allWithSoldItems = this.userRepository.findAllDistinctByProductsSoldIsNotNull();
        List<UserProductsViewModel> users = new ArrayList<>();
        for(User u : allWithSoldItems){

            List<ProductSoldViewModel> products = this.productRepository.findAllByOneUser(u.getId());
            int count = products.size();
            ProductsSold productsSold = new ProductsSold(count, products);
            users.add(new UserProductsViewModel(u.getFirstName(),u.getLastName(),u.getAge(),productsSold));
        }
        return users;
    }
}
