package cn.edu.demo.shop;
import org.springframework.data.jpa.repository.JpaRepository; import java.util.List; import java.util.Optional;
public interface ProductRepository extends JpaRepository<Product,Long>{ List<Product> findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(String name,String category); boolean existsByName(String name); Optional<Product> findByName(String name); }
