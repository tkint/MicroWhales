package fr.mickaelbaron.helloworldrestmicroservice.dao.redis;

import java.net.URI;

import javax.inject.Singleton;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author Mickael BARON
 */
@Singleton
public class JedisFactory {

	private static final String REDISALIAS_PORT_ENV_KEY = "REDISALIAS_PORT";

	private JedisPool jedisPool;

	public JedisFactory() {
		jedisPool = new JedisPool(new JedisPoolConfig(), getRedisURI());
	}

	public Jedis getJedis() {
		return jedisPool.getResource();
	}

	private URI getRedisURI() {
		String redisPort = System.getenv(REDISALIAS_PORT_ENV_KEY);
		return URI.create(redisPort != null && !redisPort.isEmpty() ? redisPort : "tcp://localhost:6379");
	}
}
