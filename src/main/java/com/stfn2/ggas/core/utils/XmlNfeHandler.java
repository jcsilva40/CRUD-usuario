package com.stfn2.ggas.core.utils;

import com.stfn2.ggas.services.componentes.faturamento.vo.NfeVO;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlNfeHandler extends DefaultHandler {

	private static final String SERIE = "serie";
	private static final String NUMERO = "nNF";
	private static final String CHAVE = "chNFe";

	private NfeVO nfe;
	private StringBuilder elementValue;

	public void characters(char[] ch, int start, int length) throws SAXException {
		if (elementValue == null) {
			elementValue = new StringBuilder();
		} else {
			elementValue.append(ch, start, length);
		}
	}

	public void startDocument() throws SAXException {
		nfe = new NfeVO();
	}

	public void startElement(String uri, String localName, String tag, Attributes atributos) throws SAXException {
		switch (tag) {
		case SERIE:
			elementValue = new StringBuilder();
			break;
		case NUMERO:
			elementValue = new StringBuilder();
			break;
		case CHAVE:
			elementValue = new StringBuilder();
			break;
		}
	}

	public void endElement(String uri, String localName, String tag) throws SAXException {
		switch (tag) {
		case SERIE:
			nfe.setSerie(Integer.parseInt(elementValue.toString()));
			break;
		case NUMERO:
			nfe.setNumeroProtocoloEnvio(elementValue.toString());
			break;
		case CHAVE:
			nfe.setChaveAcesso(elementValue.toString());
			break;
		}
	}

	public NfeVO getNfe() {
		return nfe;
	}
}