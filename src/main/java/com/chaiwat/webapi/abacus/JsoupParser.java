package com.chaiwat.webapi.abacus;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class JsoupParser {
	public static void main(String[] args) {
		System.out.println("Hello JSoupParser: " + args[0]);
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		String fileName = "finance.yahoo.SCB.BK.html";
		Document doc = null;
		File input = new File(fileName);
		try {
			doc = Jsoup.connect(args[0]).get();
			System.out.println("Doc Title: " + doc.title());
			System.out.println("Node name = " + doc.nodeName());

			// Get outer HTML of this node
//			System.out.println("Outer HTML: " + doc.outerHtml());

			// Access body
//			Element e1 = doc.body();
//			System.out.println("Element = " + e1.text());

			// Find selector #Lead-2-QuoteHeader-Proxy
			Elements dataList = doc.select("#Lead-2-QuoteHeader-Proxy");


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
				System.out.println("Text: " + e.text());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public static void main2(String[] args) {
		System.out.println("Hello JSoupParser: " + args[0]);
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		String fileName = "finance.yahoo.SCB.BK.html";
		Document doc = null;
		File input = new File(fileName);
		try {
			doc = Jsoup.parse(input, "UTF-8", "http://finance.yahoo.com");
			System.out.print(doc.title());
			Elements newsHeadlines = doc.select("div");
			for (Element headline : newsHeadlines) {
				System.out.println(String.format("%s\n\t%s",
						headline.attr("title"), headline.absUrl("href")));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
