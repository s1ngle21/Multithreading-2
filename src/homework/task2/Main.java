package homework.task2;

import java.util.concurrent.*;

public class Main {
    private static ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {
        PetrolStation petrolStation = new PetrolStation(100);

        for (int i = 1; i < 5; i++) {
            int fuelNeeded = ThreadLocalRandom.current().nextInt(10, 40);
            Future<?> res = executorService.submit(() -> petrolStation.doRefuel(fuelNeeded));
        }
        executorService.shutdown();
    }
}




