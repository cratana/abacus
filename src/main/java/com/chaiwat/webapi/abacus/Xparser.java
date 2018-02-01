package com.chaiwat.webapi.abacus;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.*;

public class Xparser {

	public static void main(String[] args) {
	}

	public static void main2(String[] args) {
		String fileName = "./finance.yahoo.SCB.BK.html";

		try {
			FileInputStream fileIS = new FileInputStream(new File(fileName));
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			Document xmlDocument = builder.parse(fileIS);
			XPath xPath = XPathFactory.newInstance().newXPath();
			String expression = "/Tutorials/Tutorial";
			try {
				NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
				int i = nodeList.getLength();
				for(int j=0; j<i; j++) {
					System.out.println(nodeList.item(j));
				}

			} catch (XPathExpressionException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	protected static void printFile(String fileName) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(new File(fileName)));
			String lineString;
			while((lineString = in.readLine()) != null) {
				System.out.println(lineString);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
