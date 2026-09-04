package cn.edu.demo.shop;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal; import java.time.LocalDateTime; import java.util.*;

@Controller
public class ShopController {
  private final ProductRepository products; private final UserRepository users; private final OrderRepository orders;
  public ShopController(ProductRepository p,UserRepository u,OrderRepository o){products=p;users=u;orders=o;}
  @ModelAttribute void common(Model m,HttpSession s){m.addAttribute("loginUser",s.getAttribute("user"));m.addAttribute("cartCount",cart(s).values().stream().mapToInt(Integer::intValue).sum());}
  @GetMapping("/") String home(@RequestParam(defaultValue="") String q,Model m){m.addAttribute("products",q.isBlank()?products.findAll():products.findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(q,q));m.addAttribute("q",q);return "index";}
  @GetMapping("/product/{id}") String detail(@PathVariable Long id,Model m){m.addAttribute("product",products.findById(id).orElseThrow());return "detail";}
  @PostMapping("/cart/add/{id}") String add(@PathVariable Long id,@RequestParam(defaultValue="1") int qty,HttpSession s){products.findById(id).orElseThrow();cart(s).merge(id,Math.max(1,qty),Integer::sum);return "redirect:/cart";}
  @GetMapping("/cart") String cartPage(HttpSession s,Model m){List<Map<String,Object>> rows=new ArrayList<>();BigDecimal total=BigDecimal.ZERO;for(var e:cart(s).entrySet()){Product p=products.findById(e.getKey()).orElse(null);if(p!=null){BigDecimal sub=p.getPrice().multiply(BigDecimal.valueOf(e.getValue()));rows.add(Map.of("product",p,"qty",e.getValue(),"subtotal",sub));total=total.add(sub);}}m.addAttribute("rows",rows);m.addAttribute("total",total);return "cart";}
  @PostMapping("/cart/update/{id}") String update(@PathVariable Long id,@RequestParam int qty,HttpSession s){if(qty<=0)cart(s).remove(id);else cart(s).put(id,qty);return "redirect:/cart";}
  @PostMapping("/cart/remove/{id}") String remove(@PathVariable Long id,HttpSession s){cart(s).remove(id);return "redirect:/cart";}
  @PostMapping("/checkout") String checkout(@RequestParam String receiver,@RequestParam String phone,@RequestParam String address,@RequestParam String payType,HttpSession s){AppUser u=(AppUser)s.getAttribute("user");if(u==null)return "redirect:/login";Map<Long,Integer> c=cart(s);if(c.isEmpty())return "redirect:/cart";BigDecimal total=BigDecimal.ZERO;List<String> lines=new ArrayList<>();for(var e:c.entrySet()){Product p=products.findById(e.getKey()).orElseThrow();int qty=Math.min(e.getValue(),p.getStock());total=total.add(p.getPrice().multiply(BigDecimal.valueOf(qty)));lines.add(p.getName()+" × "+qty);p.setStock(p.getStock()-qty);products.save(p);}orders.save(new ShopOrder(u.getUsername(),receiver,phone,address,payType,"已付款",total,LocalDateTime.now(),String.join("；",lines)));c.clear();return "redirect:/orders";}
  @GetMapping("/orders") String myOrders(HttpSession s,Model m){AppUser u=(AppUser)s.getAttribute("user");if(u==null)return "redirect:/login";m.addAttribute("orders",orders.findByUsernameOrderByCreatedAtDesc(u.getUsername()));return "orders";}
  @GetMapping("/login") String login(){return "login";}
  @PostMapping("/login") String loginDo(@RequestParam String username,@RequestParam String password,HttpSession s,Model m){var u=users.findByUsername(username);if(u.isPresent()&&u.get().getPassword().equals(password)){s.setAttribute("user",u.get());return "redirect:/";}m.addAttribute("error","用户名或密码错误");return "login";}
  @PostMapping("/register") String register(@ModelAttribute AppUser u,Model m){if(users.findByUsername(u.getUsername()).isPresent()){m.addAttribute("error","用户名已存在");return "login";}u.setAdmin(false);users.save(u);m.addAttribute("message","注册成功，请登录");return "login";}
  @GetMapping("/logout") String logout(HttpSession s){s.invalidate();return "redirect:/";}
  @GetMapping("/admin") String admin(HttpSession s,Model m){AppUser u=(AppUser)s.getAttribute("user");if(u==null||!u.isAdmin())return "redirect:/login";m.addAttribute("products",products.findAll());m.addAttribute("orders",orders.findAll());m.addAttribute("users",users.findAll());return "admin";}
  @PostMapping("/admin/product/save") String saveProduct(@ModelAttribute Product p,HttpSession s){if(!isAdmin(s))return "redirect:/login";products.save(p);return "redirect:/admin";}
  @PostMapping("/admin/product/delete/{id}") String deleteProduct(@PathVariable Long id,HttpSession s){if(!isAdmin(s))return "redirect:/login";products.deleteById(id);return "redirect:/admin";}
  @PostMapping("/admin/order/{id}/status") String status(@PathVariable Long id,@RequestParam String status,HttpSession s){if(!isAdmin(s))return "redirect:/login";ShopOrder o=orders.findById(id).orElseThrow();o.setStatus(status);orders.save(o);return "redirect:/admin";}
  private boolean isAdmin(HttpSession s){return s.getAttribute("user") instanceof AppUser u&&u.isAdmin();}
  @SuppressWarnings("unchecked") private Map<Long,Integer> cart(HttpSession s){Map<Long,Integer> c=(Map<Long,Integer>)s.getAttribute("cart");if(c==null){c=new LinkedHashMap<>();s.setAttribute("cart",c);}return c;}
}
