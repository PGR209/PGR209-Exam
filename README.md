--- How to run ---
The program can be opened in an IDE like IntelliJ or Visual Studio. To run the program, run Main from src/main/java/com/PGR209/Exam/ExamApplication.java. The spring boot program will run on http://127.0.0.1:8080, and can be tested through postman. H2 console can be accessed on http://127.0.0.1:8080/h2-console with default username: sa , and no password.


--- Important Postman Tips ---
A postman collection has been created and added to resources/data/postman. This can be imported to postman through Menu -> File -> Import -> ...select files... -> Browse to and add the file. Comments has been added to the request body, to explain a little about the custom exceptions and checks done for each request. It is recommended running each request in order, as some methods require previous data. Mostly the Customer and Address models has been used only for showing concept, where all models can be used in the same way, with very few exceptions informed in the known bugs section below.

--- Known Bugs ---
- Order had to be renamed to SalesOrder due to reservation conflicts with JPA.
- Cascading delete will delete both entities in the bidirectional relation. One solution to solve this could have been to manually check for references when deleting, however this would break one of the principles. Where as an example, subassemblies has a list of parts, but part should not know about subassemblies, however for part to check if it is listed, it needs a referance.
- The PUT method doesn't always update both lists in bidirectional relations, however POST always seems to work.

--- Links Used ---
- Exception handling tips: https://www.bezkoder.com/spring-boot-controlleradvice-exceptionhandler/
- Pagination documentation: https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/PagingAndSortingRepository.html

--- Other ---
- An early flowchart/UML chart is added to resources/data.
- As also informed in postman collection, default size for paginated pages is 4, but can be changed in configuration/ApplicationConfiguration.java.
- Originally GET method for GetAll that return full list from database, however this was removed due to assignment stating "You should implement pagination for all endpoints that return lists", that we interpreted to mean that only paginated lists should be used.
- All custom exceptions are both returned through response body and logged as a warning to console. They were split so, if needed in future to show something different through response than locally, that might be logged to a file. 