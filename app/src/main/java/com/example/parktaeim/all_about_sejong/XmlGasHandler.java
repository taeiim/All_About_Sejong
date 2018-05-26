package com.example.parktaeim.all_about_sejong;

import android.util.Log;

import com.example.parktaeim.all_about_sejong.Model.GasStationItem;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by parktaeim on 2018. 3. 12..
 */

public class XmlGasHandler extends DefaultHandler {
    private String elementValue = null;
    private Boolean elementOn = false;
    private GasStationItem gasStationItem;
    private ArrayList<GasStationItem> gasStationItemArrayList = new ArrayList<>();
    private int rank= 1;

    public ArrayList<GasStationItem> getLowPriceArrayList() {
        return gasStationItemArrayList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);

        elementOn = true;
        if (qName.equals("OIL")) {
            gasStationItem = new GasStationItem();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);

        elementOn = false;
        if (qName.equals("OIL")) {
            gasStationItem.setRank(rank);
            rank++;
            gasStationItemArrayList.add(gasStationItem);
            gasStationItem = null;


        } else if (qName.equals("UNI_ID")) {
            gasStationItem.setId(elementValue);
        } else if (qName.equals("POLL_DIV_CO")) {
            gasStationItem.setType(elementValue);
        } else if (qName.equals("OS_NM")) {
            gasStationItem.setName(elementValue);
        } else if (qName.equals("PRICE")) {
            gasStationItem.setPrice(elementValue);
        } else if (qName.equals("VAN_ADR")) {
            gasStationItem.setAddress(elementValue);
        }


//        else if (qName.equals("DISTANCE")) {
//            gasStationItem.setDistance(elementValue);
//        } else if (qName.equals("NEW_ADR")) {
//            gasStationItem.setId(elementValue);
//        } else if (qName.equals("TEL")) {
//            gasStationItem.setId(elementValue);
//        } else if (qName.equals("CAR_WASH_YN")) {
//            gasStationItem.setId(elementValue);
//        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        if (elementOn) {
            elementValue = (new String(ch, start, length)).trim();
            elementOn = false;

            Log.d("sdsdflk",elementValue);

        }
    }
}
