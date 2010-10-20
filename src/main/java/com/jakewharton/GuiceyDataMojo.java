package com.jakewharton;

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

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import com.lowereast.guiceymongo.data.generator.GuiceyDataGenerator;
import java.io.File;

/**
 * Goal which automatically regenerates 
 *
 * @goal generate
 * @phase generate-sources
 * @author Jake Wharton
 */
public class GuiceyDataMojo extends AbstractMojo {
	
    /**
     * Location of the file.
     * 
     * @parameter expression="${project.build.sourceDirectory}"
     * @required
     */
    private File outputDirectory;
    
    /**
     * Output package
     * 
     * @parameter
     * @package
     */
    private String outputPackage;
    
    /**
     * <p>By convention field names are specified as field_name, which will
	 * generate an object as <code>{'field_name': true}</code>.  Using this
	 * option, <code>{'fieldName': true}</code> will be generated.</p>
	 * 
	 * @parameter default-value="false"
     */
    private boolean useCamelCase;
    
    /**
     * List of .data files to parse.
     * 
     * @parameter
     * @required
     */
    private String[] files;

    
    
    /**
     * Run the goal.
     */
    public void execute() throws MojoExecutionException
    {
    	final GuiceyDataGenerator generator = new GuiceyDataGenerator();
    	
    	generator.setSourceDirectory(outputDirectory.getAbsolutePath());
    	generator.setOutputPackage(this.outputPackage);
    	
    	generator.generate(this.useCamelCase, this.files);
    }
}
