package json.processing.productsshop.services.user;

import json.processing.productsshop.models.binding.user.UserCreateBindingModel;
import json.processing.productsshop.models.binding.user.UsersCreate;
import json.processing.productsshop.models.entity.Product;
import json.processing.productsshop.models.entity.User;
import json.processing.productsshop.models.view.product.Query2.ProductBuyerViewModel;
import json.processing.productsshop.models.view.product.Query2.ProductsSoldWIthBuyers;
import json.processing.productsshop.models.view.product.Query4.ProductsSold;
import json.processing.productsshop.models.view.user.Query2.UserSoldItemsViewModel;
import json.processing.productsshop.models.view.user.Query2.UsersSoldItems;
import json.processing.productsshop.models.view.user.Query4.UserProductsViewModel;
import json.processing.productsshop.models.view.user.Query4.UsersProducts;
import json.processing.productsshop.repositories.ProductRepository;
import json.processing.productsshop.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ModelMapper mapper;
    //private final ModelParser mapper;

    public UserServiceImpl(UserRepository userRepository, ProductRepository productRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public void save(UsersCreate model) {
        for(UserCreateBindingModel m : model.getUsers()){
            this.save(m);
        }
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
    public UsersSoldItems usersWithSoldItems(){
        UsersSoldItems users = new UsersSoldItems();
        List<User> allWithSoldItems = this.userRepository.findAllDistinctByProductsSoldIsNotNull();
        for (User u : allWithSoldItems){
            List<Product> products = this.productRepository.findAllBySellerAndBuyerNotNull(u);
            if (products.size()>0) {
                ProductsSoldWIthBuyers productsSold = new ProductsSoldWIthBuyers();
                ProductBuyerViewModel[] productsWithBuyer = mapper.map(products, ProductBuyerViewModel[].class);
                productsSold.setProducts(productsWithBuyer);

                UserSoldItemsViewModel userWithSoldItems = new UserSoldItemsViewModel(u.getFirstName(), u.getLastName(), productsSold);
                users.getUsers().add(userWithSoldItems);
            }
        }
        Comparator<UserSoldItemsViewModel> compByLastName = Comparator.comparing(us -> us.getLastName());
        users.setUsers(users.getUsers().stream().sorted(compByLastName).collect(Collectors.toList()));
        return users;
    }

    @Override
    public UsersProducts usersAndProducts(){
        List<User> allWithSoldItems = this.userRepository.findAllDistinctByProductsSoldIsNotNull();
        UsersProducts users = new UsersProducts();
        users.setCount(allWithSoldItems.size());
        for(User user : allWithSoldItems){
            ProductsSold products = new ProductsSold();
            products.setProducts(this.productRepository.findAllByOneUser(user.getId()));
            products.setCount(products.getProducts().size());
            users.getUsers().add(new UserProductsViewModel(user.getFirstName(),user.getLastName(),user.getAge(),products));
        }
        return users;
    }
}
