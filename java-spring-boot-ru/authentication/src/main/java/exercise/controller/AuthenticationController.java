package exercise.controller;

import exercise.dto.AuthRequest;
import exercise.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/login")
public class AuthenticationController {
	@Autowired
	private JWTUtils jwtUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("")
	public String create(@RequestBody AuthRequest authRequest) {
		var auth = new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
				authRequest.getPassword());

		authenticationManager.authenticate(auth);

		return jwtUtils.generateToken(authRequest.getUsername());
	}
}
