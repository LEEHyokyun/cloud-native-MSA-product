package com.msa.product.service;

import com.msa.product.model.entity.Product;
import com.msa.product.model.request.ProductCreateRequest;
import com.msa.product.model.response.ProductResponse;
import com.msa.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public ProductResponse create(ProductCreateRequest productCreateRequest) {
        Product product = Product.create(
                productCreateRequest.getProductName(),
                productCreateRequest.getProductQty(),
                productCreateRequest.getProductPrice()
        );

        productRepository.save(product);

        return ProductResponse.from(product);
    }

    public ProductResponse readProduct(Long productId) {
        return ProductResponse.from(productRepository.findById(productId).orElse(null));
    }

    public List<ProductResponse> readAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductResponse::from)//Entity > Dto
                .toList();
    }

}
