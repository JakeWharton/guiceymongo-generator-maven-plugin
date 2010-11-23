package com.jakewharton.maven.plugin.guiceymongo_generator;

/*
 * Copyright 2010 Jake Wharton.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.File;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.codehaus.plexus.PlexusTestCase;
import org.junit.Assert;
import org.junit.Test;

import com.jakewharton.maven.plugin.guiceymongo_generator.GuiceyDataMojo;

/**
 * Plugin testing.
 * 
 * @author Jake Wharton
 */
public class GuiceyDataTest extends AbstractMojoTestCase {
	private static final String POM_FILE = "src/test/resources/test-project/pom.xml";
	private static final String POM_GOAL = "generate";
	private static final String GENERATED_FILE = "src/test/resources/test-project/src/main/java/com/testproject/model/Person.java";
	
	private static final String MESSAGE_POM_EXISTS = "Pom.xml exists";
	private static final String MESSAGE_GENERATE_GOAL = "Plugin has generate goal";
	private static final String MESSAGE_GENERATED_FILE_EXISTS = "Generated file exists";
	
    /**
     * <p>Quick and dirty test to see if generation is working properly.</p>
     * 
     * <p>Any other testing is really beyond the scope of this plugin. Testing
     * of the actual GuiceyData generator should be encapsulated in its
     * library.</p>
     * 
     * @throws Exception
     */
	@Test
    public void testGeneratedFile() throws Exception {
        final File pom = PlexusTestCase.getTestFile(GuiceyDataTest.POM_FILE);
        Assert.assertNotNull(GuiceyDataTest.MESSAGE_POM_EXISTS, pom);
        Assert.assertTrue(GuiceyDataTest.MESSAGE_POM_EXISTS, pom.exists());

        final GuiceyDataMojo myMojo = (GuiceyDataMojo)this.lookupMojo(GuiceyDataTest.POM_GOAL, pom);
        Assert.assertNotNull(GuiceyDataTest.MESSAGE_GENERATE_GOAL, myMojo);
        myMojo.execute();
        
        final File generatedFile = new File(GuiceyDataTest.GENERATED_FILE);
        Assert.assertNotNull(GuiceyDataTest.MESSAGE_GENERATED_FILE_EXISTS, generatedFile);
        Assert.assertTrue(GuiceyDataTest.MESSAGE_GENERATED_FILE_EXISTS, generatedFile.exists());
    }
}