package exercise.utils;

import exercise.model.User;
import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserUtils {
	@Autowired
	private UserRepository userRepository;

	// BEGIN
	public User getCurrentUser() {
		var auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null || !auth.isAuthenticated()) {
			return null;
		}

		return userRepository.findByEmail(auth.getName()).orElse(null);
	}
	// END

	public User getTestUser() {
		return userRepository.findByEmail("hexlet@example.com")
				.orElseThrow(() -> new RuntimeException("User not found"));
	}
}
