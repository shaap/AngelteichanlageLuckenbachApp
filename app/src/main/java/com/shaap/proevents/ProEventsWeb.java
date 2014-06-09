package com.shaap.proevents;

import android.content.ContentValues;
import android.os.StrictMode;
import android.util.JsonReader;

import com.shaap.angelteichanlage.de.JsonParser;
import com.shaap.angelteichanlage.de.util.ISO8601DateParser;
import com.shaap.proevents.types.PlaceItem;
import com.shaap.proevents.types.ProEventsItem;

import org.apache.http.client.params.HttpClientParams;
import org.apache.http.params.HttpParams;

import java.util.ArrayList;
import java.util.List;

import static android.os.StrictMode.setThreadPolicy;

/**
 * Created by Sebastian on 25.05.2014.
 */
public class ProEventsWeb {
    private static final String EVENTS_URL = "http://www.angelteichanlage.de/app/proevents/eventinfo.php";
    public static final String EVENTS_MAP_URL = "http://www.angelteichanlage.de/app/proevents/mapbg.php";
    public static ProEventsItem getEvent (int id) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        setThreadPolicy(policy);
        ProEventsItem peiTemp = new ProEventsItem();

        try {
            JsonReader reader = JsonParser.getJSONFromUrl(EVENTS_URL + "?event=" + id);

                reader.beginObject();

                while (reader.hasNext()) {
                    String name = reader.nextName();
                    if (name.equals("id")) {
                        peiTemp.setId(Integer.parseInt(reader.nextString()));
                    } else if (name.equals("title")) {
                        peiTemp.setTitle(reader.nextString());
                    } else if (name.equals("description")) {
                        peiTemp.setDescription(reader.nextString());
                    } else if (name.equals("start")) {
                        peiTemp.setStart(ISO8601DateParser.parse(reader.nextString()));
                    } else if (name.equals("end")) {
                        peiTemp.setEnd(ISO8601DateParser.parse(reader.nextString()));
                    } else if (name.equals("price")) {
                        peiTemp.setPrice(Integer.parseInt(reader.nextString()));
                    } else if (name.equals("room")) {
                        peiTemp.setRoom(Integer.parseInt(reader.nextString()));
                    } else if (name.equals("seats")) {
                        List<PlaceItem> Seats = new ArrayList<PlaceItem>();
                        reader.beginArray();
                        while (reader.hasNext()) {
                            reader.beginObject();
                            PlaceItem Seat = new PlaceItem();
                            while (reader.hasNext()) {
                                String name2 = reader.nextName();
                                if (name.equals("id")) {
                                    Seat.setId(Integer.parseInt(reader.nextString()));
                                } else if (name.equals("x")) {
                                    Seat.setX(Integer.parseInt(reader.nextString()));
                                } else if (name.equals("y")) {
                                    Seat.setY(Integer.parseInt(reader.nextString()));
                                } else if (name.equals("booked")) {
                                    Seat.setEnabled(!Boolean.parseBoolean(reader.nextString()));
                                } else {
                                    reader.skipValue();
                                }
                            }
                            Seats.add(Seat);
                            reader.endObject();
                        }
                        reader.endArray();
                        peiTemp.setPlaces(Seats);
                    } else {
                        reader.skipValue();
                    }
                }
                reader.endObject();

            reader.close();

        } catch (Exception ex) {
        ex.printStackTrace();
        }
        return peiTemp;
    }

}
