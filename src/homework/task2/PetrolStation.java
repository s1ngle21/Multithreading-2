package homework.task2;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


public class PetrolStation {
    private int amount;
    private Semaphore semaphore = new Semaphore(3, true);

    public PetrolStation(int amount) {
        this.amount = amount;
    }

    public void doRefuel(double fuelNeededToRefuel) throws InterruptedException {
        semaphore.acquire();
        String numberOfColum = Thread.currentThread().getName().substring(14);

        int refuelTime = ThreadLocalRandom.current().nextInt(3, 10);
        System.out.println("Petrol colum-" + numberOfColum + " Fueling up request " + fuelNeededToRefuel + "\n Fueling will take " + refuelTime + " seconds\n");
        TimeUnit.SECONDS.sleep(refuelTime);

            synchronized (this) {
            if (fuelNeededToRefuel > amount) {
                System.out.println("Petrol colum-" + numberOfColum + " Can not fuel, there is no fuel at the station\n");
            } else {
                this.amount -= fuelNeededToRefuel;
                System.out.println("Petrol colum-" + numberOfColum + " Fueled in " + fuelNeededToRefuel + " liters in " + refuelTime + " seconds\n");
            }
        }
        semaphore.release();
    }
}
