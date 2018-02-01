package com.chaiwat.webapi.abacus;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
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

	private static String webSource = "https://finance.yahoo.com/quote/";

	@RequestMapping(value = "/quote")
	public PriceQuote getQuote(@RequestParam(value="ticker") String ticker) {
		Document doc;
		try {
			doc = Jsoup.connect(webSource+ticker).get();
			Elements dataList = doc.select("#Lead-2-QuoteHeader-Proxy");
			float price;

			// We are looking for:
			// <span class="Trsdu(0.3s) Fw(b) Fz(36px) Mb(-4px) D(ib)" data-reactid="35">
			Elements dataRactID = dataList.select("[data-reactid='35']");

			System.out.println("dataReact Elements size = " + dataRactID.size());
			for (Element e :
					dataRactID) {
				System.out.println(e.toString());
				System.out.println("Data: " + e.data());
				System.out.println("Attributes: " + e.attributes());
				System.out.println("HTML: " + e.html());
				System.out.println("This is what you are looking for ---> " + e.text());
			}
			price = Float.parseFloat(dataRactID.text());

			return new PriceQuote(ticker, doc.title(), price);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}


	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value="name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

}
