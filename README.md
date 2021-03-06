guiceymongo-generator-maven-plugin
==================================

Maven plugin to automatically build your GuiceyMongo metadata files into their
Java source counterparts before the compilation phase.


Usage
-----

Add the following entry to the `<plugins>` section of your `pom.xml`. Configure
the following to your file layout and note what is required and what is only
optional.

    <plugin>
        <groupId>com.jakewharton</groupId>
        <artifactId>guiceymongo-generator-maven-plugin</artifactId>
        <version>1.0.0</version>
        <configuration>
      
            <!--#### REQUIRED ####-->
        
            <!-- Package of generated sources -->
            <outputPackage>com.example.model</outputPackage>
        
            <!--#### OPTIONAL ####-->
        
            <!-- Directory to output generated sources. (Default: main source directory) -->
            <outputDirectory>src/main/java</outputDirectory>
        
            <!-- Use camel casing for field names. (Default: false) -->
            <useCamelCase>false</useCamelCase>
        
            <!-- Paths to check for schema definition files. These can be files -->
            <!-- or directories. (Default: `src/main/guiceymongo`)                -->
            <paths>
                <path>src/main/guiceymongo/com/example/people.data</path>
                <path>src/main/java/com/example/model/</path>
            </paths>
        
            <!-- Extensions to look for when searching directories. (Default: .data) -->
            <extensions>
                <extension>.data</extension>
            </extensions>
        
        </configuration>
      
      
        <!--#### RECOMMENDED ####-->
      
        <!-- This will automatically invoke generation when compiling your project.   -->
        <!-- If you choose not to add it, invoke manually with 'guiceydata:generage'. -->
        <executions>
            <execution>
                <goal>generate</goal>
            </execution>
        </executions>
    </plugin>


Example
-------

See `src/test/resources/test-project/` for an example setup.

Run `mvn test` in the root of this project and then check
`src/test/resources/test-project/src/main/java/com/testproject/model/` for the
generated classes.
