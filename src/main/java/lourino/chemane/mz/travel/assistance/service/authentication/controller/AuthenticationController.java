package lourino.chemane.mz.travel.assistance.service.authentication.controller;

import lourino.chemane.mz.travel.assistance.service.authentication.AuthenticationService;
import lourino.chemane.mz.travel.assistance.service.authentication.domain.AuthenticationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lourino Chemane
 */
@RestController
@RequestMapping(path = "authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationService service;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<Object> createAuthenticationToken(@RequestBody AuthenticationRequest request) throws Exception {
        return ResponseEntity.ok(this.service.createAuthenticationToken(request));
    }
}
