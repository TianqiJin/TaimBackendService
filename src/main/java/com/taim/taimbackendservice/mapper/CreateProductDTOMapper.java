package com.taim.taimbackendservice.mapper;

import com.google.common.base.Converter;
import com.taim.taimbackendservice.model.Product;
import com.taim.taimbackendservicemodel.CreateProductDTO;
import org.springframework.stereotype.Component;

@Component
public class CreateProductDTOMapper extends Converter<Product, CreateProductDTO> {
    @Override
    protected CreateProductDTO doForward(Product product) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Product doBackward(CreateProductDTO createProductDTO) {
        Product product = new Product();
        product.setSku(createProductDTO.getSku());
        product.setDisplayName(createProductDTO.getDisplayName());
        product.setDescription(createProductDTO.getDescription());
        product.setColor(createProductDTO.getColor());
        product.setTexture(createProductDTO.getTexture());
        product.setTotalNum(createProductDTO.getTotalNum());
        product.setTotalNumVirtual(createProductDTO.getTotalNumVirtual());
        product.setUnitPrice(createProductDTO.getUnitPrice());

        return product;
    }
}
