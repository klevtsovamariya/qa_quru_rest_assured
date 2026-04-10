# Проект по автоматизации тестирования API (REST Assured)

Учебный репозиторий: проверки **REST API** хоста Selenoid (`WebDriver /wd/hub/status` и связанные сценарии) с помощью **REST Assured**, **JUnit 5** и **JSON Schema**.

## Технологии и инструменты

IntelliJ IDEA · Java · GitHub · JUnit 5 · Gradle · REST Assured · JSON Schema Validator · Selenoid (тестируемый API)

---

## Примеры автоматизированных тест-кейсов

- Проверка ответа `GET /wd/hub/status`: статус **200**, тело соответствует JSON Schema, в `value.message` есть подстрока с версией Selenoid
- Проверка, что `value.ready` равно **true** при успешном ответе
- Проверка, что `value.message` не **null**
- Запрос без авторизации возвращает **401**
- Запрос с неверным логином возвращает **401**
- Запрос на несуществующий путь возвращает **404**

---

## Запуск из терминала

**Локальный запуск** (из корня репозитория):

```bash
# Windows
gradlew.bat clean test

# Linux / macOS
./gradlew clean test
```

**Запуск только класса с тестами:**

```bash
gradlew.bat test --tests "tests.SelenoidTests"
```

**Запуск одного метода:**

```bash
gradlew.bat test --tests "tests.SelenoidTests.textVerificationTest"
```

---

## Структура репозитория

```text
src/test/java/tests/
  BaseTest.java        — инициализация RestAssured.baseURI
  SelenoidTests.java   — API-сценарии

src/test/resources/schemas/
  response_schema.json — JSON Schema
```