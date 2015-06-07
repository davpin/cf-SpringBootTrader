package io.pivotal.quotes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 * Tests for the Quotes Application.
 * @author David Ferreira Pinto
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = QuotesApplication.class)
public class QuotesApplicationTest {
	/**
	 * test loading of spring context.
	 */
	@Test
	public void contextLoads() {
	}
}
