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
