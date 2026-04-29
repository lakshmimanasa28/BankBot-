
---

# DESIGN_DOCUMENT.md

```md
# BankBot Automation Framework – Design Document

## 1. Objective

To design a scalable Selenium-Java automation framework for testing an online banking portal using modern automation practices.

---

## 2. Architecture Pattern

Page Object Model (POM)

Benefits:

- Better maintainability
- Code reusability
- Easy locator management
- Cleaner test scripts

---

## 3. Framework Components

## Base Layer

### BaseTest.java

Responsibilities:

- Browser initialization
- Driver setup using WebDriverManager
- Launch AUT
- Close browser

### BasePage.java

Reusable common methods:

- click()
- type()
- getText()
- waitForVisibility()
- waitForClickable()
- selectByVisibleText()

---

## Utility Layer

### ConfigReader.java

Reads:

- browser
- baseUrl
- timeout
- username
- password

### ExcelUtil.java

Reads login test data from Excel file.

### ScreenshotUtil.java

Captures screenshot on failure.

### ExtentManager.java

Creates HTML execution report.

### RetryAnalyzer.java

Retries failed flaky tests automatically.

---

## Listener Layer

### TestListener.java

Implements:

- onTestStart()
- onTestSuccess()
- onTestFailure()
- report flush

### RetryListener.java

Attaches retry analyzer dynamically.

---

## Page Layer

Each web page has separate class.

### LoginPage

- Login actions
- Success validation

### NewCustomerPage

- Create customer
- Validation checks

### EditCustomerPage

- Update customer address

### NewAccountPage

- Create account

### EditAccountPage

- Modify account type

### FundTransferPage

- Transfer money

### BalanceEnquiryPage

- Verify balance

---

## Test Layer

### LoginTest

Validates authentication module.

### CustomerTest

Validates customer management module.

### AccountTest

Validates account management.

### FundTransferTest

Validates transactions.

### ValidationTest

Validates field level errors.

---

## 4. Wait Strategy

Only Explicit Wait used.

No Thread.sleep() used.

---

## 5. Reporting Strategy

Extent Report includes:

- Test Name
- Pass/Fail Status
- Failure Screenshot

---

## 6. Failure Handling

RetryAnalyzer retries temporary failures caused by:

- Slow page loads
- Demo site instability

---

## 7. Scalability

Framework can be extended for:

- API Testing
- Cross Browser Grid
- CI/CD Jenkins
- Database Validation

---

## 8. Conclusion

BankBot framework is modular, reusable, scalable, and follows industry best automation standards using Selenium Java and TestNG.