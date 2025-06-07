package exercise.controller;

import exercise.dto.ContactCreateDTO;
import exercise.dto.ContactDTO;
import exercise.mapping.MappingUtils;
import exercise.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contacts")
public class ContactsController {
	private final MappingUtils mappingUtils = new MappingUtils();
	@Autowired
	private ContactRepository contactRepository;

	// BEGIN
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public ContactDTO createContact(@RequestBody ContactCreateDTO data) {
		var contact = mappingUtils.mapToContact(data);
		contactRepository.save(contact);

		return mappingUtils.mapToContactDto(contact);
	}
	// END
}
