package runner;

import org.junit.platform.suite.api.*;
/*
 * @author     Laura Xu
 * @date     2026/02/23
 * @describe Runner
 */
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key="cucumber.glue",value="stepdefinitions, hooks")
@ConfigurationParameter(key="cucumber.plugin", value="pretty, html:build/reports/cucumber.html")
public class RunCucumberTest {
}
