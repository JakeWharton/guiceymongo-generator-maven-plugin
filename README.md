guiceydata-maven-plugin
=======================

Maven plugin to automatically build your GuiceyData metadata files into their
Java source counterparts before the compilation phase.


Usage
-----

    <plugin>
      <artifactId>guiceydata-maven-plugin</artifactId>
      <configuration>
        <files>
          <file>src/main/model.data</file>
        </files>
        <outputPackage>com.example.model</outputPackage>
      </configuration>
    </plugin>

*Note*: There is no public repository for this plugin yet.


Example
-------

See `src/test/resources/test-project/` for an example setup.

Run `mvn test` in the root of this project and then check
`src/test/resources/test-project/src/main/java/com/testproject/model/` for the
generated classes.
