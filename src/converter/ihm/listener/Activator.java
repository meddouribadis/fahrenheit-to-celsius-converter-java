package converter.ihm.listener;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;

import contratconvertisseur.Convertisseur;
import convertisseurframe.ConvertisseurFrame;

public class Activator implements BundleActivator, ServiceListener {

	private static BundleContext context;
	private ConvertisseurFrame frame;
	private ServiceReference<Convertisseur> ref;
	private Convertisseur c;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		this.ref = context.getServiceReference(Convertisseur.class);
        context.addServiceListener(this);
		
		if(ref == null) {
			System.out.println("No service found, waiting for ConvertisseurAPI service.");
			this.c = null;
		}
		else {
			this.frame = new ConvertisseurFrame("Converter Badis");
			this.c = context.getService(ref);
			this.frame.setConvertisseur(this.c);
		}

	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		if(this.frame != null) this.frame.dispose();
		this.frame = null;
		this.c = null;
		this.ref = null;
        context.removeServiceListener(this);
	}

	@Override
	public void serviceChanged(ServiceEvent event) {
		String[] objectClass = (String[]) event.getServiceReference().getProperty("objectClass");
		System.out.println("Service changed : " + objectClass[0]);
		
		if(objectClass[0].equals(Convertisseur.class.getName())) {
			ServiceReference<Convertisseur> sr = (ServiceReference<Convertisseur>) event.getServiceReference();
			
			switch(event.getType()) {
			case ServiceEvent.REGISTERED:
				this.traiterServiceRegistered(sr);
				break;
			case ServiceEvent.MODIFIED:
				this.traiterServiceModified(sr);
				break;
			case ServiceEvent.UNREGISTERING:
				this.traiterServiceDeleted(sr);
			}
			
		}
		
	}
	
	private void traiterServiceModified(ServiceReference<Convertisseur> sr) {
		this.ref = sr;
		this.c = context.getService(ref);
		this.frame.setConvertisseur(this.c);
	}
	
	private void traiterServiceRegistered(ServiceReference<Convertisseur> sr) {
		this.frame = new ConvertisseurFrame("Converter Badis");
		this.ref = sr;
		this.c = context.getService(ref);
		this.frame.setConvertisseur(this.c);
	}
	
	private void traiterServiceDeleted(ServiceReference<Convertisseur> sr) {
		this.frame.dispose();
		this.frame = null;
		this.c = null;
		this.ref = null;
		searchService();
	}
	
	private void searchService() {
		this.ref = Activator.context.getServiceReference(Convertisseur.class);
		
		if(ref == null) {
			System.out.println("No services.");
			this.c = null;
		}
		else {
			this.frame = new ConvertisseurFrame("Converter Badis");
			this.c = context.getService(ref);
			this.frame.setConvertisseur(this.c);
		}
	}

}
