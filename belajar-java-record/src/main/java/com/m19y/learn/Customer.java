package com.m19y.learn;

/*
* Record class tidak bisa mengextends maupun di extends karena record adalah final
* Cocok untuk immutable data.
* Namun record bisa mengimplementasi methods dari sebuah interface
* */

public record Customer(String id, String name, String email, String phone) implements SayHello{

  // canonical constructor
  public Customer(String id, String name, String email, String phone) {
    System.out.println("Create customer");
    this.id = id;
    this.name = name;
    this.email = email != null ? email.toLowerCase() : null;
    this.phone = phone != null ? phone.toLowerCase() : null;
  }

  // constructors
  public Customer(String id, String name, String email){
    this(id, name, email, null);
  }

  public Customer(String id, String name){
    this(id, name, null, null);
  }

  // methods
  public String sayHello(String name){
    return String.format("Hello %s, my name is %s", name, this.name);
  }
}
