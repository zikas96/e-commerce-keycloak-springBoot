package org.zikas.ecommerce.app.web;

import org.keycloak.adapters.AdapterDeploymentContext;
import org.keycloak.adapters.KeycloakDeployment;
import org.keycloak.adapters.spi.HttpFacade;
import org.keycloak.adapters.springsecurity.facade.SimpleHttpFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SecurityController {

    @Autowired
    AdapterDeploymentContext adapter;

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "redirect:/";
    }

    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }

    @GetMapping("/changePassword")
    public String changePassword(RedirectAttributes attributes, HttpServletRequest request
                                , HttpServletResponse response) {
        HttpFacade facade = new SimpleHttpFacade(request, response);
        KeycloakDeployment deployment = adapter.resolveDeployment(facade);
        attributes.addAttribute("referrer", deployment.getResourceName());
        attributes.addAttribute("referrer_uri", request.getHeader("referrer"));
        return "redirect:" + deployment.getAccountUrl();
    }
}
