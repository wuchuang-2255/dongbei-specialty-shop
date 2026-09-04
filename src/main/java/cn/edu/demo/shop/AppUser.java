package cn.edu.demo.shop;
import jakarta.persistence.*;
@Entity
public class AppUser {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @Column(unique=true,nullable=false) private String username; private String password; private String name; private String email; private String phone; private String address; private boolean admin;
  public AppUser(){} public AppUser(Long id,String username,String password,String name,String email,String phone,String address,boolean admin){this.id=id;this.username=username;this.password=password;this.name=name;this.email=email;this.phone=phone;this.address=address;this.admin=admin;}
  public Long getId(){return id;} public void setId(Long v){id=v;} public String getUsername(){return username;} public void setUsername(String v){username=v;} public String getPassword(){return password;} public void setPassword(String v){password=v;} public String getName(){return name;} public void setName(String v){name=v;} public String getEmail(){return email;} public void setEmail(String v){email=v;} public String getPhone(){return phone;} public void setPhone(String v){phone=v;} public String getAddress(){return address;} public void setAddress(String v){address=v;} public boolean isAdmin(){return admin;} public void setAdmin(boolean v){admin=v;}
}
