package beans;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import model.CountSheet;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse.Suggestion;
import org.apache.solr.common.params.ModifiableSolrParams;

import com.google.gson.Gson;

public class Test {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		ArrayList<String> list = new ArrayList();
		final File folder = new File("CountSheetRepo/1" );
		System.out.println(folder.exists());
		try {
			for (File fileEntry : folder.listFiles()) {
	            list.add(fileEntry.getName());
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
    public static ArrayList<String> complete(String query){
    	ArrayList<String> suggestions = new ArrayList();
    	SolrServer solr = new HttpSolrServer("http://localhost:8983/solr/collection1/");
    	ModifiableSolrParams params = new ModifiableSolrParams();
    	params.set("qt", "/suggest");
    	params.set("q", query);

    	QueryResponse response = null;
		try {
			response = solr.query(params);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	SpellCheckResponse spellCheckResponse = response.getSpellCheckResponse();
    	if (!spellCheckResponse.isCorrectlySpelled()) {
    		System.out.println(response.getSpellCheckResponse().getSuggestions().size());
    	    for (Suggestion sug : response.getSpellCheckResponse().getSuggestions()) {
    	        System.out.println("original token: " + sug.getToken() + " - alternatives: " + sug.getAlternatives()); 
    	        suggestions.addAll(sug.getAlternatives());
    	    }
    	}
    	return suggestions;
    }
    public static void testGson(){
    	CountSheet cs = new CountSheet();
		cs.setComments("asdasd");
		cs.setId("123");
		cs.setPhysicianName("dr x");
		Gson gson = new Gson();
		String csJson = gson.toJson(cs);
		System.out.println(csJson);
		CountSheet cs2 = gson.fromJson(csJson, CountSheet.class);
		System.out.println(cs2.getId());
    }
}
