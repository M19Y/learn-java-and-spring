best practice naming test method

1. `<methodUnderTest>_<scenarioBeingTested>_<expectedOutcome>()`
2. `should<ExpectedOutcome>When<Scenario>()`

example failed:
```java
// 1
@Test
void login_withWrongPassword_shouldThrowUnauthorized() {}

// 2 (recommended)
@Test
void shouldThrowUnauthorized_whenPasswordIsIncorrect() {}

// 3
@Test
void login_withUnregisteredUser_shouldThrowUnauthorized() {}

// 4
@Test
void shouldThrowUnauthorized_whenUserIsNotRegistered() {}
```

example success:
```java
// 1
@Test
void shouldReturnToken_whenLoginIsSuccessful() { }

// 2
@Test
void login_withValidCredentials_shouldReturnToken() { }

```