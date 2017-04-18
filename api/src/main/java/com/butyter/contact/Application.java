package com.butyter.contact;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.butyter.contact.health.DataSourceConnectionHealthCheck;
import com.butyter.contact.resources.ContactResource;

import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class Application extends io.dropwizard.Application<ApplicationConfiguration> {
	public static void main(String[] args) throws Exception {
		new Application().run(args);
	}

	@Override
	public String getName() {
		return "Contact API Micro Service";
	}

	@Override
	public void initialize(Bootstrap<ApplicationConfiguration> bootstrap) {
		bootstrap.addBundle(new AssetsBundle("/apidocs", "/apidocs", "index.html"));
	}

	@Override
	public void run(ApplicationConfiguration configuration, Environment environment) {
		// init Spring context
		// before we init the app context, we have to create a parent context
		// with all the config objects others rely on to get initialized
		AnnotationConfigWebApplicationContext parent = new AnnotationConfigWebApplicationContext();
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();

		parent.refresh();
		parent.getBeanFactory().registerSingleton("configuration", configuration);
		parent.registerShutdownHook();
		parent.start();

		// the real main app context has a link to the parent context
		ctx.setParent(parent);
		ctx.register(ApplicationSpringConfiguration.class);
		ctx.refresh();
		ctx.registerShutdownHook();
		ctx.start();

		// Add the health checks to the environment
		environment.healthChecks().register("databaseHealthCheck", ctx.getBean(DataSourceConnectionHealthCheck.class));

		// resources
		environment.jersey().register(ctx.getBean(ContactResource.class));

		// resources
		/*
		 * Map<String, Object> resources =
		 * ctx.getBeansWithAnnotation(Path.class); for(Map.Entry<String,Object>
		 * entry : resources.entrySet()) {
		 * environment.jersey().register(entry.getValue()); }
		 */

		/*
		 * final HelloWorldResource resource = new HelloWorldResource(
		 * configuration.getTemplate(), configuration.getDefaultName() );
		 */

		// final TemplateHealthCheck healthCheck =
		// new TemplateHealthCheck(configuration.getTemplate());
		// environment.healthChecks().register("template", healthCheck);

		// environment.jersey().register(ctx.getBean(HelloWorldResource.class));

		// last, but not least, let's link Spring to the embedded Jetty in
		// Dropwizard
		// environment.servlets().addServletListeners(new
		// SpringContextLoaderListener(ctx));

	}

}
