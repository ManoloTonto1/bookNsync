package app;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Sync_Provider {
    private ArrayList<Subscriber> subscribers = new ArrayList<>();

    public Sync_Provider() {
    }

    public void Subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void Notify(String file, ArrayList<Subscriber> subscriberss) {
        for (int i = 0; i < subscriberss.size(); i++) {

            subscriberss.get(i).update(file);
        }

    }

    public void Sync() {

        Thread t1 = new Thread(runable);
        t1.start();

    }

    Runnable runable = new Runnable() {

        @Override
        public void run() {
            Sync_Provider sync = new Sync_Provider();
            try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
                listen(sync, watchService);

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

        }

        private void UpdateDoc(Sync_Provider sync, WatchService watchService) throws InterruptedException {
            WatchKey key;
            while ((key = watchService.take()) != null) {
                for (WatchEvent<?> event : key.pollEvents()) {
                    String file = String.valueOf(event.context());
                    /* update the file */
                    sync.Notify(file, subscribers);
                    break;
                }
                key.reset();
                run();
                TimeUnit.SECONDS.sleep(1);
            }
        }

        private void listen(Sync_Provider sync, WatchService watchService) throws InterruptedException {
            ListenService(watchService);
            UpdateDoc(sync, watchService);
        }

        private void ListenService(WatchService watchService) {
            Path path = Paths.get(System.getProperty("user.dir"));

            try {
                path.register(
                        watchService,
                        StandardWatchEventKinds.ENTRY_CREATE,
                        StandardWatchEventKinds.ENTRY_DELETE,
                        StandardWatchEventKinds.ENTRY_MODIFY);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    };

}
