package com.andy_berry.gradle.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

class Dependencies implements Plugin<Project> 
{

	private static final String DEFAULT_DEPENDENCIES_FILENAME = "dependencies.properties"
	private HashMap<String,String> dependencies = new HashMap<String,String>()
	
	void apply(Project project) 
	{
		project.ext.dependenciesFile = project.file(DEFAULT_DEPENDENCIES_FILENAME)
		
		
	}
	
}
