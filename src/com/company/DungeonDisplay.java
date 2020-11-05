package com.company;

import java.security.Key;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class DungeonDisplay {
    protected int idMax;
    protected boolean hasExit;
    private Map<Integer, Room> dungeonMap = new HashMap<Integer, Room>();
    private Map<Integer, Room> tmpMap = new HashMap<Integer, Room>();

    /**
     * This function copy the tmpMap into the dungeonMap
     */
    public void copyTmpMapIntoDungeonMap() {
        for (Map.Entry<Integer, Room> e : this.tmpMap.entrySet()) {
            if (e.getKey() != null && e.getValue() != null)
                this.dungeonMap.put(e.getKey(), e.getValue());
        }
        tmpMap.clear();
        return;
    }

    public Room randomRoom(int id, Room oldRoom) {
        Monster m;
        Item t = null;
        int randomInt = new Random().nextInt(5);


        if (randomInt < 5) {
            m = new Monster(MonsterType.BARBARIAN);
            {
                m = new Monster(MonsterType.SORCERER);
                return new NormalRoom(randomInt, m);
            }
        } else if (randomInt == 5) {
            m = new Monster(MonsterType.BARBARIAN);
            m = new Monster(MonsterType.SORCERER);
            return new NormalRoom(randomInt, t);
        } else {
            m = null;
        }
        return new NormalRoom(randomInt, m);
    }


    /**
     * This function is used to create a linked room
     *
     * @param room
     */
    public void createLinkedRoom(Room room) {
        int randomInt;
        if (this.idMax == 1) {
            randomInt = 4;
        } else {
            randomInt = new Random().nextInt(3);
        }
        if (randomInt == 0) {
            return;
        }

        int i = 0;
        if (!room.isRoomExist()) {
            while (i != randomInt) {
                Room newRoom = randomRoom(this.idMax, room);
                room.getDoors().put(this.idMax, newRoom);
                tmpMap.put(this.idMax, newRoom);
                this.idMax++;
                i++;
            }
        }
    }

    /**
     * This function is used to create the randomMap
     */
    public void createRandomMap() {
        Room entrance = new EntranceRoom(0);
        this.dungeonMap.put(entrance.getId(), entrance);
        this.idMax = 1;
        for (int i = 0; i < 2; i++) {
            Iterator<Map.Entry<Integer, Room>> it = dungeonMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Integer, Room> elem = it.next();
                Room r = elem.getValue();
                this.createLinkedRoom(r);
                r.setRoomExist(true);
            }
            copyTmpMapIntoDungeonMap();
        }
    }


    /**
     * This function is used to create the classicMap
     *
     * @param level the level to create
     */
    public void createClassicMap(int level) {

        Monster sorcerer = new Monster(MonsterType.SORCERER);
        Monster barbarian = new Monster(MonsterType.BARBARIAN);
        Arm ace = new Arm(ArmType.ACE);
        Arm lightning = new Arm(ArmType.SORT_LIGHTNING);
        Item tresor = new Item("*** - The tresor of God Parizoo - ***","That contains all the happiness that your need in your life");

        if (level == 1) {
            Room room0 = new EntranceRoom(0,barbarian);
            Room room1 = new NormalRoom(1, sorcerer);
            Room room2 = new NormalRoom(2, barbarian);
            Room room3 = new NormalRoom(3, barbarian);
            Room room4 = new NormalRoom(4, sorcerer);
            Room room5 = new NormalRoom(5, tresor);
            room5.setTresor(tresor);

            this.dungeonMap.put(0, room0);
            this.dungeonMap.put(1, room1);
            this.dungeonMap.put(2, room2);
            this.dungeonMap.put(3, room3);
            this.dungeonMap.put(4, room4);
            this.dungeonMap.put(5, room5);


            room0.getDoors().put(room1.getId(), room1);

            room1.getDoors().put(room0.getId(), room0);
            room1.getDoors().put(room2.getId(), room2);

            room2.getDoors().put(room1.getId(), room1);
            room2.getDoors().put(room3.getId(), room3);

            room3.getDoors().put(room2.getId(), room2);
            room3.getDoors().put(room4.getId(), room4);

            room4.getDoors().put(room3.getId(), room3);
            room4.getDoors().put(room5.getId(), room5);


            room5.getDoors().put(room4.getId(), room4);

        }
        }

    public int getIdMax() {
        return idMax;
    }

    public Map<Integer, Room> getDungeonMap() {
        return dungeonMap;
    }

    public void setDungeonMap(Map<Integer, Room> dungeonMap) {
        this.dungeonMap = dungeonMap;
    }

    public Map<Integer, Room> getMap() {
        return dungeonMap;
    }
    }

