# Prioritized Test Suite Implementation Issues

Based on the comprehensive test suite analysis, here are the GitHub issues that should be created to systematically implement the testing recommendations. Each issue includes title, description, acceptance criteria, and implementation guidance.

---

## Priority 1 (P0) - CRITICAL - Immediate Implementation Required

### Issue #1: Implement Basic Application Context Test

**Title:** `1 - Add basic Spring Boot application context loading test`

**Description:**
The application currently has zero effective test coverage. This issue addresses the most fundamental test - ensuring the Spring Boot application context loads successfully.

**Acceptance Criteria:**
- [ ] Add a working test method to `DogsgraphqlApplicationTests.java`
- [ ] Test verifies Spring Boot application context loads without errors
- [ ] Test runs successfully in CI pipeline
- [ ] Maven test command should show "Tests run: 1" instead of "Tests run: 0"

**Implementation Details:**
- Modify the existing empty test class
- Add `@Test void contextLoads()` method
- Verify all beans are properly configured and initialized

**Estimated Effort:** 2-4 hours

---

### Issue #2: Implement Repository Layer Tests

**Title:** `2 - Add comprehensive DogRepository unit tests`

**Description:**
The repository layer has no test coverage, leaving CRUD operations untested. This creates high risk for data corruption and persistence failures.

**Acceptance Criteria:**
- [ ] Create `DogRepositoryTest` class with `@DataJpaTest` annotation
- [ ] Test `findAll()` method returns expected data
- [ ] Test `findById()` method with existing and non-existing IDs
- [ ] Test `save()` method for creating and updating dogs
- [ ] Test `delete()` method removes dogs correctly
- [ ] Test repository works correctly with H2 in-memory database
- [ ] All tests use test data separate from `data.sql`

**Implementation Details:**
- Use `@DataJpaTest` for focused repository testing
- Use `TestEntityManager` for test data setup
- Test both positive and negative scenarios
- Verify database state changes

**Estimated Effort:** 8-12 hours

---

## Priority 2 (P1) - HIGH - Important for Business Logic

### Issue #3: Implement Query Resolver Unit Tests

**Title:** `3 - Add unit tests for GraphQL Query resolver`

**Description:**
The Query resolver contains core business logic for retrieving dogs but has no test coverage. This leaves critical read operations untested.

**Acceptance Criteria:**
- [ ] Create `QueryTest` class with mocked dependencies
- [ ] Test `findAllDogs()` method returns correct data
- [ ] Test `findDogById()` with valid ID returns correct dog
- [ ] Test `findDogById()` with invalid ID throws `DogNotFoundException`
- [ ] Mock `DogRepository` to control test data
- [ ] Achieve 100% code coverage for Query class

**Implementation Details:**
- Use `@ExtendWith(MockitoExtension.class)`
- Mock `DogRepository` using `@Mock`
- Use `@InjectMocks` for Query instance
- Test both success and failure scenarios

**Estimated Effort:** 6-8 hours

---

### Issue #4: Implement Mutation Resolver Unit Tests

**Title:** `4 - Add unit tests for GraphQL Mutation resolver`

**Description:**
The Mutation resolver handles critical write operations (delete breed, update name) but lacks test coverage, creating high risk for data corruption.

**Acceptance Criteria:**
- [ ] Create `MutationTest` class with mocked dependencies
- [ ] Test `deleteDogBreed()` successfully deletes all dogs of specified breed
- [ ] Test `deleteDogBreed()` throws `BreedNotFoundException` when breed doesn't exist
- [ ] Test `updateDogName()` successfully updates dog name
- [ ] Test `updateDogName()` throws `DogNotFoundException` when dog doesn't exist
- [ ] Mock `DogRepository` for controlled testing
- [ ] Verify repository method calls and arguments

**Implementation Details:**
- Mock repository `findAll()`, `delete()`, `findById()`, and `save()` methods
- Test the complex breed deletion logic (iterates through all dogs)
- Verify exception scenarios with proper exception types
- Use argument captors to verify saved data

**Estimated Effort:** 10-14 hours

---

## Priority 3 (P1) - HIGH - Exception Handling

### Issue #5: Implement Custom Exception Tests

**Title:** `5 - Add unit tests for custom GraphQL exceptions`

**Description:**
Custom exceptions (`DogNotFoundException`, `BreedNotFoundException`) lack test coverage, risking improper error handling in production.

**Acceptance Criteria:**
- [ ] Create `DogNotFoundExceptionTest` class
- [ ] Test exception message and extension data for `DogNotFoundException`
- [ ] Test GraphQL error type and location handling
- [ ] Create `BreedNotFoundExceptionTest` class  
- [ ] Test exception message and extension data for `BreedNotFoundException`
- [ ] Verify exceptions implement `GraphQLError` interface correctly

**Implementation Details:**
- Test exception constructors with various parameters
- Verify `getExtensions()` returns correct data
- Test `getErrorType()` returns `DataFetchingException`
- Test `getLocations()` returns null as expected

**Estimated Effort:** 4-6 hours

---

## Priority 4 (P1) - HIGH - Integration Testing

### Issue #6: Implement GraphQL Integration Tests

**Title:** `6 - Add GraphQL API integration tests for queries and mutations`

**Description:**
No integration tests exist for the GraphQL API endpoints, leaving the entire API layer untested against real GraphQL execution.

