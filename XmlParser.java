
import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import org.xml.sax.SAXException;

import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;

import java.util.HashMap;
import java.util.ArrayList;

public class XmlParser{
    private Document document = null;
    private Node root_node = null;
    public static void main(String [] args){
        XmlParser xmlp = new XmlParser();
        xmlp.load_file("");
    }

    XmlParser(){}

    public boolean load_file(String file){
        File f = new File(file);

        if(!(f.exists() && !f.isDirectory())){
            System.out.println("File name or path wrong.");
            return false;
        }
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            document = documentBuilder.parse(file);
            root_node = document.getDocumentElement();
            if(root_node == null){
                System.out.println("Log error xml not valid.");
                return false;
            }
        }catch(ParserConfigurationException err){
            err.printStackTrace();
            return false;
        }catch(SAXException err){
            err.printStackTrace();
            return false;
        }catch(IOException err){
            err.printStackTrace();
            return false;
        }
        return true;
    }

    private Node _get_node_byName(Node chnode, String node_name){
        String[] splitted_node_name = node_name.split("\\.");

        // first check root node
        if(chnode.getNodeName().equals(splitted_node_name[0])){
            if(splitted_node_name.length >1){
                String copy;
                copy = node_name.substring((splitted_node_name[0].length()+1), node_name.length());
                return _get_node_byName(chnode, copy);
            }else{
                return chnode;
            }
        }else{
            // check child nodes
            NodeList childrenList = chnode.getChildNodes();
            for(int i = 0; i<childrenList.getLength(); i++){
                if(childrenList.item(i).getNodeName().equals(splitted_node_name[0])){
                    if(splitted_node_name.length >1){
                        String copy;
                        copy = node_name.substring((splitted_node_name[0].length()+1), node_name.length());
                        return _get_node_byName(childrenList.item(i), copy);
                    }else{
                        return childrenList.item(i);
                    }
                }
            }
        }
        return null;
    }

    public boolean get_SubNodesValuesList(String node_name, HashMap<String, String> dst ){
        
        Node gotNode = _get_node_byName(root_node,node_name);
        NodeList childrenList = gotNode.getChildNodes();
        for(int i = 0; i<childrenList.getLength(); i++){          
            if(childrenList.item(i).getNodeType() != Node.ELEMENT_NODE)continue;
            String key = childrenList.item(i).getNodeName();
            String value = childrenList.item(i).getTextContent();
            dst.put(key, value);          
        }
        //System.out.println("Dest List:");
        //System.out.println(dst);
        return true;

    }

    public boolean get_SubNodesParamsList(String node_name, ArrayList<HashMap<String, String>> dst ){
        
        
        Node gotNode = _get_node_byName(root_node,node_name);
        NodeList childrenList = gotNode.getChildNodes();
        for(int i = 0; i<childrenList.getLength(); i++){
            if(childrenList.item(i).getNodeType() != Node.ELEMENT_NODE){continue;}
            NamedNodeMap attrMap = childrenList.item(i).getAttributes();
            HashMap<String, String> tmp = new HashMap<String, String>();
            for (int j=0; j < attrMap.getLength(); j++){
                Node attribute = attrMap.item(j);
                String key = attribute.getNodeName();
                if(key == null)continue;
                String value = attribute.getTextContent();
                tmp.put(key, value);          
            }
            dst.add(tmp);
        }
        //System.out.println("Dest List:");
        //System.out.println(dst);
        return true;

    }

    public boolean get_SubNodesParamList(String node_name, String param_name, ArrayList<String> dst ){
        
        Node gotNode = _get_node_byName(root_node,node_name);
        NodeList childrenList = gotNode.getChildNodes();

        for(int i = 0; i<childrenList.getLength(); i++){
            if(childrenList.item(i).getNodeType() != Node.ELEMENT_NODE){continue;}
            NamedNodeMap attrMap = childrenList.item(i).getAttributes();
            for (int j=0; j < attrMap.getLength(); j++){
                Node attribute = attrMap.item(j);
                String key = attribute.getNodeName();
                
                if(key == null || key != param_name)continue;
                String value = attribute.getTextContent();             
                dst.add(value);
            }
        }
        //System.out.println("Dest List:");
        //System.out.println(dst);
        return true;

    }

    public boolean get_NodeParamsValues(String node_name, HashMap<String, String> dst ){
        
        
        Node gotNode = _get_node_byName(root_node,node_name);
        NamedNodeMap attrMap = gotNode.getAttributes();
        for (int j=0; j < attrMap.getLength(); j++){
            String key = attrMap.item(j).getNodeName();
            if(key == null)continue;
            String value = attrMap.item(j).getTextContent();
            dst.put(key,value);
        }
        //System.out.println("Dest map:");
        //System.out.println(dst);
        return true;

    }

    public boolean get_NodeParamValue(String node_name, String param_name, String dst ){
        
        
        Node gotNode = _get_node_byName(root_node,node_name);
        NamedNodeMap attrMap = gotNode.getAttributes();
        for (int j=0; j < attrMap.getLength(); j++){
            String key = attrMap.item(j).getNodeName();
            if(key == null || key != param_name)continue;
            String value = attrMap.item(j).getTextContent();
            dst = value;
        }
        //System.out.println("Dest:");
        //System.out.println(dst);
        return true;

    }

}