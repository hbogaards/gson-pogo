# gson-pogo

This test application demonstrates the problem with rendering a POGO object tree using `GSON` views.

After starting the app with `grails run-app --info` you can issue 2 requests using `curl` or something equivalent:

* First to `http://localhost:8080/family/index.json` to get JSON created from Domain classes.
* Second to `http://localhost:8080/family/index2.json` to get JSON create from POGO classes from `src/main/groovy`.

Both sets of classes are equivalent, with just a `name` property and a list of children.

The JSON from the Domain classes is correct, the JSON from the POGOs is not:

* `[{"id":1,"children":[{"id":1,"children":[{"id":1,"name":"Claus"}],"name":"Bernhard"}],"name":"Hendrik"}]`
* `[{"children":{}"name":"Wilhelmina"}]`

Note the empty `{}` and the missing `,`
