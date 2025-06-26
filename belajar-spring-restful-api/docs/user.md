# User API Spec

## Register User

Endpoint: POST /api/users

Request Body:
```json
{
  "username": "Bro",
  "password": "secret123",
  "name": "Bro G"
}
```

Response Body (Success): 201
```json
{
  "data": "OK"
}
```

Response Body (Failed): 400
```json
{
  "errors": "password must not blank, ..."
}
```

## Login User

Endpoint: POST /api/auth/login

Request Body:
```json
{
  "username": "Bro",
  "password": "secret123"
}
```

Response Body (Success): 200
```json
{
  "data": {
    "token": "simple-token",
    "expiredAt": 1234567890 // millisecond
  }
}
```

Response Body (Failed): 401
```json
{
  "errors": "username and password wrong"
}
```

## Get User

Endpoint: GET /api/users/current

Request Header:

- X-API-TOKEN: Token (mandatory)

Response Body (Success): 200

```json
{
  "data": {
    "username": "Bro",
    "name": "Bro G"
  }
}
```

Response Body (Failed): 401

```json
{
  "errors": "Unauthorized"
}
```

## Update User

Endpoint: PATCH /api/users

Request Header:
- X-API-TOKEN: Token (mandatory)

Request Body:

```json
{
  "name": "Bro W", // if only want to update name
  "password": "secret456" // if only want to update password
}
```

Response Body (Success): 200
```json
{
  "data": "OK"
}
```

Response Body (Failed): 401
```json
{
  "errors": "Unauthorized"
}
```

## Logout User

Endpoint: DELETE /api/auth/logout

Request Header:

- X-API-TOKEN: Token (mandatory)

Response Body (Success): 200
```json
{
  "data": "OK"
}
```

Response Body (Failed): 401
```json
{
  "errors": "Unauthorized"
}
```