package projects.warehouseapp.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import projects.warehouseapp.entity.WarehouseEntity;
import projects.warehouseapp.service.WarehouseService;
import projects.warehouseapp.service.exception.WarehouseNotFoundException;

import java.util.List;
import java.util.Scanner;

@Component
public class WarehouseUi {

    @Autowired
    WarehouseService warehouseService;

    int option;

    public void startUi() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("0.Exit");
            System.out.println("1.Add warehouse");
            System.out.println("2.View warehouses");
            System.out.println("3.Remove warehouse by id");
            System.out.println("4.Remove warehouse by name");
            option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                System.out.println("Enter name of the warehouse");
                String name = scanner.nextLine();
                System.out.println("Enter age of the warehouse");
                int age = scanner.nextInt();
                scanner.nextLine();
                WarehouseEntity warehouseEntity = new WarehouseEntity();
                warehouseEntity.setName(name);
                warehouseEntity.setAge(age);
                warehouseService.addWarehouse(warehouseEntity);
            } else if (option == 2) {
                List<WarehouseEntity> warehouseEntityList = warehouseService.getWarehouses();
                for (WarehouseEntity warehouseEntity : warehouseEntityList) {
                    System.out.println(warehouseEntity.getId() + ". " + warehouseEntity.getName() + " warehouse - " + warehouseEntity.getAge() + " years old");
                }
            } else if (option == 3) {
                System.out.println("Enter id of warehouse that will be deleted");
                int departmentId = scanner.nextInt();
                scanner.nextLine();
                try {
                    warehouseService.deleteWarehouse(departmentId);
                } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
                    System.out.println("Warehouse with id:" + departmentId + " doesn't exist!");
                }
            } else if (option == 4) {
                System.out.println("Enter name of the warehouse that will be deleted");
                String warehouseName = scanner.nextLine();
                try {
                    warehouseService.deleteWarehouse(warehouseName);
                } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
                    System.out.println("Warehouse with name:" + warehouseName + " doesn't exist!");
                }
            }
        } while (option != 0);
    }
}
