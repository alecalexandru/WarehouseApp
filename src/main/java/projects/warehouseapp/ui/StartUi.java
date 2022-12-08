package projects.warehouseapp.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import projects.warehouseapp.service.exception.ProductNotFoundException;

import java.util.Scanner;

@Component
public class StartUi {

    @Autowired
    private ProductUi productUi;

    @Autowired
    private WarehouseUi warehouseUi;
    int option;

    public void startUi() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("1.Product management");
            System.out.println("2.Warehouse management");
            option = scanner.nextInt();
            scanner.nextLine();
            if (option == 1) {
                productUi.startUi();
            } else if (option == 2) {
                warehouseUi.startUi();
            }
        } while (option != 0);
    }
}
