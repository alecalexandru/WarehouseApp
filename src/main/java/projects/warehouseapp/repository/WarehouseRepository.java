package projects.warehouseapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import projects.warehouseapp.entity.WarehouseEntity;

import javax.transaction.Transactional;

@Repository
public interface WarehouseRepository extends JpaRepository<WarehouseEntity, Integer> {
    @Transactional
    @Modifying
    @Query(value = "delete from warehouse where name = :warehouseName", nativeQuery = true)
    void deleteWarehouseByName(@Param("warehouseName") String name);
}
