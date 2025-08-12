package myMavenProject;

import org.junit.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class MVN_02TestNGClass {

	@Test
	public void testA() {

		System.out.println("TestA");
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("BeforeMethod");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("AfterMethod");

	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("Beforetest");
	}

	@AfterTest
	public void afterTest() {

		System.out.println("After Test");
	}

}

