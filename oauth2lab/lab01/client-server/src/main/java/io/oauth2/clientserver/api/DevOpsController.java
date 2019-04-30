package io.oauth2.clientserver.api;

import io.oauth2.clientserver.source.UserInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api")
public class DevOpsController {

    /**
     * 资源API
     */
    @RequestMapping("/userlist")
    public ResponseEntity<List<UserInfo>> getAllUsers() {
        return ResponseEntity.ok(getUserList());
    }

    private List<UserInfo> getUserList() {
        List<UserInfo> users = new ArrayList<>();
        users.add(new UserInfo("xiejun", "xiejunb@yonyou.com"));
        users.add(new UserInfo("cdr", "cdr@yonyou.com"));
        users.add(new UserInfo("xx", "xx@yonyou.com"));
        return users;
    }
}
