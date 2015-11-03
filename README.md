# find-cep
- Project to find address based on postal code
- And provide a CRUD for address

#Technologies
- Java 8
- Junit
- SpringBoot
- Provide a aspect error handler for exceptions

#EndPoints
- [GET] /cep-api/cep/{cep} - Fill {cep} with your cep without '-';
- [GET] /cep-api/address/{id} - Get user address by address id, replace {id} to user address id;
- [PUT] /cep-api/address/ - Update address if exist, all required fields are not null(street, cep, number, city, state) and cep is valid;
- [POST] /cep-api/address/ - Create new address if all required fields are not null(street, cep, number, city, state) and cep is valid;
- [DELETE] /cep-api/address/{id} - Remove user address by id if exist, replace {id} to user address id.
