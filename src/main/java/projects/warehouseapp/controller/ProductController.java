package projects.warehouseapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import projects.warehouseapp.entity.ProductEntity;
import projects.warehouseapp.entity.WarehouseEntity;
import projects.warehouseapp.service.ProductService;
import projects.warehouseapp.service.WarehouseService;
import projects.warehouseapp.service.exception.ProductNotFoundException;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private WarehouseService warehouseService;

    @GetMapping("getProducts")
    public String viewProducts(Model model) {

        List<ProductEntity> productEntities = productService.getProducts();

        model.addAttribute("products", productEntities);
        return "view-products";
    }

    @GetMapping("addProducts")
    public String addProductsPage(Model model) {

        List<WarehouseEntity> warehousesList = warehouseService.getWarehouses();

        model.addAttribute("product", new ProductEntity());
        model.addAttribute("warehouses", warehousesList);

        return "add-products";
    }

    @PostMapping("add-new-product")
    public String addNewProduct(ProductEntity product) {
        productService.addProduct(product);
        return "redirect:/getProducts";
    }

    @GetMapping("edit-product-page/{productId}")
    public String editProductPage(@PathVariable("productId") int productId, Model model) throws ProductNotFoundException {

        ProductEntity productEntity = productService.getProduct(productId);
        model.addAttribute("product", productEntity);

        List<WarehouseEntity> warehouseList = warehouseService.getWarehouses();
        model.addAttribute("warehouses", warehouseList);

        return "edit-product";
    }

    @PostMapping("edit-product")
    public String editProduct(ProductEntity productEntity) throws ProductNotFoundException {

        productService.updateProduct(productEntity);

        return "redirect:/getProducts";
    }

    @GetMapping("delete-product-page/{id}")
    public String deleteProduct(@PathVariable("id") int productId) {

        productService.deleteProductById(productId);

        return "redirect:/getProducts";
    }

}
