package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse.Suggestion;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.primefaces.event.SelectEvent;


//this class is solely for the search box. This class read data from solr to do autocomplete.
@ManagedBean(name = "AutoComplete") 
@ViewScoped
public class AutoCompleteView implements Serializable{

	 private static final long serialVersionUID = 1L;
	    private String selected;

	    /**
	     * @return the selected
	     */
	    public String getSelected() {
	        return selected;
	    }

	    /**
	     * @param selected
	     *            the selected to set
	     */
	    public void setSelected(String selected) {
	        this.selected = selected;
	    }
	    // the complete method guesses the user input based on what she/he has typed
	    // the params in here are set based on Solr configs. 
	    // the data-configs.xml and the solrconfig.xml in /collection1/conf/ need to be changed if you want to change autocomplete features
	    public ArrayList<String> complete(String query){
	    	ArrayList<String> suggestions = new ArrayList();
	    	SolrServer solr = new HttpSolrServer("http://localhost:8983/solr");
	    	ModifiableSolrParams params = new ModifiableSolrParams();
	    	params.set("qt", "/suggest");
	    	params.set("q", query);
	    	params.set("spellcheck", "on");
	    	//params.set("spellcheck.build", "true");
	    	String[] tempQueryArray = query.split(" ");
	    	StringBuffer tempQ = new StringBuffer();
	    	QueryResponse response = null;
			try {
				response = solr.query(params);
			} catch (SolrServerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// compound word to handle words with space in between
			StringBuffer compoundWord= new StringBuffer();
			List<String> thisSug = new ArrayList<String>();
	    	SpellCheckResponse spellCheckResponse = response.getSpellCheckResponse();
	    	Suggestion suggestion;
	    	if (!spellCheckResponse.isCorrectlySpelled()) {
	    		
	    		Map<String, Suggestion> map = response.getSpellCheckResponse().getSuggestionMap();
	    		// loops in the input and takes words. looks for suggestions and appends to the result
	    		for (int j = 1; j < 4; j++) {			
		    		for (int i = 0; i < tempQueryArray.length; i++) {
		    			suggestion = (map.get(tempQueryArray[i]));
						if (suggestion == null) {
							tempQ.append(tempQueryArray[i]+" ");
						} else {
							if (suggestion.getAlternatives().size()>= j) {
								tempQ.append(suggestion.getAlternatives().get(suggestion.getAlternatives().size()-j)+ " ");
							} else {
								tempQ.append(suggestion.getAlternatives().get(suggestion.getAlternatives().size()-1)+ " ");
							}
						}
					}
		    		// add all of the suggestions to the results to pass to the page
		    		suggestions.add(tempQ.toString());
		    		tempQ = new StringBuffer();
	    		}
	    	}
	    	return suggestions;
	    }
}