package jsonprocessing.cardealer.services.part;

import jsonprocessing.cardealer.entity.binding.PartCreateBindingModel;
import jsonprocessing.cardealer.entity.model.Part;
import jsonprocessing.cardealer.entity.model.Supplier;
import jsonprocessing.cardealer.repositories.PartRepository;
import jsonprocessing.cardealer.repositories.SupplierRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PartServiceImpl implements PartService {

    private final ModelMapper mapper = new ModelMapper();
    private final PartRepository partRepository;
    private final SupplierRepository supplierRepository;

    public PartServiceImpl(PartRepository partRepository, SupplierRepository supplierRepository) {
        this.partRepository = partRepository;
        this.supplierRepository = supplierRepository;
    }


    @Override
    public void save(PartCreateBindingModel model) {
        Part part = mapper.map(model, Part.class);
        Supplier supplier = this.supplierRepository.findById(model.getSupplier_id());
        supplier.getParts().add(part);
        part.setSupplier(supplier);
        this.partRepository.saveAndFlush(part);
    }

    @Override
    public void save(PartCreateBindingModel[] models) {
        for(PartCreateBindingModel model : models){
            this.save(model);
        }
    }

    @Override
    public long count() {
        return this.partRepository.count();
    }
}
