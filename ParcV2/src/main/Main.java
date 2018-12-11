package main;

import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Context;
import org.restlet.data.Protocol;

import application.MyParcApplication;
import backend.Parc;

/**
 *
 * @author KOUROUMA & KERMORGANT
 */
public final class Main
{

    /** Hide constructor. */
    private Main()
    {
        throw new UnsupportedOperationException();
    }

    /**
     * Main method.
     *
     * @param args  The arguments of the commande line
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        // Create a component
        Component component = new Component();
        Context context = component.getContext().createChildContext();
        component.getServers().add(Protocol.HTTP, 8124);

        // Create an application
        Application application = new MyParcApplication(context);

        // Add the backend into component's context
        Parc parc = new Parc();
        context.getAttributes().put("parc", parc);
        component.getDefaultHost().attach(application);

        // Start the component
        component.start();
        
    }

}
