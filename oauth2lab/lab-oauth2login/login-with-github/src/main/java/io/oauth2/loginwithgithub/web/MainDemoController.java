package io.oauth2.loginwithgithub.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sgven on 2019/5/27.
 */
@Controller
public class MainDemoController {

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    @RequestMapping("/")
    public String index() {
        ClientRegistration githubClientRegistration = clientRegistrationRepository.findByRegistrationId("gitbub");
        return githubClientRegistration.toString();
    }
}
