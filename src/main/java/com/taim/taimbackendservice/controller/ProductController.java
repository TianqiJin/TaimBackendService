package com.taim.taimbackendservice.controller;

import com.taim.taimbackendservice.manager.product.ProductManager;
import com.taim.taimbackendservicemodel.CreateProductDTO;
import com.taim.taimbackendservicemodel.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductManager productManager;

    @Autowired
    public ProductController(ProductManager productManager) {
        this.productManager = productManager;
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/products",
            params = "action=getAll"
    )
    public List<ProductDTO> getAllProducts() {
        return this.productManager.getAllProducts();
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/products",
            params = "action=getById"
    )
    public ProductDTO getProductById(@RequestParam("id") long id) {
        return this.productManager.getById(id);
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/products",
            params = "action=save"
    )
    public void saveProduct(@RequestBody CreateProductDTO createProductDTO) {
        this.productManager.saveProduct(createProductDTO);
    }
}
