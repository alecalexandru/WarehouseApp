package projects.warehouseapp.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import projects.warehouseapp.entity.ProductEntity;
import projects.warehouseapp.entity.WarehouseEntity;
import projects.warehouseapp.service.ProductService;
import projects.warehouseapp.service.WarehouseService;
import projects.warehouseapp.service.exception.ProductNotFoundException;
import projects.warehouseapp.service.exception.WarehouseNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class ProductUi {

    @Autowired
    private ProductService productService;

    @Autowired
    private WarehouseService warehouseService;

    int option;

    public void startUi() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("0.Exit");
            System.out.println("1.Add product");
            System.out.println("2.View product");
            System.out.println("3.View all products");
            System.out.println("4.Cahnge product price");
            System.out.println("5.Remove product by id");
            System.out.println("6.Associate product to warehouse");
            System.out.println("7.Order products by price");
            option = scanner.nextInt();
            scanner.nextLine();
            if (option == 1) {
                System.out.println("Enter id of the product");
                int productId = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter name of the product");
                String name = scanner.nextLine();
                System.out.println("Enter product quantity");
                int quantity = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter product description");
                String description = scanner.nextLine();
                System.out.println("Enter product category");
                String category = scanner.nextLine();
                System.out.println("Enter price of the product");
                float price = scanner.nextFloat();
                scanner.nextLine();
                ProductEntity productEntity = new ProductEntity();
                productEntity.setId(productId);
                productEntity.setName(name);
                productEntity.setCategory(category);
                productEntity.setPrice(price);
                productService.addProduct(productEntity);
            } else if (option == 2) {
                System.out.println("Enter product id");
                int option = scanner.nextInt();
                scanner.nextLine();
                try {
                    ProductEntity productEntity = productService.getProduct(option);
                    System.out.println(productEntity);
                } catch (ProductNotFoundException productNotFoundException) {
                    System.out.println(productNotFoundException.getMessage());
                }
            } else if (option == 3) {
                List<ProductEntity> productEntities = productService.getProducts();
                for (ProductEntity product : productEntities) {
                    System.out.print(product.getId() + ". " + product.getName() + " | " +
                            product.getPrice() + " lei | ");

                    WarehouseEntity warehouseEntity = product.getWarehouse();
                    if (warehouseEntity != null) {
                        System.out.print(product.getWarehouse() + " warehouse |");
                    }
                    System.out.println();
                }
            } else if (option == 4) {
                System.out.println("Enter product id");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter the new product price");
                float price = scanner.nextFloat();
                scanner.nextLine();
                try {
                    productService.changeProductPrice(id, price);

                } catch (ProductNotFoundException productNotFoundException) {
                    System.out.println(productNotFoundException.getMessage());
                }
            } else if (option == 5) {
                System.out.println("Enter product id that will be deleted");
                int id = scanner.nextInt();
                scanner.nextLine();
                try {
                    productService.deleteProductById(id);
                } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
                    System.out.println("Product with id " + id + " doesn't exist!");
                }
            } else if (option == 6) {
                System.out.println("Enter product id");
                int productId = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter warehouse id");
                int warehouseId = scanner.nextInt();
                scanner.nextLine();
                try {
                    productService.addProductInWarehouse(productId, warehouseId);
                } catch (ProductNotFoundException | WarehouseNotFoundException exception) {
                    System.out.println(exception.getMessage());
                }
            } else if (option == 7) {
                System.out.println("Press 1 for ascending order or 2 for descending order");
                int optiune = scanner.nextInt();
                scanner.nextLine();
                if (optiune == 1) {
                    List<ProductEntity> productEntities = productService.getProductsByPrice2();
                    for (ProductEntity product : productEntities) {
                        System.out.println(product.getId() + ". " + product.getName() + " | " +
                                product.getPrice() + " lei | " + product.getWarehouse() + " warehouse |");
                    }
                } else if (optiune == 2) {
                    List<ProductEntity> productEntities = productService.getProductsByPrice1();
                    for (ProductEntity product : productEntities) {
                        System.out.println(product.getId() + ". " + product.getName() + " | " +
                                product.getPrice() + " lei | " + product.getWarehouse() + " warehouse |");
                    }
                }
            }

        } while (option != 0);

    }
}
