package projects.warehouseapp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import projects.warehouseapp.entity.ProductEntity;
import projects.warehouseapp.entity.WarehouseEntity;
import projects.warehouseapp.repository.ProductRepository;
import projects.warehouseapp.repository.WarehouseRepository;
import projects.warehouseapp.service.exception.ProductNotFoundException;
import projects.warehouseapp.service.exception.WarehouseNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    public void addProduct(ProductEntity product) {
        productRepository.save(product);
    }

    public List<ProductEntity> getProducts() {
        List<ProductEntity> products = productRepository.findAll();
        return products;
    }



        public ProductEntity getProduct(int id) throws ProductNotFoundException {
        Optional<ProductEntity> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException("Product with id:" + id + " doesn't exist!");
        }
        ProductEntity product = optionalProduct.get();
        return product;
    }


    public void changeProductPrice(int id, float price) throws ProductNotFoundException {
        Optional<ProductEntity> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException("Product with id:" + id + " doesn't exist!");
        }
        ProductEntity productEntity = optionalProduct.get();
        productEntity.setPrice(price);
        productRepository.save(productEntity);
    }

    public void updateProduct(ProductEntity modifiedProduct) throws ProductNotFoundException {

        ProductEntity existingProduct = getProduct(modifiedProduct.getId());

        existingProduct.setCategory(modifiedProduct.getCategory());
        existingProduct.setPrice(modifiedProduct.getPrice());
        existingProduct.setId(modifiedProduct.getId());
        existingProduct.setName(modifiedProduct.getName());
        existingProduct.setWarehouse(modifiedProduct.getWarehouse());
        existingProduct.setQuantity(modifiedProduct.getQuantity());
        existingProduct.setDescription(modifiedProduct.getDescription());

        productRepository.save(existingProduct);
    }

    public void deleteProductById(int id) {
        productRepository.deleteById(id);
    }

    public void addProductInWarehouse(int productId, int warehouseId) throws
            ProductNotFoundException, WarehouseNotFoundException {
        Optional<ProductEntity> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException("Product with id " + productId + " doesn't exist!");
        }
        ProductEntity productEntity = optionalProduct.get();

        Optional<WarehouseEntity> optionalWarehouse = warehouseRepository.findById(warehouseId);
        if (optionalWarehouse.isEmpty()) {
            throw new WarehouseNotFoundException("Warehouse with id " + warehouseId + " doesn't exist!");
        }
        WarehouseEntity warehouseEntity = optionalWarehouse.get();

        productEntity.setWarehouse(warehouseEntity);

        productRepository.save(productEntity);
    }

    public List<ProductEntity> getProductsByPrice1() {
        return productRepository.orderProductsByPrice1();
    }

    public List<ProductEntity> getProductsByPrice2() {
        return productRepository.orderProductsByPrice2();
    }

}
