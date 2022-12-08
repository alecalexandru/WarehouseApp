package projects.warehouseapp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import projects.warehouseapp.entity.WarehouseEntity;
import projects.warehouseapp.repository.WarehouseRepository;
import projects.warehouseapp.service.exception.WarehouseNotFoundException;

import java.util.List;
import java.util.Optional;

@Component
public class WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    public void addWarehouse(WarehouseEntity warehouse) {
        warehouseRepository.save(warehouse);
    }

    public void deleteWarehouse(int warehouseId)  {

        warehouseRepository.deleteById(warehouseId);
    }

    public void deleteWarehouse(String warehouseName)  {
        warehouseRepository.deleteWarehouseByName(warehouseName);
    }

    public List<WarehouseEntity> getWarehouses() {
        List<WarehouseEntity> warehouses = warehouseRepository.findAll();
        return warehouses;
    }

    public WarehouseEntity getWarehouse(int id) throws WarehouseNotFoundException {
        Optional<WarehouseEntity> optionalWarehouse = warehouseRepository.findById(id);
        if (optionalWarehouse.isEmpty()) {
            throw new WarehouseNotFoundException("Warehouse with id:" + id + " doesn't exit!");
        }

        WarehouseEntity warehouseEntity = optionalWarehouse.get();
        return warehouseEntity;
    }

    public void updateWarehouse(WarehouseEntity modifiedWarehouse) throws WarehouseNotFoundException {
       WarehouseEntity existingWarehouse = getWarehouse(modifiedWarehouse.getId());
       existingWarehouse.setAge(modifiedWarehouse.getAge());
       existingWarehouse.setName(modifiedWarehouse.getName());
       warehouseRepository.save(existingWarehouse);
    }
}
