package com.m19y.learn.controller;

import com.m19y.learn.entity.User;
import com.m19y.learn.model.address.AddressRequest;
import com.m19y.learn.model.address.AddressResponse;
import com.m19y.learn.model.res.web.WebResponse;
import com.m19y.learn.model.res.web.WebResponseImpl;
import com.m19y.learn.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api/contacts/{contactId}/addresses")
public class AddressController {

  private final AddressService addressService;

  @Autowired
  public AddressController(AddressService addressService) {
    this.addressService = addressService;
  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<AddressResponse>> create(User user,
                                                             @RequestBody @Valid AddressRequest request,
                                                             @PathVariable(value = "contactId") String contactId){

    request.setContactId(contactId);
    AddressResponse addressResponse = addressService.create(user, request);

    URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(addressResponse.getId())
            .toUri();

    return ResponseEntity.created(location).body(WebResponseImpl.of(addressResponse));
  }

  @GetMapping(path = "/{addressId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<AddressResponse>> get(User user,
                                                          @PathVariable(value = "contactId") String contactId,
                                                          @PathVariable(value = "addressId") String addressId){
    AddressResponse addressResponse = addressService.get(user, contactId, addressId);
    return ResponseEntity.ok(WebResponseImpl.of(addressResponse));
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<List<AddressResponse>>> list(User user,
                                                          @PathVariable(value = "contactId") String contactId){
    List<AddressResponse> addressResponse = addressService.list(user, contactId);
    return ResponseEntity.ok(WebResponseImpl.of(addressResponse));
  }

  @PutMapping(
          path = "/{addressId}",
          produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<AddressResponse>> update(User user,
                                                             @RequestBody @Valid AddressRequest request,
                                                             @PathVariable(value = "contactId") String contactId,
                                                             @PathVariable(value = "addressId") String addressId){

    request.setContactId(contactId);
    request.setAddressId(addressId);

    AddressResponse addressResponse = addressService.update(user, request);


    return ResponseEntity.ok(WebResponseImpl.of(addressResponse));
  }

  @DeleteMapping(path = "/{addressId}")
  public ResponseEntity<Void> remove(User user,
                                     @PathVariable(value = "contactId") String contactId,
                                     @PathVariable(value = "addressId") String addressId){
    addressService.remove(user, contactId, addressId);
    return ResponseEntity.noContent().build();
  }
}
