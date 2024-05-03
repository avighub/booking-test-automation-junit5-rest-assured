### Assignment Demonstration

Scenarios
- verify validation error if title is empty
- verify validation error if author is empty
- verify validation error if duplicate book is added
- verify a new book is created successfully

Framework feature
- Build POJO with Lombok
- SpecFactory for easy control over different specifications
- API service for defining different API endpoitns and methods
- Test Class uses Arrange, Act, Assert format to make test readable and easy to understand
- Each test is isolated by starting with clean state , scalable for parallel run
- Jackson library to serialize deserialize objects
