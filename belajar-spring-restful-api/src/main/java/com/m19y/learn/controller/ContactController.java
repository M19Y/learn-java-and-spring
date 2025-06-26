package com.m19y.learn.controller;

import com.m19y.learn.entity.User;
import com.m19y.learn.model.contact.ContactResponse;
import com.m19y.learn.model.contact.CreateContactRequest;
import com.m19y.learn.model.contact.SearchContactRequest;
import com.m19y.learn.model.contact.UpdateContactRequest;
import com.m19y.learn.model.res.web.BaseWebResponse;
import com.m19y.learn.model.res.web.PagedResponse;
import com.m19y.learn.model.res.web.WebResponse;
import com.m19y.learn.model.res.web.WebResponseImpl;
import com.m19y.learn.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api/contacts")
public class ContactController {

  private final ContactService contactService;

  @Autowired
  public ContactController(ContactService contactService) {
    this.contactService = contactService;
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<ContactResponse>> create(User user, @RequestBody CreateContactRequest request){
    ContactResponse contactResponse = contactService.create(user, request);

    URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(contactResponse.id())
            .toUri();

    return ResponseEntity.created(location).body(WebResponseImpl.of(contactResponse));
  }

  @GetMapping(path = "/{contactId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<ContactResponse>> get(User user, @PathVariable(value = "contactId") String id){
    ContactResponse contactResponse = contactService.get(user, id);
    return ResponseEntity.ok().body(WebResponseImpl.of(contactResponse));
  }

  @PutMapping(path = "/{contactId}",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<ContactResponse>> update(User user,
                                                             @PathVariable(value = "contactId") String id,
                                                             @RequestBody UpdateContactRequest request){
    request.setId(id);
    ContactResponse contactResponse = contactService.update(user, request);
    return ResponseEntity.ok().body(WebResponseImpl.of(contactResponse));
  }

  @DeleteMapping(path = "/{contactId}")
  public ResponseEntity<Void> remove(User user, @PathVariable(value = "contactId") String id){
    contactService.delete(user, id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<List<ContactResponse>>> search(
          User user,
          @RequestParam(value = "name", required = false) String name,
          @RequestParam(value = "email", required = false) String email,
          @RequestParam(value = "phone", required = false) String phone,
          @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
          @RequestParam(value = "size", required = false, defaultValue = "10") Integer size){

    SearchContactRequest request = new SearchContactRequest(name, email, phone, page, size);
    Page<ContactResponse> search = contactService.search(user, request);

    return ResponseEntity.ok().body(
      PagedResponse.of(
      search.getContent(),
      search.getNumber(),
      search.getSize(),
      search.getTotalPages())
    );
  }
}
