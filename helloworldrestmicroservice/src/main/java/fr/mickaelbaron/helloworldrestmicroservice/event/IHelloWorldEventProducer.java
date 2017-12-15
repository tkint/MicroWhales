package fr.mickaelbaron.helloworldrestmicroservice.event;

import fr.mickaelbaron.helloworldrestmicroservice.model.HelloWorld;

/**
 * @author Mickael BARON
 */
public interface IHelloWorldEventProducer {

	void sendMessage(HelloWorld newHelloWorld);
}
