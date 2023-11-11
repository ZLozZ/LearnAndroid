package com.example.b3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Spinner
import android.widget.Toast
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import org.xml.sax.Attributes
import org.xml.sax.SAXException
import org.xml.sax.helpers.DefaultHandler
import java.io.InputStream
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.SAXParser
import javax.xml.parsers.SAXParserFactory

class MainActivity : AppCompatActivity() {
    private lateinit var btnDom: Button
    private lateinit var btnSax: Button
    private lateinit var spnList: Spinner
    private lateinit var listItem: ListView


    private var arrEmployees: MutableList<ManagerEmployees> = mutableListOf()
    private var arrCareer: MutableList<String> = mutableListOf()
    private var arrPhone: MutableList<String> = mutableListOf()
    private lateinit var spninerAdapter: ArrayAdapter<String>

    private lateinit var listAdapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addControls()
        addProceesData()
        addEvents()
    }

    private fun addProceesData() {
        var index = 0
        var flag = 0
        btnDom.setOnClickListener {
            // The information.xml file will be taken in the form of input stream
            val istream: InputStream = assets.open("employees.xml")

            // Steps to convert this input stream into a list
            val builderFactory: DocumentBuilderFactory = DocumentBuilderFactory.newInstance()
            val docBuilder: DocumentBuilder = builderFactory.newDocumentBuilder()
            val doc: Document? = docBuilder.parse(istream)
            val nList: NodeList = doc!!.getElementsByTagName("employee")

            // Iterating through this list
            for (i in 0 until nList.length) {
                if (nList.item(0).nodeType === Node.ELEMENT_NODE) {
                    val elm: Element = nList.item(i) as Element
                    val id: String = elm.getAttribute("id")
                    val career: String = elm.getAttribute("title").toString()
                    val name = getNodeValue("name", elm).toString()
                    val phone = getNodeValue("phone", elm).toString()
                    if (career !in arrCareer) {
                        arrCareer.add(career)
                        index++
                        flag = 1
                    }
                    var arrInfors: MutableList<Infors> = mutableListOf()
                    if (arrEmployees.size == 0) {
                        arrInfors.add(Infors(id, name, phone))
                        arrEmployees.add(ManagerEmployees(career, arrInfors))
                        continue
                    }
                    for (i in 0..arrEmployees.size - 1) {
                        if (arrEmployees[i].career == career) {
                            arrEmployees[i].infors.add(Infors(id, name, phone))
                        }
                        if (flag == 1) {
                            arrInfors.add(Infors(id, name, phone))
                            arrEmployees.add(ManagerEmployees(career, arrInfors))
                            flag = 0
                        }
                    }
                }
            }
            spninerAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrCareer)
            spnList.adapter = spninerAdapter
        }
        btnSax.setOnClickListener {
            //instancing the SAXParserFactory class
            val parserFactory: SAXParserFactory = SAXParserFactory.newInstance()
            //instancing the SAXParser class
            val saxParser: SAXParser = parserFactory.newSAXParser()
            val defaultHandler = object : DefaultHandler() {
                var currentValue = ""
                var currentElement = false
                var id:String = ""
                var career:String = ""
                var name:String = ""
                var phone:String = ""
                //overriding the startElement() method of DefaultHandler
                override fun startElement(
                    uri: String,
                    localName: String,
                    qName: String,
                    attributes: org.xml.sax.Attributes
                ) {
                    currentElement = true
                    currentValue = ""
                    if (localName == "employee") {
                        id = attributes.getValue("id")
                        career = attributes.getValue("title")
                    }
                }

                //overriding the endElement() method of DefaultHandler
                override fun endElement(uri: String, localName: String, qName: String) {
                    currentElement = false
                    //Toast.makeText(this@MainActivity, "$currentValue", Toast.LENGTH_SHORT).show()
                    if (localName.equals("name", ignoreCase = true))
                        name= currentValue
                    else if (localName.equals("phone", ignoreCase = true))
                        phone = currentValue
//                    Toast.makeText(this@MainActivity, "$name", Toast.LENGTH_SHORT).show()
                    index++
                    if(index==3){
                        index = 0
                        if (career !in arrCareer) {
                            arrCareer.add(career)
                            index++
                            flag = 1
                        }
                        var arrInfors: MutableList<Infors> = mutableListOf()
                        if (arrEmployees.size == 0) {
                            arrInfors.add(Infors(id, name, phone))
                            arrEmployees.add(ManagerEmployees(career, arrInfors))
                            arrPhone.add(phone)
                            flag == 0
                        }else {
                            for (i in 0..arrEmployees.size - 1) {
                                if (arrEmployees[i].career == career && phone !in arrPhone) {
                                    arrEmployees[i].infors.add(Infors(id, name, phone))
                                    arrPhone.add(phone)
                                }
                                if (flag == 1 && phone !in arrPhone) {
                                    arrInfors.add(Infors(id, name, phone))
                                    arrEmployees.add(ManagerEmployees(career, arrInfors))
                                    arrPhone.add(phone)
                                    flag = 0
                                }
                            }
                        }
                    }
                }

                //overriding the characters() method of DefaultHandler
                override fun characters(ch: CharArray, start: Int, length: Int) {
                    if (currentElement) {
                        currentValue += String(ch, start, length)
                    }
                }
            }
            val istream = assets.open("employees.xml")
            saxParser.parse(istream, defaultHandler)
            Toast.makeText(this@MainActivity, "$arrEmployees", Toast.LENGTH_SHORT).show()
            spninerAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrCareer)
            spnList.adapter = spninerAdapter
        }
    }

    private fun addEvents() {// Try and Catch for avoiding the application to crash
        spnList.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                var arrInfor: MutableList<String> = mutableListOf()
                for (i in 0..(arrEmployees[position].infors.size - 1)) {
                    arrInfor.add(arrEmployees[position].infors[i].stt + "-" + arrEmployees[position].infors[i].name + "-" + arrEmployees[position].infors[i].phone)
                }
                listAdapter =
                    ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, arrInfor)
                listItem.adapter = listAdapter
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }

    // A function to get the node value while parsing
    private fun getNodeValue(tag: String?, element: Element): String? {
        val nodeList = element.getElementsByTagName(tag)
        val node = nodeList.item(0)
        if (node != null) {
            if (node.hasChildNodes()) {
                val child = node.firstChild
                while (child != null) {
                    if (child.nodeType == Node.TEXT_NODE) {
                        return child.nodeValue
                    }
                }
            }
        }
        // Returns nothing if nothing was found
        return ""
    }

    private fun addControls() {
        btnDom = findViewById(R.id.btnDom)
        btnSax = findViewById(R.id.btnSax)
        spnList = findViewById(R.id.spnList)
        listItem = findViewById(R.id.lstItem)
    }
}