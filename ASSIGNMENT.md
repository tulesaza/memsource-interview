# Assignment

* This is the skeleton we use for new backend services. Look around, check
[README.md](README.md) file and get familiar with the structure.

* Given this skeleton write a service with `/translate` REST endpoint that will
translate given fields of the JSON file from given source language to given target
language using an MT engine.

  * It must accept `POST` request with `multipart/form-data` containing `options` and `file`.
  * `options` is of type `application/json` containing fields `sourceLanguage` and `targetLanguage`.
  * `file` is the JSON file to be translated. The endpoint should not assume particular JSON structure
   with exception of the conditions stated here. The source text for translation is the value of field matching
   the `sourceLanguage` while the translation should be put into the value of field matching the `targetLanguage`.
   For example when `sourceLanguage` is `en` and `targetLanguage` is `cs` and the input file is
   ```json
   {
     "tests": [
       {
         "en": "Hello world!",
         "cs": "",
         "de": ""
       } 
     ]
   } 
   ``` 
  the result will be (depending on MT output)
  ```json
   {
     "tests": [
       {
         "en": "Hello world!",
         "cs": "Ahoj světe!",
         "de": ""
       } 
     ]
   } 
   ```
  As a reference and to test the service you can use more complex [sample.json](src/test/resources/sample.json).
  * You might safely ignore the case where value corresponding to `sourceLanguage` or `targetLanguage`
  would not be a string or not present inside the same JSON object.
  * The endpoint will return the translated JSON file of type `application/json`.
  * You should provide at least one test for the endpoint.
  * `./gradlew clean build` must pass without any SpotBugs or Checkstyle warning
  * Use Git to track your changes. Do not share it publicly just include the repository metadata when submitting the solution.

* For the MT engine you have these choices:
  * Use an MT engine of your choice providing free/trial accounts such as
  Google, Microsoft or Amazon. It must have REST API or at least REST-like API.
  * Use the Google Translate v3 API https://cloud.google.com/translate/docs/quickstart-client-libraries-v3
  that provides free characters https://cloud.google.com/translate/pricing.
  * If you don't want to use or can't use a real MT engine implement an in-app
  pseudo translation service returning the original string with `Translated` suffix
  as the translation.

* Things to consider:
  * The skeleton is configured to use Spock framework (http://spockframework.org)
  for testing. Feel free to use JUnit if you prefer it.
  * Think about future support of multiple MT engines.
  * Think about future support of different file formats.
  * Use your favorite OSS libraries when needed.

* Sample curl command for the endpoint testing:    
 `curl -X POST -v -Foptions="{\"sourceLanguage\": \"en\", \"targetLanguage\": \"cs\"};type=application/json" -Ffile=@src/test/resources/sample.json 'http://localhost:8888/translate'`

## FAQ

* I'm not sure I understand the task. I'm completely stuck.
  * Contact the assignment submitter.
* There are multiple choices on how to proceed with the implementation.
  * Think about the best approach, document your decisions and proceed.
  * Contact the assignment submitter.