package org.paulobichara.customers.service;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Class responsible for activating REST support and define a root path
 * @author Paulo Augusto Dacach Bichara
 *
 */
@ApplicationPath("/rest")
public class JaxRsActivator extends Application
{
}