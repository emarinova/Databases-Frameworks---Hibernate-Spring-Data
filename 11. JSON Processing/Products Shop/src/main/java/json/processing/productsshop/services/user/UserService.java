package json.processing.productsshop.services.user;

import json.processing.productsshop.models.binding.user.UserCreateBindingModel;
import json.processing.productsshop.models.view.user.UserProductsViewModel;
import json.processing.productsshop.models.view.user.UserSoldItemsViewModel;

import java.util.List;

public interface UserService {

    void save(UserCreateBindingModel model);
    void save(UserCreateBindingModel[] models);
    List<UserSoldItemsViewModel> usersWithSoldItems();
    List<UserProductsViewModel> usersAndProducts();
}
