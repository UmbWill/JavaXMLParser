# JavaXMLParser
An easy XML parser, using w3c library.


This is a useful XML parser in java. 
There are some tiny and cute functions that help to obtain all the informations inside your xml.

## Function description
**get_SubNodesValuesList** : get all the subnodes' values from an input node

* Input:  
  * String node_name : from which node starting to look for
  * HashMap<String, String> dst : the destination HashMap the pair <NodeName,Value>
  
**get_SubNodesParamsList** : get all the subnodes' params' values from an input node

* Input:  
  * String node_name : from which node starting to look for
  * ArrayList<HashMap<String, String>> dst : destination ArrayList, every element of this Array contain an HashMap with pair <ParamName, ParamValue>
  
**get_SubNodesParamList** : get all the subnodes' param single value from an input node

* Input:  
  * String node_name : from which node starting to look for
  * String param_name : param name
  * ArrayList<String> dst : destination ArrayList with the value of the input param for every subnodes
  
**get_NodeParamsValues** : get all the node's params values

* Input:  
  * String node_name : the input node name
  * HashMap<String, String> dst : the destination HashMap the pair <ParamName,Value>  
  
**get_NodeParamValue** : get all the node's param single value 

* Input:  
  * String node_name : the input node name
  * String param_name : param name
  * String dst  : destination String, the single value
  
