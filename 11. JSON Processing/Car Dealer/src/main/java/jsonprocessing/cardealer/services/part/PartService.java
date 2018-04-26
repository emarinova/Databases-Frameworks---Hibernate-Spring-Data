package jsonprocessing.cardealer.services.part;

import jsonprocessing.cardealer.entity.binding.PartCreateBindingModel;

public interface PartService {
    void save(PartCreateBindingModel model);
    void save(PartCreateBindingModel[] models);
    long count();
}
