package com.taim.taimbackendservice.mapper;

import com.taim.taimbackendservice.model.Product;
import com.taim.taimbackendservicemodel.CreateProductDTO;
import org.springframework.stereotype.Component;

@Component
public class CreateProductDTOMapper {

    public Product map(CreateProductDTO createProductDTO) {
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