**Acceptance Criteria:**
- [ ] Create `GraphQLIntegrationTest` class with `@SpringBootTest`
- [ ] Test `findAllDogs` GraphQL query returns correct JSON structure
- [ ] Test `findDogById` query with valid and invalid IDs
- [ ] Test `deleteDogBreed` mutation removes correct dogs
- [ ] Test `updateDogName` mutation updates dog correctly
- [ ] Test GraphQL error responses for exception scenarios
- [ ] Use real H2 database with test data

**Implementation Details:**
- Use `@SpringBootTest(webEnvironment = RANDOM_PORT)`
- Use `TestRestTemplate` or `WebTestClient` for HTTP requests
- Send actual GraphQL queries as JSON POST requests
- Verify both successful responses and error responses
- Test GraphQL response structure and data types

**Estimated Effort:** 16-20 hours

---

## Priority 5 (P2) - MEDIUM - Quality Assurance

### Issue #7: Add Test Coverage Reporting

**Title:** `7 - Implement JaCoCo test coverage reporting with minimum thresholds`

**Description:**
No test coverage metrics are available, making it impossible to track testing progress and identify untested code.

**Acceptance Criteria:**
- [ ] Add JaCoCo Maven plugin to `pom.xml`
- [ ] Configure minimum coverage thresholds (80% line coverage, 70% branch coverage)
- [ ] Generate HTML coverage reports
- [ ] Exclude auto-generated classes from coverage
- [ ] Build fails if coverage thresholds are not met
- [ ] Coverage reports available in CI pipeline

**Implementation Details:**
- Add JaCoCo plugin with execution goals
- Configure coverage rules and limits
- Exclude Spring Boot auto-configuration classes
- Generate reports in `target/site/jacoco/` directory

**Estimated Effort:** 6-8 hours

---

### Issue #8: Fix CI Java Version Mismatch

**Title:** `8 - Fix CI configuration Java version mismatch (POM Java 17 vs CI Java 21)`

**Description:**
The POM specifies Java 17 but CI is configured for Java 21, creating potential deployment issues.

**Acceptance Criteria:**
- [ ] Update CI configuration to use Java 17 (matching POM)
- [ ] OR update POM to use Java 21 (matching CI) 
- [ ] Ensure consistent Java version across development, CI, and production
- [ ] All CI jobs (build, test, package, verify) use same Java version
- [ ] Document the chosen Java version in README

**Implementation Details:**
- Modify `.github/workflows/maven.yml`
- Update `java-version` in all CI job steps
- Test that application builds and runs correctly with chosen version

**Estimated Effort:** 2-4 hours

---

## Priority 6 (P2) - MEDIUM - Performance & Edge Cases

### Issue #9: Add Performance and Edge Case Tests

**Title:** `9 - Add performance tests and edge case handling for GraphQL operations`

**Description:**
No testing exists for performance characteristics or edge cases, risking poor performance and unexpected failures.

**Acceptance Criteria:**
- [ ] Test database query performance for large datasets
- [ ] Test GraphQL query complexity and execution time
- [ ] Test concurrent access scenarios
- [ ] Test null parameter handling
- [ ] Test invalid ID formats (non-numeric, negative)
- [ ] Test empty database scenarios
- [ ] Memory usage tests for large result sets

**Implementation Details:**
- Use JUnit 5 `@Timeout` annotations
- Create performance benchmarks
- Use `@ParameterizedTest` for edge cases
- Test with various dataset sizes

**Estimated Effort:** 12-16 hours

---

## Priority 7 (P3) - LOW - Advanced Features

### Issue #10: Add GraphQL Schema Contract Tests

**Title:** `10 - Add GraphQL schema validation and contract testing`

**Description:**
No validation exists for GraphQL schema consistency, risking breaking changes to API consumers.

**Acceptance Criteria:**
- [ ] Validate GraphQL schema compilation
- [ ] Test schema backward compatibility
- [ ] Validate type mappings between Java entities and GraphQL types
- [ ] Test GraphQL schema documentation
- [ ] Schema regression testing for API versioning

**Implementation Details:**
- Use GraphQL Java schema validation utilities
- Create schema snapshot tests
- Test schema introspection queries

**Estimated Effort:** 8-12 hours

---

### Issue #11: Add Security and Input Validation Tests  

**Title:** `11 - Add security tests and input validation for GraphQL endpoints`

**Description:**
No security testing exists for GraphQL endpoints, risking security vulnerabilities in input handling.

**Acceptance Criteria:**
- [ ] Test SQL injection prevention
- [ ] Test GraphQL injection attacks
- [ ] Test input size limits
- [ ] Test malformed GraphQL queries
- [ ] Test authentication/authorization (if applicable)
- [ ] Test rate limiting (if applicable)

**Implementation Details:**
- Test with malicious input patterns
- Verify proper input sanitization
- Test error handling for security scenarios

**Estimated Effort:** 10-14 hours

---

## Implementation Timeline

### Week 1: Foundation (Issues #1-2)
- Critical: Application context test
- Critical: Repository layer tests
- Fix: CI Java version mismatch

### Week 2: Core Logic (Issues #3-5)  
- Query resolver tests
- Mutation resolver tests
- Exception handling tests

### Week 3: Integration & QA (Issues #6-8)
- GraphQL integration tests
- Test coverage reporting
- Performance improvements

### Week 4: Advanced Features (Issues #9-11)
- Performance and edge case tests
- Contract testing
- Security testing

## Total Estimated Effort
- **Critical Priority (P0-P1):** 46-64 hours
- **Medium Priority (P2):** 20-28 hours  
- **Advanced Features (P3):** 18-26 hours
- **Total:** 84-118 hours

This prioritized approach ensures the most critical testing gaps are addressed first, providing immediate value and risk reduction for the project.