package converter.ihm.tracker;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import contratconvertisseur.Convertisseur;
import convertisseurframe.ConvertisseurFrame;

public class Activateur implements BundleActivator, ServiceTrackerCustomizer<Convertisseur, Convertisseur>{
	
	private static BundleContext context;
	private ConvertisseurFrame frame;
	private ServiceReference<Convertisseur> ref;
	private Convertisseur c;
	private ServiceTracker<Convertisseur, Convertisseur> tracker;
	
	// BundleActivator Part //
	@Override
	public void start(BundleContext bundleContext) throws Exception {
		Activateur.context = bundleContext;
		this.ref = context.getServiceReference(Convertisseur.class);
		this.c = null;
		this.tracker = new ServiceTracker<>(Activateur.context, Convertisseur.class, this);
		this.tracker.open();
		
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

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		Activateur.context = null;
		if(this.frame != null) this.frame.dispose();
		this.frame = null;
		this.c = null;
		this.ref = null;
	}
	
	// Tracker Part //
	static BundleContext getContext() {
		return context;
	}

	@Override
	public Convertisseur addingService(ServiceReference<Convertisseur> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modifiedService(ServiceReference<Convertisseur> arg0, Convertisseur arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removedService(ServiceReference<Convertisseur> arg0, Convertisseur arg1) {
		// TODO Auto-generated method stub
		
	}

}
