package application;


import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import resources.NavettesResource;
import resources.ClientsResource;

/**
 *
 * Application.
 *
 * @author KOUROUMA & KERMORGANT
 *
 */
public class MyParcApplication extends Application
{

    public MyParcApplication(Context context)
    {
        super(context);
    }

    @Override
    public Restlet createInboundRoot()
    {
        Router router = new Router(getContext());
        router.attach("/clients", ClientsResource.class);
        router.attach("/navettes", NavettesResource.class);
        return router;
    }
}
