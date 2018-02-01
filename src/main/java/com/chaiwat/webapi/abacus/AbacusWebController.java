package com.chaiwat.webapi.abacus;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class AbacusWebController {

	@RequestMapping(value = "/index")
	public String hello() {
		return "Hello Spring Boot!";
	}

	@RequestMapping("/get")
	public String get(@RequestParam("name") String value) {
		System.out.println(">>>>>>>>>>> @RequestParam = " + value);

		return value;
	}

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value="name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

}
