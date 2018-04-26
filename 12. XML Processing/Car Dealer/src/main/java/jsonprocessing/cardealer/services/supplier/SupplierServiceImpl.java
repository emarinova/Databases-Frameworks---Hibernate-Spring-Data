package jsonprocessing.cardealer.services.supplier;

import jsonprocessing.cardealer.entity.binding.SupplierCreateBindingModel;
import jsonprocessing.cardealer.entity.model.Supplier;
import jsonprocessing.cardealer.entity.view.Query3.SupplierLocalViewModel;
import jsonprocessing.cardealer.parser.interfaces.ModelParser;
import jsonprocessing.cardealer.repositories.SupplierRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {

    private final ModelParser mapper;
    private final SupplierRepository supplierRepository;

    public SupplierServiceImpl(ModelParser mapper, SupplierRepository supplierRepository) {
        this.mapper = mapper;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public void save(SupplierCreateBindingModel model) {
        Supplier supplier = mapper.convert(model, Supplier.class);
        this.supplierRepository.saveAndFlush(supplier);
    }

    @Override
    public void save(SupplierCreateBindingModel[] models) {
        for(SupplierCreateBindingModel model : models){
            this.save(model);
        }
    }

    @Override
    public long count() {
        return this.supplierRepository.count();
    }

    @Override
    public List<SupplierLocalViewModel> localSuppliers() {
        return this.supplierRepository.localSuppliers();
    }
}
