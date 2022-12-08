package projects.warehouseapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import projects.warehouseapp.entity.ProductEntity;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    @Query(value = "SELECT * FROM product ORDER BY price DESC", nativeQuery = true)
    List<ProductEntity> orderProductsByPrice2();

    @Query(value = "SELECT * FROM product ORDER BY price ASC", nativeQuery = true)
    List<ProductEntity> orderProductsByPrice1();

}
