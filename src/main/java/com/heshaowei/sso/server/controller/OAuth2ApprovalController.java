package com.heshaowei.sso.server.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@SessionAttributes("authorizationRequest")
public class OAuth2ApprovalController {

    @RequestMapping("/oauth/confirm_access")
    public String getAccessConfirmation(Map<String, Object> model, HttpServletRequest request)
            throws Exception {
        AuthorizationRequest authorizationRequest = (AuthorizationRequest) model.get("authorizationRequest");
        Set<String> scopes = authorizationRequest.getScope();

        List<String> scopeList = new ArrayList<String>();
        for (String scope : scopes) {
            scopeList.add(scope);
        }
        model.put("scopeList", scopeList);
        return "oauth/oauth_approval";
    }

}