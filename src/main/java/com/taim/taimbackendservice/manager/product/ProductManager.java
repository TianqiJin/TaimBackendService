package com.taim.taimbackendservice.manager.product;

import com.taim.taimbackendservicemodel.CreateProductDTO;
import com.taim.taimbackendservicemodel.ProductDTO;

import java.util.List;

public interface ProductManager {
    ProductDTO getProductBySku(String sku);
    List<ProductDTO> getAllProducts();
    void saveProduct(CreateProductDTO createProductDTO);
    ProductDTO getById(long id);
}
