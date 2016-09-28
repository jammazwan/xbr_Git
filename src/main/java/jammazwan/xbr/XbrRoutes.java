package jammazwan.xbr;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.jgit.api.Status;


public class XbrRoutes extends RouteBuilder {

    
    

    @Override
    public void configure() throws Exception {
        from("direct:xbr")
            .process(new Processor() {
                public void process(Exchange exchange) throws Exception {
                    String text = exchange.getIn().getBody(String.class);
                    String newAnswer = "My " + text;
                    exchange.getOut().setBody(newAnswer);
                }
            });
    	from("direct:resolvePropertyPlaceholders").process(new Processor() {
    		public void process(Exchange exchange) throws Exception {
    			String text = exchange.getIn().getBody(String.class);
    			String newAnswer = text + " " + getContext().resolvePropertyPlaceholders("{{gitusername}}");
    			exchange.getOut().setBody(newAnswer);
    		}
    	});
    	from("direct:start")
        .to("git://.?operation=status").process(new Processor() {
    		public void process(Exchange exchange) throws Exception {
    			Status response = exchange.getIn().getBody(Status.class);
    			String newAnswer = response.isClean() + " " + getContext().resolvePropertyPlaceholders("{{gitusername}}");
    			exchange.getOut().setBody(newAnswer);
    		}
    	});
    }
}
