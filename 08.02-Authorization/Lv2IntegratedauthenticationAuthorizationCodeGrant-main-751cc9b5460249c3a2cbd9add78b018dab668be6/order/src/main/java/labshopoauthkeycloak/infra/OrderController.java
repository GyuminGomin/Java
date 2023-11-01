package labshopoauthkeycloak.infra;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @GetMapping("/placeOrder")
    @RolesAllowed({"ROLE_CUSTOMER"})
    public String placeAnOrder() {
        String preferred_username = getPreferred_username();        
        return "<center> Hi~ " + preferred_username + 
                        ". Welcome to 12stMall.<br/><br/> We've Good Products, You can place an Order.";
    }

    @GetMapping("/manageOrder")
    @RolesAllowed({"ROLE_ADMIN"})
    public String orderManage() {
        String preferred_username = getPreferred_username();        
        return "<center> Hi~ " + preferred_username + 
                        ". Welcome to 12stMall.<br/><br/> You can manage Order Admin Jobs.";
    }

    @GetMapping("/getUser")
    @PermitAll
    public String getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Jwt) {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            return String.format("You are [%s] with e-mail address [%s].%n",
                jwt.getSubject(), jwt.getClaimAsString("email"));
        }
        else {
            return "Something went wrong; authentication is not provided by IAP/JWT.\n";
        }
    }

    private String getPreferred_username() {
        Jwt jwt = (Jwt)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return jwt.getClaimAsString("preferred_username");
         
    }
}
