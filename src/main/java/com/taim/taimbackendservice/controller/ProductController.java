package com.taim.taimbackendservice.controller;

import com.taim.taimbackendservice.mapper.CreateProductDTOMapper;
import com.taim.taimbackendservice.mapper.ProductDTOMapper;
import com.taim.taimbackendservice.model.Product;
import com.taim.taimbackendservice.service.product.IProductService;
import com.taim.taimbackendservicemodel.CreateProductDTO;
import com.taim.taimbackendservicemodel.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    private final IProductService productService;
    private final ProductDTOMapper productDTOMapper;

    private final CreateProductDTOMapper createProductDTOMapper;

    @Autowired
    public ProductController(IProductService productService,
                             ProductDTOMapper productDTOMapper,
                             CreateProductDTOMapper createProductDTOMapper) {
        this.productService = productService;
        this.productDTOMapper = productDTOMapper;
        this.createProductDTOMapper = createProductDTOMapper;
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/products",
            params = "action=getAll"
    )
    public List<ProductDTO> getAllProducts() {
        return this.productService.getAll().stream()
                .map(this.productDTOMapper::convert)
                .collect(Collectors.toList());
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/products",
            params = "action=getById"
    )
    public ProductDTO getProductById(@RequestParam("id") long id) {
        return this.productDTOMapper.convert(this.productService.getById(id));
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/products",
            params = "action=save"
    )
    public ProductDTO saveProduct(@RequestBody CreateProductDTO createProductDTO) {
        Product product = this.createProductDTOMapper.reverse().convert(createProductDTO);

        return this.productDTOMapper.convert(this.productService.save(product));
    }
}
