package com.sgg.springboot3.boot;

import com.sgg.springboot3.boot.entity.Article;
import com.sgg.springboot3.boot.service.impl.ArticleServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Random;

@SpringBootTest
class Boot320TransctionApplicationTests {

	@Autowired
	private ArticleServiceImpl articleService;

	@Test
	void testInsertArticle() {
		String content = """
				在芭比乐园里，各种各样的芭比和肯每天都过着童话般100%完美的生活。
				但是某一天，芭比（玛格特·罗比 Margot Robbie 饰）发现自己的生活开始有了变化——比如她的一天不再一帆风顺，
				她开始思考死亡的意义，甚至她的双脚也不再是完美的高跟鞋形——她竟然脚掌落地了！接连出现的不完美打破了芭比乐园的平静，
				意识到存在感危机的芭比被迫前往真实世界探寻真相，肯（瑞恩·高斯林 Ryan Gosling 饰）也一路同行，一场大冒险就此开启。
				""";
		Article article = new Article();
		article.setUserId(new Random().nextInt(1000));
		article.setTitle("忍者神龟：变种大乱斗");
		article.setSummary("美国喜剧动作冒险动画电影");
		article.setReadCount(new Random().nextInt(10000));
		article.setCreateTime(LocalDateTime.of(2023,8 , 11,10,10,10));
		article.setUpdateTime(LocalDateTime.parse("2023-09-03T10:15:30"));



		boolean b = articleService.insertArticle(article, content);
		System.out.println("发布新的文章 = " + b);
	}

}
