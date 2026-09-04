package cn.edu.demo.shop;
import jakarta.persistence.*;
import java.math.BigDecimal; import java.time.LocalDateTime;
@Entity @Table(name="shop_orders")
public class ShopOrder {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; private String username; private String receiver; private String phone; private String address; private String payType; private String status; private BigDecimal total; private LocalDateTime createdAt; @Column(length=4000) private String items;
  public ShopOrder(){} public ShopOrder(String username,String receiver,String phone,String address,String payType,String status,BigDecimal total,LocalDateTime createdAt,String items){this.username=username;this.receiver=receiver;this.phone=phone;this.address=address;this.payType=payType;this.status=status;this.total=total;this.createdAt=createdAt;this.items=items;}
  public Long getId(){return id;} public String getUsername(){return username;} public String getReceiver(){return receiver;} public String getPhone(){return phone;} public String getAddress(){return address;} public String getPayType(){return payType;} public String getStatus(){return status;} public BigDecimal getTotal(){return total;} public LocalDateTime getCreatedAt(){return createdAt;} public String getItems(){return items;} public void setStatus(String v){status=v;}
}
