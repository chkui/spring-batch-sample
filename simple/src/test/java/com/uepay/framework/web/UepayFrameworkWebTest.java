//package com.uepay.framework.web;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.ResponseEntity;
//
//import com.uepay.framework.test.UepayTestRunner;
//import com.uepay.framework.test.annotation.UepayTest;
//import com.uepay.framework.web.ext.Vo;
//
//import junit.framework.TestCase;
//
//@RunWith(UepayTestRunner.class)
//@UepayTest(testClass = UepayFrameworkWeb.class, environment = UepayTest.Env.Web)
//public class UepayFrameworkWebTest {
//	@Autowired
//	private TestRestTemplate restTemplate;
//
//	@Test
//	public void test() {
//        ResponseEntity<Vo> result = restTemplate.getForEntity("/test/index", Vo.class);
//        Vo vo = result.getBody();
//        TestCase.assertEquals(400, vo.getCode());
//        TestCase.assertEquals("Hello World!", vo.getMsg());
//	}
//}
