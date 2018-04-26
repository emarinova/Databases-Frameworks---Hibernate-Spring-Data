package json.processing.productsshop.services.user;

import json.processing.productsshop.models.binding.user.UserCreateBindingModel;
import json.processing.productsshop.models.binding.user.UsersCreate;
import json.processing.productsshop.models.view.user.Query2.UsersSoldItems;
import json.processing.productsshop.models.view.user.Query4.UsersProducts;

public interface UserService {

    void save(UsersCreate model);
    void save(UserCreateBindingModel model);
    void save(UserCreateBindingModel[] models);
    UsersSoldItems usersWithSoldItems();
    UsersProducts usersAndProducts();
}
