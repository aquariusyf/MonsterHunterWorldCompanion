package com.yzhang.monsterhunterworldcompanion.appdatabase.events;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class EventQuest implements Parcelable {

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

    protected EventQuest(Parcel in) {
        id = in.readInt();
        name = in.readString();
        type = in.readString();
        description = in.readString();
        requirements = in.readString();
        questRank = in.readInt();
        successConditions = in.readString();
        location = in.readParcelable(Location.class.getClassLoader());
    }

    public static final Creator<EventQuest> CREATOR = new Creator<EventQuest>() {
        @Override
        public EventQuest createFromParcel(Parcel in) {
            return new EventQuest(in);
        }

        @Override
        public EventQuest[] newArray(int size) {
            return new EventQuest[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(type);
        dest.writeString(description);
        dest.writeString(requirements);
        dest.writeInt(questRank);
        dest.writeString(successConditions);
        dest.writeParcelable(location, flags);
    }

    public static class Location implements Parcelable{

        private int id;
        private String name;

        public Location(int id, String name) {
            this.id = id;
            this.name = name;
        }

        protected Location(Parcel in) {
            id = in.readInt();
            name = in.readString();
        }

        public static final Creator<Location> CREATOR = new Creator<Location>() {
            @Override
            public Location createFromParcel(Parcel in) {
                return new Location(in);
            }

            @Override
            public Location[] newArray(int size) {
                return new Location[size];
            }
        };

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeString(name);
        }
    }

}
