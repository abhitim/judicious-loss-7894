## Payment wallet Application (M-Pay)

## Objective:

We made a online payment application where customer can open his account and add money from account to his wallet , can do transfer money..


## Features Or Usecases:

- Customers will be able to park their money in the wallet.
- Customer will be able to pay different bills using this wallet.
- They should be able to connect bank account with this payment wallet and add money.
- Customers transaction details will be saved.
- Customers can able to check balance, deposit money, transaction history etc.


## Tech Stacks:

- Spring Boot
- Java
- Lombok
- MySql
- Jpa
- Swagger-Ui


## Installation and Run 

- The Apllication is not hosted on cloud services, so you cannot make api call directly.
- You can clone this repository and run the application on local machine.
- Before running the application make sure that you updated the database properties.

   ## Database properties
   
   spring.datasource.url=jdbc:mysql://localhost:3306/`databasename`</br>
   spring.datasource.username=`Username`</br>
   spring.datasource.password=`Password`

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

## Entity Relationship Diagram
<img src = "BACKEND/M-Pay/src/main/resources/ER Diagram.jpeg">

## Roles & Responsibilities :-

- Wallet Module [fw19_0695 - Abhishek Tomar `Team-Leader`](https://github.com/abhitim)
- Transaction Module [fw19_0650 - Rituraj Nagar `Member`](https://github.com/riturajnagar)
- Account Module [fw18_0171 - Raushan Kumar `Member`](https://github.com/raushan18314)
- Bill Payment Module [fw16_693 - Rutuja Atul Kavitake `Member`](https://github.com/rutu175)
- Beneficiary Module [fp04_048 - Vivek Kumar Mishra `Member`](https://github.com/mishravivek09) 
- Customer Module [fp04_048 - Vivek Kumar Mishra `Member`](https://github.com/mishravivek09)


## Learning :-

- During this project we got to learn how to work in groups.
- During this project we increased our communication skills.
- We Learned how to communicate with team members.
- Overall we learn lots of things during this project.
- Our skills of Java And Spring boot became more better and clear.


## Team - Members:

👤 [fw19_0695 - Abhishek Tomar `Team-Leader`](https://github.com/abhitim)

👤 [fp04_048 - Vivek Kumar Mishra `Member`](https://github.com/mishravivek09)

👤 [fw16_693 - Rutuja Atul Kavitake `Member`](https://github.com/rutu175)

👤 [fw18_0171 - Raushan Kumar `Member`](https://github.com/raushan18314)

👤 [fw19_0650 - Rituraj Nagar `Member`](https://github.com/riturajnagar)
