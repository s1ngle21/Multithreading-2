package homework.task2;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;


public class PetrolStation {
    private final static Logger LOGGER = Logger.getLogger(PetrolStation.class.getName());
    private int amount;
    private Semaphore semaphore = new Semaphore(3, true);

    public PetrolStation(int amount) {
        this.amount = amount;
    }

    public void doRefuel(double fuelNeededToRefuel) {
        String numberOfColum = Thread.currentThread().getName().substring(14);
        int refuelTime = ThreadLocalRandom.current().nextInt(3, 10);
        try {
            semaphore.acquire();
            LOGGER.info("Petrol colum-" + numberOfColum + " Fueling up request " + fuelNeededToRefuel + "\n Fueling will take " + refuelTime + " seconds\n");
            TimeUnit.SECONDS.sleep(refuelTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        synchronized (this) {
            if (fuelNeededToRefuel > amount) {
                LOGGER.info("Petrol colum-" + numberOfColum + " Can not fuel, there is no fuel at the station\n");
            } else {
                this.amount -= fuelNeededToRefuel;
                LOGGER.info("Petrol colum-" + numberOfColum + " Fueled in " + fuelNeededToRefuel + " liters in " + refuelTime + " seconds\n");
            }
        }
        semaphore.release();
    }
}
