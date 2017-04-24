package com.efeiyi.support.cache.jedis;

import redis.clients.jedis.ShardedJedis;

public interface Executor<K> {
	public K execute(ShardedJedis jedis);
}
