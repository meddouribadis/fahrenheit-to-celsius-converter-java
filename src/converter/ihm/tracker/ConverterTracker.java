package converter.ihm.tracker;

import contratconvertisseur.Convertisseur;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

public class ConverterTracker implements ServiceTrackerCustomizer<Convertisseur, Convertisseur> {
	
	private BundleContext context;
	private ServiceReference<Convertisseur> sr;
	private Convertisseur c;
	
	public ConverterTracker(BundleContext bc, ServiceReference<Convertisseur> sr, Convertisseur c) {
		this.context = bc;
		this.sr = sr;
		this.c = c;
	}

	@Override
	public Convertisseur addingService(ServiceReference<Convertisseur> sr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modifiedService(ServiceReference<Convertisseur> sr, Convertisseur c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removedService(ServiceReference<Convertisseur> sr, Convertisseur c) {
		// TODO Auto-generated method stub
		
	}



}
