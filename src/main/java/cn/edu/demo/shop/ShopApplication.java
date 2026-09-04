package cn.edu.demo.shop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.math.BigDecimal;

@SpringBootApplication
public class ShopApplication {
  public static void main(String[] args) { SpringApplication.run(ShopApplication.class, args); }
  @Bean CommandLineRunner seed(ProductRepository products, UserRepository users) {
    return args -> {
      if (products.count() == 0) {
        products.save(new Product(null,"五常稻花香大米","粮油米面","黑龙江五常核心产区，颗粒饱满，米香自然。",new BigDecimal("79.90"),120,"https://images.unsplash.com/photo-1586201375761-83865001e31c?auto=format&fit=crop&w=900&q=80",true,true));
        products.save(new Product(null,"长白山黑木耳","山珍菌菇","肉厚脆嫩，适合凉拌、炖菜和日常家庭烹饪。",new BigDecimal("36.80"),86,"https://images.unsplash.com/photo-1504545102780-26774c1bb073?auto=format&fit=crop&w=900&q=80",true,false));
        products.save(new Product(null,"哈尔滨红肠","肉类熟食","蒜香与烟熏风味协调，开袋即食。",new BigDecimal("45.00"),65,"https://images.unsplash.com/photo-1598103442097-8b74394b95c6?auto=format&fit=crop&w=900&q=80",true,true));
        products.save(new Product(null,"延边辣白菜","佐餐食品","酸辣爽脆，低温发酵，适合佐餐和炒饭。",new BigDecimal("24.90"),140,"https://images.unsplash.com/photo-1583224964978-2257b960c3d3?auto=format&fit=crop&w=900&q=80",false,true));
        products.save(new Product(null,"丹东草莓果酱","休闲食品","以草莓果肉熬制，适合面包、酸奶和甜点。",new BigDecimal("29.80"),72,"https://images.unsplash.com/photo-1490474418585-ba9bad8fd0ea?auto=format&fit=crop&w=900&q=80",false,true));
        products.save(new Product(null,"东北榛子","坚果炒货","颗粒均匀，香脆易剥，适合日常零食。",new BigDecimal("39.90"),95,"https://images.unsplash.com/photo-1599599810769-bcde5a160d32?auto=format&fit=crop&w=900&q=80",true,false));
      }
      if (users.count() == 0) {
        users.save(new AppUser(null,"admin","admin123","系统管理员","admin@example.com","13800000000","哈尔滨市南岗区",true));
        users.save(new AppUser(null,"demo","demo123","演示用户","demo@example.com","13900000000","长春市朝阳区",false));
      }
    };
  }
}
