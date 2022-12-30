package com.example.projectemployeeretro;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ProjectEmployeeRetroApplicationTests {

	BasicTest basicTest = new BasicTest();
	@Test
	void contextLoads() {
		int a = 20;
		int b = 50;
		int result = basicTest.add(a, b);
		assertThat(result).isEqualTo(70);
	}
	class BasicTest{
		int add(int a, int b){
			return a + b;
		}
	}

}
