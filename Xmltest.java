import java.util.ArrayList;

public class Xmltest{

	public static void main(String[] args){
		XmlParser xmlp = new XmlParser();
		ArrayList<String> dst = new ArrayList<String>();
		xmlp.load_file("graph.xml");
		xmlp.get_SubNodesParamList("Graph.nodes","value", dst);
		System.out.println(dst);
	}
}
