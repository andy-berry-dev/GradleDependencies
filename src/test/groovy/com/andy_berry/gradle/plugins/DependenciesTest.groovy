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
		project.apply plugin: 'dependencies'
	}
	
	@Test
	public void testPluginCanBeAppliedUsingShortname() {
		assertTrue( project.plugins.hasPlugin(Dependencies.class) )
	}
	
	@Test
	public void testDependenciesFileExtensionIsAddedToProject() {
		assertTrue( project.ext.has("dependenciesFile") )
		assertTrue( project.hasProperty("dependenciesFile") )
	}
	
	@Test
	public void testDependenciesFileCanBeOverridden() {
		project.ext.dependenciesFile = project.file("someFile.properties")
		assertEquals( project.file("someFile.properties"), project.ext.dependenciesFile )
	}
	
}
