package cn.com.myproject.security;

import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;

public class MyResourceOwnerPasswordResourceDetails extends ResourceOwnerPasswordResourceDetails {
    public MyResourceOwnerPasswordResourceDetails() {
        super();
        this.setClientId("client1");
        this.setClientSecret("client2");
    }
}
