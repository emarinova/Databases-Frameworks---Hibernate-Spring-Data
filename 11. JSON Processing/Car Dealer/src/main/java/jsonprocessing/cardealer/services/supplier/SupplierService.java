package jsonprocessing.cardealer.services.supplier;

import jsonprocessing.cardealer.entity.binding.SupplierCreateBindingModel;
import jsonprocessing.cardealer.entity.view.Query3.SupplierLocalViewModel;

import java.util.List;

public interface SupplierService {
    void save(SupplierCreateBindingModel model);
    void save(SupplierCreateBindingModel[] models);
    long count();
    List<SupplierLocalViewModel> localSuppliers();
}
