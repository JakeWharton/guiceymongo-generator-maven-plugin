package com.jakewharton.maven.plugin.guiceydata;

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
import junit.framework.Assert;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.codehaus.plexus.PlexusTestCase;

/**
 * Plugin testing.
 * 
 * @author Jake Wharton
 */
public class GuiceyDataTest extends AbstractMojoTestCase {
    /**
     * {@inheritDoc}
     */
    protected void setUp() throws Exception {
        super.setUp();

        // TODO ...
    }

    /**
     * {@inheritDoc}
     */
    protected void tearDown() throws Exception {
        super.tearDown();

        // TODO ...
    }

    /**
     * @throws Exception if any
     */
    public void testSomething() throws Exception {
        final File pom = PlexusTestCase.getTestFile("src/test/resources/test-project/pom.xml");
        Assert.assertNotNull(pom);
        Assert.assertTrue(pom.exists());

        final GuiceyDataMojo myMojo = (GuiceyDataMojo)this.lookupMojo("generate", pom);
        Assert.assertNotNull(myMojo);
        myMojo.execute();

        // TODO ...
    }
}