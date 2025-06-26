package com.m19y.learn.service;

import com.m19y.learn.entity.Contact;
import com.m19y.learn.entity.User;
import com.m19y.learn.model.contact.ContactResponse;
import com.m19y.learn.model.contact.CreateContactRequest;
import com.m19y.learn.model.contact.SearchContactRequest;
import com.m19y.learn.model.contact.UpdateContactRequest;
import org.springframework.data.domain.Page;

public interface ContactService {

  ContactResponse create(User user, CreateContactRequest request);
  ContactResponse get(User user, String id);
  ContactResponse update(User user, UpdateContactRequest request);
  void delete(User user, String id);
  Page<ContactResponse> search(User user, SearchContactRequest request);
  Contact findContactForUserOrThrow(User user, String id);
}
