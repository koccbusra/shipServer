package shipmain;

import java.util.Dictionary;
import java.util.Hashtable;

public class AircraftCarrierImpl implements Ship.AircraftCarrierOperations
{

   // data member and constructor for TIE

   private org.omg.CORBA.ORB _orb;
   private Dictionary _aircrafts = new Hashtable();

   public AircraftCarrierImpl(org.omg.CORBA.ORB orb)
   {
      _orb = orb;
   }

   public synchronized Ship.Aircraft launch(String name)
   {
      Ship.Aircraft aircraft = (Ship.Aircraft) _aircrafts.get(name);

      if (aircraft == null)
      {
         AircraftImpl new_aircraft = new AircraftImpl(name);
         aircraft = new Ship._AircraftStub();

         // TIE uses _orb. instead of _orb().

         _orb.connect(aircraft);

         System.out.println(aircraft + " on Catapult 2");
         _aircrafts.put(name, aircraft);
      }
      return aircraft;
   }
}