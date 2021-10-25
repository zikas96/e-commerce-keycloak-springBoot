package org.zikas.ecommerce.app.web;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zikas.ecommerce.app.entities.Supplier;
import org.zikas.ecommerce.app.repositories.ProductRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ProductController{

    @Autowired
    private KeycloakRestTemplate keycloakRestTemplate;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/products")
    public String products(Model model){
        model.addAttribute("products",productRepository.findAll());
        return "products";
    }
    @GetMapping("/suppliers")
    public String suppliers(Model model){
        /*RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","Bearer " + map(request));
        HttpEntity httpEntity = new HttpEntity(httpHeaders);*/
        PagedModel<Supplier> pageSuppliers = keycloakRestTemplate.getForObject("http://localhost:8081/suppliers", PagedModel.class);
        model.addAttribute("suppliers", pageSuppliers);
        return "suppliers";
    }

    @GetMapping("/jwt")
    @ResponseBody
    public Map<String, String> map(HttpServletRequest request){
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) request.getUserPrincipal();
        KeycloakPrincipal principal = (KeycloakPrincipal) token.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        Map<String, String> map = new HashMap<>();
        map.put("access_token", context.getIdTokenString());
        return map;
    }

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e, Model model){
        System.out.println(e.getMessage());
        model.addAttribute("errorMessage", "Problème d'autorisation");
        return "errors";
    }

}
