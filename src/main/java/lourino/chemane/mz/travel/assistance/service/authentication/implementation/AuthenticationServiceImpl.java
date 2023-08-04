package lourino.chemane.mz.travel.assistance.service.authentication.implementation;

import lourino.chemane.mz.travel.assistance.security.service.impl.UserServiceImpl;
import lourino.chemane.mz.travel.assistance.security.util.JwtUtil;
import lourino.chemane.mz.travel.assistance.service.authentication.AuthenticationService;
import lourino.chemane.mz.travel.assistance.service.authentication.domain.AuthenticationRequest;
import lourino.chemane.mz.travel.assistance.service.authentication.domain.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author Lourino Chemane
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserServiceImpl userService;

    @Override
    public AuthenticationResponse createAuthenticationToken(AuthenticationRequest request) throws Exception {

        try {
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (BadCredentialsException e){
            throw new Exception("Username or Password invalid", e);
        }

        UserDetails userDetails = this.userService.loadUserByUsername(request.getUsername());

        return new AuthenticationResponse(JwtUtil.genarateToken(userDetails));
    }
}
