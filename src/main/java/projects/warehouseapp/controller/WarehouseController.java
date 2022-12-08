package projects.warehouseapp.controller;


import com.fasterxml.jackson.annotation.JsonGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import projects.warehouseapp.entity.WarehouseEntity;
import projects.warehouseapp.service.WarehouseService;
import projects.warehouseapp.service.exception.WarehouseNotFoundException;

import java.util.List;

@Controller
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @GetMapping("getWarehouses")
    public String viewWarehouses(Model model) {
        List<WarehouseEntity> warehouseEntity = warehouseService.getWarehouses();
        model.addAttribute("warehouses", warehouseEntity);
        return "view-warehouse";
    }

    @GetMapping("addWarehouses")
    public String addWarehousePage(Model model) {
        model.addAttribute("warehouse", new WarehouseEntity());
        return "add-warehouses";
    }

    @PostMapping("add-new-warehouse")
    public String addNewWarehouse(WarehouseEntity warehouse) {
        warehouseService.addWarehouse(warehouse);
        return "redirect:/getWarehouses";
    }

    @GetMapping("edit-warehouse/{warehouseId}")
    public String editWarehousePage(@PathVariable("warehouseId") int id, Model model) throws WarehouseNotFoundException {

        WarehouseEntity warehouseEntity = warehouseService.getWarehouse(id);

        model.addAttribute("warehouse", warehouseEntity);

        return "edit-warehouse";
    }

    @PostMapping("edit-warehouse")
    public String editWarehouse(WarehouseEntity warehouseEntity) throws WarehouseNotFoundException {

      warehouseService.updateWarehouse(warehouseEntity);

        return "redirect:/getWarehouses";
    }

    @GetMapping("delete-warehouse-page/{warehouseId}")
    public String deleteWarehouse(@PathVariable("id") int id) {
        warehouseService.deleteWarehouse(id);
        return "redirect:/getWarehouses";
    }

//    @GetMapping("see-warehouse-products/{id}")
//    public String seeProducts(@PathVariable("id") int id) {
//        warehouseService.getWarehouseProducts(id);
//        return
//    }
}
