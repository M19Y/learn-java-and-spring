# Address API Spec

# Create Address

Endpoint: POST /api/contacts/{idContacts}/addresses

Request Header:

- X-API-TOKEN: Token (mandatory)

Request Body:
```json
{
  "street": "bro-street",
  "city": "Jakarta",
  "province": "Jakarta",
  "country": "Indonesia",
  "postalCode": "71137"
}
```

Response Body (Success): 201
```json
{
  "data": {
    "id": "random-string",
    "street": "bro-street",
    "city": "Jakarta",
    "province": "Jakarta",
    "country": "Indonesia",
    "postalCode": "71137"
  }
}
```

Response Body (Failed): 404
```json
{
  "errors": "Contact is not found"
}
```

# Update Address

Endpoint: PUT /api/contacts/{idContacts}/addresses/{idAddress}

Request Header:

- X-API-TOKEN: Token (mandatory)

Request Body:
```json
{
  "street": "bro-street",
  "city": "Jakarta",
  "province": "Jakarta",
  "country": "Indonesia",
  "postalCode": "71137"
}
```

Response Body (Success): 200
```json
{
  "data": {
    "id": "random-string",
    "street": "bro-street",
    "city": "Jakarta",
    "province": "Jakarta",
    "country": "Indonesia",
    "postalCode": "71137"
  }
}
```

Response Body (Failed): 404
```json
{
  "errors": "Contact is not found"
}
```

# Remove Address

Endpoint: DELETE /api/contacts/{idContacts}/addresses/{idAddress}

Request Header:

- X-API-TOKEN: Token (mandatory)


Response Body (Success): 200
```json
{
  "data": "OK"
}
```

Response Body (Failed): 404
```json
{
  "errors": "Contact is not found"
}
```

# Get Address

Endpoint: GET /api/contacts/{idContacts}/addresses/{idAddress}

Request Header:

- X-API-TOKEN: Token (mandatory)

Response Body (Success): 200
```json
{
  "data": {
    "id": "random-string",
    "street": "bro-street",
    "city": "Jakarta",
    "province": "Jakarta",
    "country": "Indonesia",
    "postalCode": "71137"
  }
}
```

Response Body (Failed): 404
```json
{
  "errors": "Address is not found"
}
```

# List Address

Endpoint: GET /api/contacts/{idContacts}/addresses

Request Header:

- X-API-TOKEN: Token (mandatory)

Response Body (Success): 200
```json
{
  "data": [{
    "id": "random-string",
    "street": "bro-street",
    "city": "Jakarta",
    "province": "Jakarta",
    "country": "Indonesia",
    "postalCode": "71137"
  }]
}
```

Response Body (Failed): 404
```json
{
  "errors": "Contact is not found"
}
```