package com.andy_berry.gradle.plugins

import groovy.util.ConfigSlurper

import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project

class Dependencies implements Plugin<Project> 
{

	private static final String DEFAULT_DEPENDENCIES_FILENAME = "dependencies.properties"
	
	private HashMap<String,String> dependencies = new HashMap<String,String>()
	
	void apply(Project project) 
	{
		
		Project dendenciesFileProject = project
		while (dendenciesFileProject.parent != null)
		{
			dendenciesFileProject = dendenciesFileProject.parent
		}
		
		if (!project.ext.has('dependenciesFile'))
		{
			project.ext.dependenciesFile = dendenciesFileProject.file(DEFAULT_DEPENDENCIES_FILENAME)
		}
		
		if (project.ext.dependenciesFile.exists())
		{
			dependencies = new ConfigSlurper().parse( project.ext.dependenciesFile.toURL() )			
		}
		
		println dependencies
		project.ext.dependency = { String dep ->
			if (dependencies.dep == null) {
				throw new GradleException("No dependency found for '${dep}'.")
			}
			else
			{
				return dependencies[dep]
			}

		}
		
	}
	
}
