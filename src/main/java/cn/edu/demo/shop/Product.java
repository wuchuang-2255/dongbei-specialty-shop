package cn.edu.demo.shop;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class Product {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  private String name; private String category; @Column(length=1000) private String description;
  private BigDecimal price; private Integer stock; @Column(length=1000) private String image;
  private boolean hot; private boolean latest;
  public Product() {}
  public Product(Long id,String name,String category,String description,BigDecimal price,Integer stock,String image,boolean hot,boolean latest){this.id=id;this.name=name;this.category=category;this.description=description;this.price=price;this.stock=stock;this.image=image;this.hot=hot;this.latest=latest;}
  public Long getId(){return id;} public void setId(Long v){id=v;} public String getName(){return name;} public void setName(String v){name=v;} public String getCategory(){return category;} public void setCategory(String v){category=v;} public String getDescription(){return description;} public void setDescription(String v){description=v;} public BigDecimal getPrice(){return price;} public void setPrice(BigDecimal v){price=v;} public Integer getStock(){return stock;} public void setStock(Integer v){stock=v;} public String getImage(){return image;} public void setImage(String v){image=v;} public boolean isHot(){return hot;} public void setHot(boolean v){hot=v;} public boolean isLatest(){return latest;} public void setLatest(boolean v){latest=v;}
}
