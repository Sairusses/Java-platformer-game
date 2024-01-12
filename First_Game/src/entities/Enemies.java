package entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Enemies {

    public List<Mushroom> mushrooms;
    public List<Eye> eyes;

    Player player;
    Random r = new Random();
    int randomX = r.nextInt(0, 1130);
    Thread spawnThread;

    public Enemies(Player player) {
        this.player = player;
        mushrooms = new ArrayList<>();
        eyes = new ArrayList<>();
        spawnEnemies();
        startSpawnThread();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void spawnEnemies() {
        randomX = r.nextInt(0, 1130);
        spawnMushroom(randomX, 300, player);
        randomX = r.nextInt(0, 1130);
        spawnEye(randomX, 300, player);
    }

    private void startSpawnThread() {
        spawnThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000); // Sleep for 5 seconds
                    randomX = r.nextInt(0, 1130);
                    spawnMushroom(randomX, 300, player);
                    randomX = r.nextInt(0, 1130);
                    spawnEye(randomX, 300, player);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        spawnThread.start();
    }

    public void stopSpawnThread() {
        spawnThread.interrupt();
    }

    public void spawnMushroom(float x, float y, Player player) {
        Mushroom newMushroom = new Mushroom(x, y, player);
        mushrooms.add(newMushroom);
    }

    public void spawnEye(float x, float y, Player player) {
        Eye newEye = new Eye(x, y, player);
        eyes.add(newEye);
    }

    public void update() {
        for (Mushroom mushroom : mushrooms) {
            if (mushroom.isAlive()) mushroom.update();
        }
        for (Eye eye : eyes) {
            if (eye.isAlive()) eye.update();
        }
    }

    public void checkEnemyHit(Rectangle rect) {
        for (Mushroom mushroom : mushrooms) {
            if (mushroom.isAlive())
                if (rect.intersects(mushroom.getHitbox())) {
                    mushroom.hurt(mushroom.mushroomDamage);
                    return;
                }
        }
        for (Eye eye : eyes) {
            if (eye.isAlive())
                if (rect.intersects(eye.getHitbox())) {
                    eye.hurt(eye.eyeDamage);
                    return;
                }
        }
    }

    public void render(Graphics g) {
        for (Mushroom mushroom : mushrooms) {
            if (mushroom.isAlive()) mushroom.render(g);
        }
        for (Eye eye : eyes) {
            if (eye.isAlive()) eye.render(g);
        }
    }
}
