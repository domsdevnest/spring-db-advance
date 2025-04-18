package hello.itemservice;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@MapperScan("hello.itemservice.repository.mybatis")
class ItemServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
