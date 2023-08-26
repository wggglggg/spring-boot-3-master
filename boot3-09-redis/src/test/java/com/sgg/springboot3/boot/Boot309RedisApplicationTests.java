package com.sgg.springboot3.boot;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.List;
import java.util.UUID;

@Slf4j
@SpringBootTest
class Boot309RedisApplicationTests {

	@Autowired
	private StringRedisTemplate redisTemplate;

	/**
	 * string： 普通字符串 ： redisTemplate.opsForValue()
	 */
	@Test
	void contextLoads() {
		redisTemplate.opsForValue().set("PriceNum", UUID.randomUUID().toString());

		String priceNum = redisTemplate.opsForValue().get("PriceNum");
		log.info("PriceNum ==> {}", priceNum);
	}

	/**
	 * list:    列表：       redisTemplate.opsForList(), 相同的value为重复添加
	 */
	@Test
	public void testList(){
		String listName = "numList";
		// 为numList列表 添加三个数字
	    redisTemplate.opsForList().rightPush(listName, "1");
	    redisTemplate.opsForList().rightPush(listName, "2");
	    redisTemplate.opsForList().rightPush(listName, "3");

		// 最左边一个删掉并获取这个删掉的值 打印
		String leftPop = redisTemplate.opsForList().leftPop(listName);
		// 删除最右边的
		String rightPop = redisTemplate.opsForList().rightPop(listName);
		log.info("leftPop  ====> {}" , leftPop);
		log.info("rightPop ====> {}" , rightPop);
	}

	/**
	 * set:     集合:       redisTemplate.opsForSet() 重复添加会自动剔除
	 */
	@Test
	public void testSet(){
		String setName = "numSet";
		//1、给集合中添加元素
	    redisTemplate.opsForSet().add(setName, "红");
	    redisTemplate.opsForSet().add(setName, "蓝");
	    redisTemplate.opsForSet().add(setName, "绿");

		Boolean matchResult = redisTemplate.opsForSet().isMember(setName, "黑");
//		Assertions.assertTrue(matchResult);
		// 人为判定matchResult为   假, 如果结果mathcResult确实为假,不报错.
		Assertions.assertFalse(matchResult);
	}

	/**
	 * zset:    有序集合:    redisTemplate.opsForZSet() 重复的不再添加
	 */
	@Test
	public void testZSet(){
		String zsetName = "zsettest";
	    redisTemplate.opsForZSet().add(zsetName, "Freelace无线蓝牙耳机", 178.00);
		redisTemplate.opsForZSet().add(zsetName, "鑫谷850W全模电源", 379.00);
		redisTemplate.opsForZSet().add(zsetName, "欧莱雅（LOREAL）男士洗面奶", 48.30);

		// 找出最贵的一样商品
		ZSetOperations.TypedTuple<String> popMax = redisTemplate.opsForZSet().popMax(zsetName);

		String value = popMax.getValue();
		Double score = popMax.getScore();
		log.info("最贵的商品是 =>> {}", value);
		log.info("商品的价格是 =>> {}", score);
	}

	@Test
	public void testhash(){
		String mapName = "userMap";
	    redisTemplate.opsForHash().put(mapName, "name", "蔡幸娟");
		redisTemplate.opsForHash().put(mapName, "age", "50");

		log.info("name: ==> {} " ,redisTemplate.opsForHash().get(mapName, "name"));
	}
}
