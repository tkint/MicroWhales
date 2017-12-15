package fr.mickaelbaron.helloworldrestmicroservice.event.rabbitmq;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import javax.inject.Singleton;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author Mickael BARON
 */
@Singleton
public class RabbitMQFactory {

	private static final String RABBIT_MQ_HOST_ENV_KEY = "RABBITMQALIAS_PORT_5672_TCP_ADDR";

	public static final String EXCHANGE_NAME = "helloworld";

	private Channel currentChanel;

	public RabbitMQFactory() throws IOException, TimeoutException, KeyManagementException, NoSuchAlgorithmException, URISyntaxException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUri(getRedisURI());
		Connection connection = factory.newConnection();
		currentChanel = connection.createChannel();

		currentChanel.exchangeDeclare(EXCHANGE_NAME, "fanout");
	}

	public Channel getChannel() {
		return currentChanel;
	}
	
	private URI getRedisURI() {
		String hostVariable = System.getenv(RABBIT_MQ_HOST_ENV_KEY);
		String hostValue = hostVariable != null && !hostVariable.isEmpty() ? "amqp://" + hostVariable + ":5672"	: "amqp://localhost:5672";
		return URI.create(hostValue);
	}
}
