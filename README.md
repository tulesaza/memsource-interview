# TODO Service

* This is a skeleton for the backend assignment. For the assignment itself see [ASSIGNMENT.md](ASSIGNMENT.md)

**Things to change before coding:**
* This README
* `build.gradle`
  * `bootJar.baseName`
  * remove `-proc:none` arguments from `compileJava` and `compileTestGroovy` in case you need
  an annotation processor(s) 
* `com.memsource.skeleton.SkeletonApplication`
* `src/main/resources/application.properties`
  * set `server.port`
  * set `request.long-runtime-ms` (for logging)
* `src/main/resources/start`
  * change `SERVICE_NAME`
* `src/main/resources/logback-config.xml`
  * change `service.name` to the name appropriate for your service
* `dependencies.md`
  * change header to reflect the name of your service
* update `apichanges.md` when doing API changes; use [sematic versioning](https://semver.org/)
* `.gitlab-ci.yml`
  * uncomment `pages` and `swagger` jobs once you have services to publish 

**Internal service for ...** 

## Application responsibilities
* ...

## Dependencies

### Build Dependencies
* Bash
* OpenJDK 8
  * Path to the JDK set into `JAVA_HOME` environment variable
* Project `build` placed in the same directory as this project

### Configuration
* copy & verify/adjust the following files into `~/env/services/TODO/` directory:
  * `src/main/resources/application.properties`
  * `src/main/resources/logback-config.xml`
  * `src/main/resources/start`

## Development
* import Gradle project to IntelliJ IDEA (`./gradlew idea` can be used for IDE metadata generation)
* run via IntelliJ IDEA or with `./gradlew bootRun` (developers will likely want to use
`-ea -Dspring.profiles.active=dev -Dslf4j.trace=com.memsource
-Dlogging.config=~/env/services/TODO/logback-config.xml` VM options)

## Test
* tests are written in Groovy using Spock framework
* all tests run with `./gradlew test`

## Code-style notes
* code analysis tools:
   * Java: Checkstyle, SpotBugs
   * Groovy: CodeNarc
* code coverage: JaCoCo

## Other notes
* full build (code-check, tests, code coverage) runs with `./gradlew build jacocoTestReport`
* all reports are in HTML format and can be found in `build/reports` directory

## Profiles
* application has several profiles (other than "production" when no profile is specified):
   * `dev` - used for development
   * `test` - used for tests

| profile         | application port | mongo host  | mongo port | database name    |
|-----------------|------------------|-------------|------------|------------------|
| &lt;production> | TODO             | localhost   | 27017      | TODO             |
| dev             | TODO             | localhost   | 27017      | TODO-dev         |
| test            | <random>         | <in-memory> | -          | -                |

## API Documentation notes
* [interactive documentation](http://dev.gitlab-pages.memsource.com/TODO/)
