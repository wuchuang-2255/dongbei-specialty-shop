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
      products.findByName("长白山黑木耳").ifPresent(p -> { p.setImage("https://imgservice.suning.cn/uimg1/b2c/image/JU_Mj7L2Ew3QhW1NxRxpAw.jpg_800w_800h_4e_80Q_is"); products.save(p); });
      add(products,"长白山人参","滋补山珍","参形完整，适合煲汤、泡茶与节日赠礼。","168.00",35,"https://images.unsplash.com/photo-1615485500704-8e990f9900f7?auto=format&fit=crop&w=900&q=80",true);
      add(products,"东北野生松子","坚果炒货","颗粒饱满，松香自然，开口易剥。","58.80",78,"https://images.unsplash.com/photo-1508747703725-719777637510?auto=format&fit=crop&w=900&q=80",false);
      add(products,"铁岭榛蘑","山珍菌菇","东北炖菜常用山珍，香气浓郁。","42.80",66,"https://images.unsplash.com/photo-1512595765784-5ebad80772a3?auto=format&fit=crop&w=900&q=80",false);
      add(products,"吉林猴头菇","山珍菌菇","肉质厚实，适合煲汤和炖菜。","49.90",52,"https://images.unsplash.com/photo-1504545102780-26774c1bb073?auto=format&fit=crop&w=900&q=80",true);
      add(products,"黑龙江蓝莓干","休闲食品","酸甜柔韧，适合办公室零食。","32.90",105,"https://images.unsplash.com/photo-1490474418585-ba9bad8fd0ea?auto=format&fit=crop&w=900&q=80",false);
      add(products,"大兴安岭沙果干","休闲食品","果香清新，酸甜开胃。","26.80",93,"https://images.unsplash.com/photo-1596591606975-97ee5cef3a1e?auto=format&fit=crop&w=900&q=80",false);
      add(products,"东北冻梨","时令水果","东北冬季特色吃法，解冻后清甜多汁。","29.90",60,"https://images.unsplash.com/photo-1568702846914-96b305d2aaeb?auto=format&fit=crop&w=900&q=80",false);
      add(products,"辽宁南果梨","时令水果","果香浓郁，肉质细腻。","55.00",80,"https://images.unsplash.com/photo-1514756331096-242fdeb70d4a?auto=format&fit=crop&w=900&q=80",false);
      add(products,"丹东黄蚬子","海产干货","鲜味突出，冷链包装发货。","69.90",48,"https://images.unsplash.com/photo-1498654200943-1088dd4438ae?auto=format&fit=crop&w=900&q=80",false);
      add(products,"大连烤鱼片","海产干货","咸香有嚼劲，独立小包装。","35.80",110,"https://images.unsplash.com/photo-1535400255456-984241443b29?auto=format&fit=crop&w=900&q=80",false);
      add(products,"锦州小菜","佐餐食品","清脆爽口，早餐佐粥方便。","19.90",130,"https://images.unsplash.com/photo-1547592180-85f173990554?auto=format&fit=crop&w=900&q=80",false);
      add(products,"东北酸菜","佐餐食品","自然发酵，适合炖粉条和汆白肉。","16.80",160,"https://images.unsplash.com/photo-1583224964978-2257b960c3d3?auto=format&fit=crop&w=900&q=80",true);
      add(products,"延边冷面","粮油米面","面条筋道，附带风味汤料。","22.90",125,"https://images.unsplash.com/photo-1569718212165-3a8278d5f624?auto=format&fit=crop&w=900&q=80",false);
      add(products,"东北土豆粉条","粮油米面","久煮不易断，适合炖菜与火锅。","18.80",145,"https://images.unsplash.com/photo-1552611052-33e04de081de?auto=format&fit=crop&w=900&q=80",false);
      add(products,"盘锦蟹田大米","粮油米面","米粒整齐，口感软糯。","72.90",98,"https://images.unsplash.com/photo-1586201375761-83865001e31c?auto=format&fit=crop&w=900&q=80",true);
      add(products,"农家粘豆包","传统糕点","黄米外皮配豆沙馅，蒸热食用。","33.80",74,"https://images.unsplash.com/photo-1578985545062-69928b1d9587?auto=format&fit=crop&w=900&q=80",false);
      add(products,"沈阳不老林糖","传统糕点","经典花生与奶香风味。","27.90",115,"https://images.unsplash.com/photo-1581798459219-318e76aecc7b?auto=format&fit=crop&w=900&q=80",false);
      add(products,"吉林煎粉礼盒","地方小吃","配料齐全，在家即可制作地方风味。","38.80",57,"https://images.unsplash.com/photo-1547592180-85f173990554?auto=format&fit=crop&w=900&q=80",false);
      add(products,"齐齐哈尔烤肉蘸料","调味品","孜然芝麻复合香味，适合烧烤。","15.90",180,"https://images.unsplash.com/photo-1596040033229-a9821ebd058d?auto=format&fit=crop&w=900&q=80",false);
      add(products,"延边辣椒酱","调味品","辣味柔和，适合拌饭、拌面和腌菜。","21.80",138,"https://images.unsplash.com/photo-1583224964978-2257b960c3d3?auto=format&fit=crop&w=900&q=80",false);
      if (users.count() == 0) {
        users.save(new AppUser(null,"admin","admin123","系统管理员","admin@example.com","13800000000","哈尔滨市南岗区",true));
        users.save(new AppUser(null,"demo","demo123","演示用户","demo@example.com","13900000000","长春市朝阳区",false));
      }
    };
  }
  private static void add(ProductRepository r,String n,String c,String d,String price,int stock,String image,boolean hot){
    if(!r.existsByName(n)) r.save(new Product(null,n,c,d,new BigDecimal(price),stock,image,hot,true));
  }
}
