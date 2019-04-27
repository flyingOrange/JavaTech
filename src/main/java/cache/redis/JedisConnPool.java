package cache.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisConnPool {

	private final JedisPool jedisPool;

	public JedisConnPool() {
		// 获得连接池配置对象，设置配置项
		JedisPoolConfig config = new JedisPoolConfig();
		// 最大连接数
		config.setMaxTotal(30);
		// 最大空闲连接数
		config.setMaxIdle(10);
		// 获得连接池
		jedisPool = new JedisPool(config, "localhost", 6379);
	}

	public Jedis getConnection() {
		Jedis jedis = jedisPool.getResource();
		return jedis;
	}

}
