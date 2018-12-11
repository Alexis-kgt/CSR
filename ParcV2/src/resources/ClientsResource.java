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
import internals.Client;

/**
 * Resource exposing the users
 *
 * @author KOUROUMA
 * @author KERMORGANT
 *
 */
public class ClientsResource extends ServerResource {
	/** Backend. */
	private Parc parc_;

	/**
	 * Constructor. Call for every single user request.
	 */
	public ClientsResource() {
		super();
		parc_ = (Parc) getApplication().getContext().getAttributes().get("parc");
	}

	/**
	 *
	 * Returns the list of all the users
	 *
	 * @return JSON representation of the users
	 * @throws JSONException
	 */
	@Get("json")
	public Representation getClients() throws JSONException {
		Client[] clients = parc_.getClients();
		Collection<JSONObject> jsonClients = new ArrayList<JSONObject>();

		for (Client client_ : clients) {
			JSONObject current = new JSONObject();
			current.put("id", client_.getId());
			current.put("name", client_.getName());
			current.put("url", getReference() + "/" + client_.getId());
			current.put("etat", client_.etat);
			jsonClients.add(current);

		}
		JSONArray jsonArray = new JSONArray(jsonClients);
		return new JsonRepresentation(jsonArray);
	}

}
