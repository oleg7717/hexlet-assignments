package exercise.mapping;

import exercise.dto.ContactCreateDTO;
import exercise.dto.ContactDTO;
import exercise.model.Contact;

public class MappingUtils {
	public ContactDTO mapToContactDto(Contact post){
		ContactDTO contactDTO = new ContactDTO();
		contactDTO.setId(post.getId());
		contactDTO.setFirstName(post.getFirstName());
		contactDTO.setLastName(post.getLastName());
		contactDTO.setPhone(post.getPhone());
		contactDTO.setCreatedAt(post.getCreatedAt());
		contactDTO.setUpdatedAt(post.getUpdatedAt());

		return contactDTO;
	}

	public Contact mapToContact(ContactCreateDTO dto){
		var contact = new Contact();
		contact.setFirstName(dto.getFirstName());
		contact.setLastName(dto.getLastName());
		contact.setPhone(dto.getPhone());

		return contact;
	}
}
