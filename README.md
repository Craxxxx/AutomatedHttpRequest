AUTOMATED HTTP REQUEST MODULE WITH POSITIVE, NEGATIVE AND EDGE TESTING SCENARIOS

**Prerequisites**

Before you begin, ensure you have the following installed on your machine:

1. Java 21 SDK (or compatible JDK)
2. Git for cloning the repository 
3. Gradle Wrapper (included in this project) 
4. Allure Command-Line for report generation and viewing Allure Report
5. Internet access (for initial dependency download)

  

**EXECUTION**
1. Sebelum Menjalankan buka terlebih dahulu website https://gorest.co.in/
2. Lakukan login pada website tersebut untuk mengambil bearer token
3. Masukan bearer token pada BaseTest.java ->  String token = "";
4. jalankan test dengan ./gradlew clean test
5. generate allure report dengan ./gradlew allureReport
6. Buka allure report dengan allure open app/build/reports/allure-report/allureReport
