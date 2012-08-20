package com.andy_berry.gradle.plugins

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
//import org.gradle.api.*
import org.junit.*
import static org.junit.Assert.*

class DependenciesTest {

	private Project project
	
	@Before
	public void setup()
	{
		project = ProjectBuilder.builder().build()
	}
	
	@Test
	public void testPluginCanBeAppliedUsingShortname() 
	{
		project.apply plugin: 'dependencies'
		assertTrue( project.plugins.hasPlugin(Dependencies.class) )
	}
	
	@Test
	public void testDependenciesFileExtensionIsAddedToProject() 
	{
		project.apply plugin: 'dependencies'
		assertTrue( project.ext.has("dependenciesFile") )
		assertTrue( project.hasProperty("dependenciesFile") )
	}
	
	@Test
	public void testDefaultDependenciesFileIsInProjectDir() 
	{
		project.apply plugin: 'dependencies'
		assertEquals( project.file("dependencies.properties"), project.ext.dependenciesFile )
	}
	
	@Test
	public void testDependenciesFileCanBeOverridden() 
	{
		project.ext.dependenciesFile = project.file("someFile.properties")
		project.apply plugin: 'dependencies'
		assertEquals( project.file("someFile.properties"), project.ext.dependenciesFile )
	}

	@Test
	public void testDefaultDependenciesFileIsAddedToRootProject() 
	{
		Project childProject1 = ProjectBuilder.builder().withParent(project).build()
		Project childProject2 = ProjectBuilder.builder().withParent(childProject1).build()
		
		project.apply plugin: 'dependencies'
		childProject1.apply plugin: 'dependencies'
		childProject2.apply plugin: 'dependencies'
		
		assertEquals( project.file("dependencies.properties"), project.ext.dependenciesFile )
		assertEquals( project.file("dependencies.properties"), childProject1.ext.dependenciesFile )
		assertEquals( project.file("dependencies.properties"), childProject2.ext.dependenciesFile )
	}

	@Test
	public void testDependenciesDefinitionsAreLoaded()
	{
		File dependenciesFile = project.file("dependencies.properties")
		dependenciesFile.createNewFile()
		dependenciesFile.text = "someDep = 'some-group:some-id:1.2.3'"
		project.ext.dependenciesFile = dependenciesFile
	
		project.apply plugin: 'dependencies'
		assertEquals( 'some-group:some-id:1.2.3', project.dependency("someDep") )
	}
		
}
