package com.taim.taimbackendservice.mapper;

import com.google.common.base.Converter;
import com.taim.taimbackendservice.model.Product;

import com.taim.taimbackendservicemodel.ProductDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductDTOMapper extends Converter<Product, ProductDTO> {


    @Override
    protected ProductDTO doForward(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .sku(product.getSku())
                .displayName(product.getDisplayName())
                .description(product.getDescription())
                .color(product.getColor())
                .texture(product.getTexture())
                .totalNum(product.getTotalNum())
                .totalNumVirtual(product.getTotalNumVirtual())
                .unitPrice(product.getUnitPrice())
                .build();
    }

    @Override
    protected Product doBackward(ProductDTO productItem) {
        throw new UnsupportedOperationException();
    }

}
