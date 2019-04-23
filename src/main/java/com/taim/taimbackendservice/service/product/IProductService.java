package com.taim.taimbackendservice.service.product;

import com.taim.taimbackendservice.model.Product;
import com.taim.taimbackendservice.service.IBaseService;

public interface IProductService extends IBaseService<Product> {
    Product getProductBySku(String sku);
}
