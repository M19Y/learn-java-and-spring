package com.m19y.learn.mapper;

import com.m19y.learn.entity.Contact;
import com.m19y.learn.model.contact.ContactResponse;

public class ContactMapper {

  public static ContactResponse toDto(Contact contact){
    return new ContactResponse(
      contact.getId(),
      contact.getFirstName(),
      contact.getLastName(),
      contact.getEmail(),
      contact.getPhone()
    );
  }
}
