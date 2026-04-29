# BankBot – Selenium Java Automation Framework

## Project Overview

BankBot is a Selenium WebDriver automation framework built using Java, Maven, and TestNG for testing the Guru99 Demo Banking Application.

**Application Under Test:**  
https://demo.guru99.com/V4/index.php

The framework is designed using the **Page Object Model (POM)** pattern to ensure clean, reusable, and maintainable automation code.

---

# Tech Stack

- Java
- Selenium WebDriver
- TestNG
- Maven
- WebDriverManager
- Apache POI
- Extent Reports

---

# Project Structure

```text
BankBot/
│── pom.xml
│── README.md
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/bankbot/
│   │           ├── base/
│   │           │   ├── BasePage.java
│   │           │   └── BaseTest.java
│   │           │
│   │           ├── pages/
│   │           │   ├── LoginPage.java
│   │           │   ├── HomePage.java
│   │           │   ├── NewCustomerPage.java
│   │           │   ├── EditCustomerPage.java
│   │           │   ├── NewAccountPage.java
│   │           │   ├── EditAccountPage.java
│   │           │   ├── FundTransferPage.java
│   │           │   └── BalanceEnquiryPage.java
│   │           │
│   │           └── utils/
│   │               ├── ConfigReader.java
│   │               ├── ExcelUtil.java
│   │               ├── ScreenshotUtil.java
│   │               ├── ExtentManager.java
│   │               └── RetryAnalyzer.java
│
│   └── test/
│       ├── java/
│       │   └── com/bankbot/
│       │       ├── tests/
│       │       │   ├── LoginTest.java
│       │       │   ├── CustomerTest.java
│       │       │   ├── AccountTest.java
│       │       │   ├── FundTransferTest.java
│       │       │   └── ValidationTest.java
│       │       │
│       │       └── listeners/
│       │           ├── TestListener.java
│       │           └── RetryListener.java
│       │
│       └── resources/
│           ├── config.properties
│           ├── testng.xml
│           └── testdata/
│               └── LoginData.xlsx
│
├── reports/
│   └── ExtentReport.html
│
└── screenshots/
