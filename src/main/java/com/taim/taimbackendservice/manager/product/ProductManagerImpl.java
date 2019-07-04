package com.taim.taimbackendservice.manager.product;

import com.taim.taimbackendservice.mapper.CreateProductDTOMapper;
import com.taim.taimbackendservice.mapper.ProductDTOMapper;
import com.taim.taimbackendservice.model.Product;
import com.taim.taimbackendservice.repository.ProductRepository;
import com.taim.taimbackendservicemodel.CreateProductDTO;
import com.taim.taimbackendservicemodel.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductManagerImpl implements ProductManager {

    private final ProductRepository productRepository;
    private final CreateProductDTOMapper createProductDTOMapper;
    private final ProductDTOMapper productDTOMapper;

    @Autowired
    public ProductManagerImpl(ProductRepository productRepository,
                              CreateProductDTOMapper createProductDTOMapper,
                              ProductDTOMapper productDTOMapper) {
        this.productRepository = productRepository;
        this.createProductDTOMapper = createProductDTOMapper;
        this.productDTOMapper = productDTOMapper;
    }

    @Override
    @Transactional
    public ProductDTO getById(long id) {
        return this.productDTOMapper.convert(productRepository.getOne(id));
    }

    @Override
    @Transactional
    public ProductDTO getProductBySku(String sku) {
        return this.productDTOMapper.convert(productRepository.getProductBySku(sku));
    }

    @Override
    @Transactional
    public List<ProductDTO> getAllProducts() {
        List<Product> products = this.productRepository.findAll();
        return products.stream()
                .map(this.productDTOMapper::convert)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void saveProduct(CreateProductDTO createProductDTO) {
        Product product = this.createProductDTOMapper.map(createProductDTO);
        this.productRepository.saveAndFlush(product);
    }
}
