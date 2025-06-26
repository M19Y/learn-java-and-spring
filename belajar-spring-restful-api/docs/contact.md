# Contact API Spec

## Create Contact

Endpoint: POST /api/contacts

Request Header:

- X-API-TOKEN: Token (mandatory)

Request Body:
```json
{
  "firstName": "Sis G",
  "lastName": "Girl",
  "email": "sis@gmail.com",
  "phone": "081234567890"
}
```

Response Body (Success): 201
```json
{
  "data": {
    "id": "random-string",
    "firstName": "Sis G",
    "lastName": "Girl",
    "email": "sis@gmail.com",
    "phone": "081234567890"
  }
}
```

Response Body (Failed): 400
```json
{
  "errors": "invalid email format, ..."
}
```

## Update Contact

Endpoint: PUT /api/contacts/{idContact}

Request Header:

- X-API-TOKEN: Token (mandatory)

Request Body:
```json
{
  "firstName": "Sis G",
  "lastName": "Girl",
  "email": "sis@gmail.com",
  "phone": "081234567890"
}
```

Response Body (Success): 200
```json
{
  "data": {
    "id": "random-string",
    "firstName": "Sis G",
    "lastName": "Girl",
    "email": "sis@gmail.com",
    "phone": "081234567890"
  }
}
```

Response Body (Failed): 400
```json
{
  "errors": "invalid email format, ..."
}
```

## Get Contact

Endpoint: GET /api/contacts/{idContact}

Request Header:

- X-API-TOKEN: Token (mandatory)

Response Body (Success): 200
```json
{
  "data": {
    "id": "random-string",
    "firstName": "Sis G",
    "lastName": "Girl",
    "email": "sis@gmail.com",
    "phone": "081234567890"
  }
}
```

Response Body (Failed): 404
```json
{
  "errors": "Contact not found!"
}
```

## Search Contact

Endpoint: GET /api/contacts

Query Param:

- name  : `String`, contact firstname or lastname, using like query (Optional) 
- phone : `String`, contact phone, using like query (Optional) 
- email : `String`, contact email, using like query (Optional) 
- page  : `Integer`, default 0 
- size  : `Integer`, default 10

Request Header:

- X-API-TOKEN: Token (mandatory)

Response Body (Success): 200
```json
{
  "data": [
    {
      "id": "random-string",
      "firstName": "Sis G",
      "lastName": "Girl",
      "email": "sis@gmail.com",
      "phone": "081234567890"
    },
    {
      "id": "random-string",
      "firstName": "Sis Y",
      "lastName": "Girl Y",
      "email": "sisy@gmail.com",
      "phone": "081234567890"
    }
  ],
  "paging": {
    "currentPage": 0,
    "totalPage": 10,
    "size": 10
  }
}
```

Response Body (Failed): 401
```json
{
  "errors": "Unauthorized"
}
```

## Remove Contact

Endpoint: DELETE /api/contacts/{idContact}

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
  "errors": "Contact not found!"
}
```