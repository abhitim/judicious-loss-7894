
## Payment wallet Application (M-Pay)

## Objective:

We made an java backend application to perform CRUD operations related to the payment wallet application .

## Entity RelationShiop Diagram Of Application:
<img src="M_Pay/src/main/resources/ER Diagram.jpeg" alt="E-R Diagram" />

## Features Or Usecases:

- Customers will be able to park their money in the wallet.
- Customer will be able to pay different bills using this wallet.
- Customers transaction details will be saved.
- They should be able to connect bank account with this payment wallet and add money.
- Customers can able to check balance, deposit money, transaction history etc.


## Tech Stacks:

- Spring Boot
- Java
- Lombok
- MySql
- Jpa
- Swagger-Ui


## Installation & Run

* Before running the API server, you should update the database config inside the [application.properties](M_Pay/src/main/resources/application.properties) file. 
* Update the port number, username and password as per your local database config.

```
    server.port=8089
    spring.datasource.url=jdbc:mysql://localhost:3306/`database name`;
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.username=root
    spring.datasource.password=root

```


## Hibernate properties
  
  spring.jpa.show-sql=true
  spring.jpa.hibernate.ddl-auto=update
  spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect

## Exception properties
  spring.mvc.throw-exception-if-no-handler-found=true
  spring.web.resources.add-mappings=false
  
#Swagger-Ui properties
  spring.mvc.pathmatch.matching-strategy=ANT_PATH_MATCHER

### API - Endpoints

`/banks/{walletId}`
`/bank/{accountNumber}`
`/bank/{accountNumber}`

`/beneficiary/add`
`/beneficiary/remove`
`/beneficiary/get`
`/beneficiary/all`

`/bills/{walletId}`

`/customer/add`
`/customer/validate`

`/signIn`
`/logout/{key}`

`/transaction`
`/getTransactions`
`/transactions/{walletId}/{date}`

`/showbalance`
`/addmoney/{amount}/{Accno}`
`/depositmoney/{amount}/{Accno}`
`/getcustomerbywalletid/{walletId}`


## Roles & Responsibilities :-

- Wallet Module             
- Transaction Module        
- Account Module            
- Bill Payment Module       
- Beneficiary Module        
- Customer Module           


## Learning :-

- During this project we got to learn how to work in groups.
- During this project we increased our communication skills.
- We Learned how to communicate with team members.
- Overall we learn lots of things during this project.
- Our skills of Java And Spring boot became more better and clear.


## Team - Members:

👤 [fw19_0695 - Abhishek Tomar `Team-Leader`](https://github.com/abhitim)

👤 [fp04_048  - Vivek Kumar Mishra `Member`](https://github.com/mishravivek09)

👤 [fw16_693  - Rutuja Atul Kavitake `Member`](https://github.com/rutu175)

👤 [fw18_0171 - Raushan Kumar `Member`](https://github.com/raushan18314)

👤 [fw19_0650 - Rituraj Nagar `Member`](https://github.com/riturajnagar)

