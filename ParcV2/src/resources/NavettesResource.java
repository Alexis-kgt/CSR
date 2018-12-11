package resources;

import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import backend.Parc;
import internals.Navette;

/**
 * Resource exposing the navettes
 *
 * @author KOUROUMA
 * @author KERMORGANT
 *
 */
public class NavettesResource extends ServerResource {

	/** Backend. */
	private Parc parc_;

	/**
	 * Constructor. Call for every single navette request.
	 */
	public NavettesResource() {
		super();
		parc_ = (Parc) getApplication().getContext().getAttributes().get("parc");
	}

	/**
	 *
	 * Returns the list of all the tweets of the navette
	 *
	 * @return JSON representation of the tweets of the navette
	 * @throws JSONException
	 */
	@Get("json")
	public Representation getNavettes() throws JSONException {

		Navette[] navettes = parc_.getNavettes();
		Collection<JSONObject> jsonNavettes = new ArrayList<JSONObject>();

		for (Navette navette_ : navettes) {
			JSONObject current = new JSONObject();
			current.put("id", navette_.getId());
			current.put("name", navette_.getName());
			current.put("url", getReference() + "/" + navette_.getId());
			current.put("etat", navette_.etat);
			//current.put("etat", "ETAT");
			jsonNavettes.add(current);

		}
		JSONArray jsonArray = new JSONArray(jsonNavettes);
		return new JsonRepresentation(jsonArray);
	}

}
