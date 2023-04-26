package homework.task2;

import java.util.concurrent.*;

public class Main {
    private static ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

    public static void main(String[] args) {
        PetrolStation petrolStation = new PetrolStation(100);
        Future<?>[] res = new Future[7];

        for (int i = 1; i < 7; i++) {
            int fuelNeeded = ThreadLocalRandom.current().nextInt(10, 40);
            res[i] = executorService.submit(() -> {
                try {
                    petrolStation.doRefuel(fuelNeeded);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        executorService.shutdown();
    }
}




