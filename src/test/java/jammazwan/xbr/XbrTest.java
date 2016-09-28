package jammazwan.xbr;

import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XbrTest extends CamelSpringTestSupport {

	@Override
	protected AbstractXmlApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext("META-INF/spring/camel-context.xml");
	}

	@Test
	public void testXbr() throws Exception {
		String reply = template.requestBody("direct:xbr", "No Meaning Here", String.class);
		assertEquals("My No Meaning Here", reply);
	}

	@Test
	public void testResolvePropertyPlaceholders() throws Exception {
		String reply = template.requestBody("direct:resolvePropertyPlaceholders", "seed", String.class);
		assertEquals("seed William", reply);
	}

	@Test
	public void testGitStatus() throws Exception {
		String reply = template.requestBody("direct:start", "seed", String.class);
		assertEquals("true William", reply);
	}

}
