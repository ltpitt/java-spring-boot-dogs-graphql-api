[![build](https://github.com/ltpitt/java-spring-boot-dogs-graphql-api/workflows/build/badge.svg)](https://github.com/ltpitt/java-spring-boot-dogs-graphql-api/actions)
[![CodeQL](https://github.com/ltpitt/java-spring-boot-dogs-graphql-api/workflows/CodeQL/badge.svg)](https://github.com/ltpitt/java-spring-boot-dogs-graphql-api/actions?query=workflow%3ACodeQL)
[![GitHub Issues](https://img.shields.io/github/issues-raw/ltpitt/java-spring-boot-dogs-graphql-api)](https://github.com/ltpitt/java-spring-boot-dogs-graphql-api/issues)
[![Total Commits](https://img.shields.io/github/last-commit/ltpitt/java-spring-boot-dogs-graphql-api)](https://github.com/ltpitt/java-spring-boot-dogs-graphql-api/commits)
[![GitHub commit activity](https://img.shields.io/github/commit-activity/4w/ltpitt/java-spring-boot-dogs-graphql-api?foo=bar)](https://github.com/ltpitt/java-spring-boot-dogs-graphql-api/commits)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](https://github.com/ltpitt/java-spring-boot-dogs-graphql-api/blob/master/LICENSE)
![Contributions welcome](https://img.shields.io/badge/contributions-welcome-orange.svg)

# Java Spring Boot Dogs GraphQL API
> A Dog Rest Api written using Spring Boot and GraphQL

## Prerequisites

- Download and install [Intellij IDEA](https://www.jetbrains.com/idea/download)

## How to use

- Clone this repo locally
- Open the project with IntelliJ IDEA
- Run the project
- Open [GraphiQL](http://localhost:8081/graphiql)

Run the following example queries:

```
query {findAllDogs {
  id
  name
  breed
  origin
}}
```

```
query {
  findDogById(id: 1) {
    id
    breed
  }
}
```


## Meta

Davide Nastri – d.nastri@gmail.com

Distributed under the MIT license. See ``LICENSE`` for more information.

[Java Spring Boot Dogs GraphQL API](https://github.com/ltpitt/java-spring-boot-graphql-dogs-api)

## Contributing

1. Fork it (<https://github.com/ltpitt/java-spring-boot-graphql-dogs-api/fork>)
2. Create your feature branch (`git checkout -b feature/fooBar`)
3. Commit your changes (`git commit -am 'Add some fooBar'`)
4. Push to the branch (`git push origin feature/fooBar`)
5. Create a new Pull Request
