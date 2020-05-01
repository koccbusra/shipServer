package shipmain;

public class AircraftImpl implements Ship.AircraftOperations, Runnable
{
   private String _codeNumber;
   private int _fuelCapacity;
   private static Thread engineThread;

   public void run()
   {
      System.out.println(_codeNumber + " fuel status = " + _fuelCapacity + " pounds");
      while (_fuelCapacity > 0)
      {
         try 
         {
            Thread.sleep(10000);
         }
         catch (InterruptedException e)
         {
            return;
         }
         _fuelCapacity = _fuelCapacity - 500;
         System.out.println(_codeNumber + " fuel status = " + _fuelCapacity + " pounds");
      }
   }
   public AircraftImpl(String codeNumber)
   {
      _codeNumber = codeNumber;
      _fuelCapacity = 10000;

      this.engineThread = new Thread(this);
      this.engineThread.start(); 
   }

   public String codeNumber()
   {
      return _codeNumber;
   }

   public int fuelCapacity()
   {
      return _fuelCapacity;
   }
   }