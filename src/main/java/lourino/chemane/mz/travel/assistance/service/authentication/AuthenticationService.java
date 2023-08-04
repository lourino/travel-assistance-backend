package lourino.chemane.mz.travel.assistance.service.authentication;

import lourino.chemane.mz.travel.assistance.service.authentication.domain.AuthenticationRequest;
import lourino.chemane.mz.travel.assistance.service.authentication.domain.AuthenticationResponse;

/**
 * @author Lourino Chemane
 */
public interface AuthenticationService {

    AuthenticationResponse createAuthenticationToken(AuthenticationRequest request) throws Exception;
}
