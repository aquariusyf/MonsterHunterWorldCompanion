package com.yzhang.monsterhunterworldcompanion.appdatabase.events;

import java.util.Date;

public class EventQuest {

    private int id;
    private String name;
    private String type;
    private String description;
    private String requirements;
    private int questRank;
    private String successConditions;
    private Date startTimestamp;
    private Date endTimestamp;
    private Location location;

    public EventQuest(
            int id,
            String name,
            String type,
            String description,
            String requirements,
            int questRank,
            String successConditions,
            Date startTimestamp,
            Date endTimestamp,
            Location location) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.requirements = requirements;
        this.questRank = questRank;
        this.successConditions = successConditions;
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getRequirements() {
        return requirements;
    }

    public int getQuestRank() {
        return questRank;
    }

    public String getSuccessConditions() {
        return successConditions;
    }

    public Date getStartTimestamp() {
        return startTimestamp;
    }

    public Date getEndTimestamp() {
        return endTimestamp;
    }

    public Location getLocation() {
        return location;
    }

    public class Location {

        private int id;
        private String name;

        public Location(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

    }

}
