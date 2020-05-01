package shipmain;

import java.io.FileWriter;

import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;

public class ShipMain {

	public static void main(String[] args) {
		
		  org.omg.CORBA.ORB orb = null;
	      try {
	         orb = org.omg.CORBA.ORB.init(args, null);
	      }
	      catch (org.omg.CORBA.SystemException se) 
	      { 
	         System.err.println("ORB init failure " + se); 
	         System.exit(1); 
	      }

	      orb.init();

	      // TIE uses these two new lines

//	      AircraftCarrierImpl new_carrier = new AircraftCarrierImpl(orb);
//	      Ship.AircraftCarrier carrier = new AircraftCarrier(new_carrier, "Nimitz");
//
//	      try {
//	    	  orb.cre
//	         boa.obj_is_ready(carrier);
//	      }
//	      catch (org.omg.CORBA.SystemException se) 
//	      { 
//	         System.err.println("Object Ready failure " + se); 
//	         System.exit(1); 
//	      }

	      org.omg.CORBA.Object initRef = null;
	      try {
	         initRef = orb.resolve_initial_references("NameService");
	      }
	      catch (org.omg.CORBA.SystemException se) 
	      { 
	         System.err.println("Resolve init failure " + se); 
	         System.exit(1); 
	      }
	      catch (org.omg.CORBA.UserException ue) 
	      { 
	         System.err.println("Resolve init failure " + ue); 
	         System.exit(1); 
	      }

	      NamingContext initContext = null;
	      try {
	         initContext = NamingContextHelper.narrow(initRef);
	      }
	      catch (org.omg.CORBA.SystemException se) 
	      { 
	         System.err.println("Context narrow failure " + se); 
	         System.exit(1); 
	      }

//	    Write object reference to an IOR file so Orbix Client can access

	      try 
	      {
	         FileWriter output = new FileWriter("ns.ior");
	         output.write(orb.object_to_string(initRef));
	         output.close();
	         System.out.println("Wrote IOR to file: ns.ior");
	      }
	      catch(java.io.IOException e) 
	      {
	         System.out.println("Exception: " + e);
	         System.exit(1);
	      }

	      NameComponent[] name = new NameComponent[1];
	      NamingContext objContext = null;
	      try {
	         name[0] = new NameComponent("objects" , "");
	         objContext = initContext.bind_new_context(name);
	      }
	      catch (org.omg.CORBA.SystemException se) 
	      { 
	         System.err.println("Bind init failure " + se); 
	         System.exit(1); 
	      }
	      catch (org.omg.CORBA.UserException ue) 
	      { 
	         System.err.println("Bind init failure " + ue); 
	         System.exit(1); 
	      }

	      NamingContext milContext = null;
	      try {
	         name[0] = new NameComponent("military" , "");
	         milContext = objContext.bind_new_context(name);
	      }
	      catch (org.omg.CORBA.SystemException se) 
	      { 
	         System.err.println("Bind obj failure " + se); 
	         System.exit(1); 
	      }
	      catch (org.omg.CORBA.UserException ue) 
	      { 
	         System.err.println("Bind obj failure " + ue); 
	         System.exit(1); 
	      }

	      try {
	         name[0] = new NameComponent("navy" , "");
	      }
	      catch (org.omg.CORBA.SystemException se) 
	      { 
	         System.err.println("Bind mil failure " + se); 
	         System.exit(1); 
	      }

	      try {
	         orb.init();
	      }
	      catch (org.omg.CORBA.SystemException se) 
	      { 
	         System.err.println("Impl Ready failure " + se); 
	         System.exit(1); 
	      }
	}

}
