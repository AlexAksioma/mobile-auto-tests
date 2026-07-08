# mobile-auto-tests

A cross-platform (Android + iOS) mobile UI test-automation framework built on
**Appium** and **Selenium**, using a Page Object model with platform-aware
XPath locators. Tests run with **TestNG**.

## Tech stack

| Concern            | Choice                                   |
|--------------------|------------------------------------------|
| Language           | Java 21 (Gradle toolchain)               |
| Build              | Gradle (Kotlin DSL, `java-library`)      |
| Mobile driver      | Appium `java-client` 9.3.0               |
| WebDriver          | Selenium 4.27.0                          |
| Test runner        | TestNG 7.10.2                            |
| Logging            | SLF4J 2.0.16 (`slf4j-simple`)            |

## Project layout

```
src/
  main/java/com/mobile/framework/
    core/
      Platform.java       # ANDROID / IOS enum, resolves the current platform
      Locator.java        # Holds an Android + iOS XPath; picks one per platform
      View.java           # A UI element = parent-page locator + own locator
      BasePage.java       # Base class for page objects; builds Views
      DriverHolder.java   # Thread-local AppiumDriver holder
    pages/
      LoginPage.java      # Page objects: one class per screen
      HomePage.java
      MarketPage.java
  test/java/com/mobile/tests/
    LocatorCompositionTest.java  # Unit tests for locator/View composition (no device)
    NewTaskTest.java             # Trivial smoke test
```

## Core concepts

### Platform
`Platform.current()` decides whether the run targets **Android** or **iOS**.
It reads the `platform` JVM system property first, then the `PLATFORM`
environment variable, defaulting to `ANDROID`. Tests can override it directly
with `Platform.setCurrent(Platform.IOS)`.

### Locator
A `Locator` carries **two XPaths** — one for Android, one for iOS — and returns
the correct one for the active platform via `xpath()`. Helpers:
- `Locator.of(androidXPath, iosXPath)` — distinct selectors per platform.
- `Locator.same(xpath)` — identical selector on both (e.g. `//*[@text='OK']`).
- `parent.child(child)` — concatenates XPaths to express nesting.

### View
A `View` is a single UI element, resolved as **page-root locator + element
locator**. It exposes the interactions used by tests:
`tap()`, `text()`, `enterText(...)`, `isDisplayed()`, `exists()`, plus `child(...)`
for nested elements. All actions go through `DriverHolder.driver()`.

### BasePage & Page Objects
Each screen is a class extending `BasePage`. The constructor passes the screen's
Android/iOS **root** XPath; element accessors call the protected `view(...)`
helpers so every element is automatically scoped under that root. Example
pattern (from `LoginPage`):

```java
public class LoginPage extends BasePage {
    public LoginPage() {
        super("//*[@resource-id='login_screen']",   // Android root
              "//*[@name='LoginViewController']");   // iOS root
    }

    public View emailInput() {
        return view("//*[@resource-id='email_input']",
                    "//*[@name='email_textfield']");
    }
}
```

`HomePage` also demonstrates nested elements via `profileSection().child(...)`.

### DriverHolder
Holds the `AppiumDriver` in a `ThreadLocal` so tests can run in parallel, one
driver per thread. `set(driver)` before a test, `clear()` to quit and clean up.
Accessing the driver before `set()` throws `IllegalStateException`.

## Running the tests

```bash
# All tests (Android is the default platform)
./gradlew test

# Target iOS locators
./gradlew test -Dplatform=IOS
```

> Note: `LocatorCompositionTest` is a pure unit test of locator/View
> composition and needs **no** running Appium server or device. Tests that
> drive a real app additionally require a configured `AppiumDriver`
> (via `DriverHolder.set(...)`) and a running Appium server.

## Adding a new screen

1. Create a class in `src/main/java/com/mobile/framework/pages/` extending `BasePage`.
2. Pass the screen's Android and iOS root XPaths to `super(...)`.
3. Add one accessor method per element returning `view(androidXPath, iosXPath)`.
4. Use `.child(Locator.of(...))` for elements nested inside another element.
