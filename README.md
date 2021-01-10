STEPS TO LOAD THE DATA AND LOG IN!!!!

1. CREATE SCHEMA `daus` (mysql) ;
2. Run 'DicesApplication.main()' to create the tables on our mysql database
3. Run 'data.main()' to add the initial name and password
4. Let's log in. Postman -> POST http://localhost:8080/login ( body: {"name":"ivan","password":"password"} )
5. from the response -> header, we get the Autorization Bearer that we will need to use for every other request using the AUTHORIZATION by BEARER TOKEN
