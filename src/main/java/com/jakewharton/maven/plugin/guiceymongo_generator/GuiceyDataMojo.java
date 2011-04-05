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

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import com.mattinsler.guiceymongo.data.generator.GuiceyDataGenerator;
import java.io.File;

/**
 * Goal which automatically generates MongoDB DAO Java source files using
 * GuiceyData definition files.
 * 
 * @goal generate
 * @phase generate-sources
 * @author Jake Wharton <jakewharton@gmail.com>
 */
public class GuiceyDataMojo extends AbstractMojo {
	/**
	 * Default file extension to look for in directories.
	 */
	private static final String DEFAULT_EXTENSION = ".data";

	/**
	 * Message when no paths are explicitly defined in the pom.xml.
	 */
	private static final String MESSAGE_NO_PATHS = "No paths explicitly listed. Using source directory.";

	/**
	 * Message when no extensions are explicitly defined in the pom.xml.
	 */
	private static final String MESSAGE_NO_EXTENSIONS = "No extensions listed. Using default '" + GuiceyDataMojo.DEFAULT_EXTENSION + "'.";

	/**
	 * Message that generation is about to occur.
	 */
	private static final String MESSAGE_GENERATING = "Generating classes";

	/**
	 * Message when a fatal generation error has occurred.
	 */
	private static final String MESSAGE_ERROR = "Fatal error occured while generating classes.";



	/**
	 * Location of the file.
	 * 
	 * @parameter expression="${project.build.sourceDirectory}"
	 * @required
	 */
	private File outputDirectory;

	/**
	 * Output package.
	 * 
	 * @parameter
	 * @package
	 */
	private String outputPackage;

	/**
	 * By convention field names are specified as "field_name", which will
	 * generate an object as <code>{'field_name': true}</code>. Using this
	 * option, <code>{'fieldName': true}</code> will be generated.
	 * 
	 * @parameter default-value="false"
	 */
	private boolean useCamelCase;

	/**
	 * List of file or directory paths to parse.
	 * 
	 * @parameter
	 */
	private String[] paths;

	/**
	 * Path to the source directory. This is used as a default for the
	 * <code>paths</code> parameter if no values were specified.
	 * 
	 * @parameter default-value="src/main/guiceymongo"
	 * @required
	 * @readonly
	 */
	private File defaultFileDirectory;

	/**
	 * List of extensions to parse when listing directories in the
	 * <code>paths</code> parameter.
	 * 
	 * @parameter
	 */
	private String[] extensions;



	/**
	 * Run the goal.
	 */
	public void execute() throws MojoExecutionException {
		// Check for pom.xml specified files
		if ((this.paths == null) || (this.paths.length == 0)) {
			this.getLog().info(GuiceyDataMojo.MESSAGE_NO_PATHS);
			this.paths = new String[] {
					this.defaultFileDirectory.getAbsolutePath()
			};
		}

		// Check for pom.xml specified extensions
		if ((this.extensions == null) || (this.extensions.length == 0)) {
			this.getLog().info(GuiceyDataMojo.MESSAGE_NO_EXTENSIONS);
			this.extensions = new String[] {
					GuiceyDataMojo.DEFAULT_EXTENSION
			};
		}

		this.getLog().info(GuiceyDataMojo.MESSAGE_GENERATING);

		// Set up the generator with our values
		final GuiceyDataGenerator generator = new GuiceyDataGenerator();
		generator.setSourceDirectory(this.outputDirectory.getAbsolutePath());
		generator.setOutputPackage(this.outputPackage);
		generator.setFileExtensions(this.extensions);
		generator.setUseCamelCaseKeys(this.useCamelCase);

		// Hide output stream
		generator.setIsQuiet(true);

		try {
			// Perform the generation
			generator.generate(this.paths);
		} catch (final Exception e) {
			this.getLog().error(GuiceyDataMojo.MESSAGE_ERROR);
			throw new MojoExecutionException(GuiceyDataMojo.MESSAGE_ERROR, e);
		}
	}
}
