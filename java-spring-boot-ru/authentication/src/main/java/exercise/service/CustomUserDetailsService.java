package exercise.service;

// BEGIN

import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsManager {
	@Autowired
	private UserRepository userRepository;

	public UserDetails loadUserByUsername(String email) {
		return userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User with email" + email + " not found"));
	}

	@Override
	public void createUser(UserDetails user) {
		throw new UnsupportedOperationException("Operation not support");
	}

	@Override
	public void updateUser(UserDetails user) {
		throw new UnsupportedOperationException("Operation not support");
	}

	@Override
	public void deleteUser(String username) {
		throw new UnsupportedOperationException("Operation not support");
	}

	@Override
	public void changePassword(String oldPassword, String newPassword) {
		throw new UnsupportedOperationException("Operation not support");
	}

	@Override
	public boolean userExists(String username) {
		throw new UnsupportedOperationException("Operation not support");
	}
}
// END
