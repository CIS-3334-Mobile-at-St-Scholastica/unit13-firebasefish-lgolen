package css.cis3334.fishlocatorfirebase;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cssuser on 4/20/2017.
 */

public class FishFirebaseData {

    public static final String FishDataTag = "Fish Data";
    FirebaseDatabase database;
    DatabaseReference myFishDbRef;

    public DatabaseReference open()  {
        // Get an instance of the database and a reference to the fish data in it
        database = FirebaseDatabase.getInstance();
        myFishDbRef = database.getReference(FishDataTag);
        return myFishDbRef;
    }

    public void close() {

    }

    public Fish createFish( String species, String weightInOz, String dateCaught) {           //Added String rating as a parameter
        // ---- Get a new database key for the vote
        String key = myFishDbRef.child("Fish").push().getKey();
        // ---- set up the fish object
        Fish newFish = new Fish(key, species, weightInOz, dateCaught);
        // ---- write the vote to Firebase
        myFishDbRef.child(key).setValue(newFish);
        return newFish;
    }

    public Fish createFish( String species, String weightInOz, String dateCaught, String locationLatitude, String locationLongitude) {           //Added String rating as a parameter
        // ---- Get a new database key for the vote
        String key = myFishDbRef.child("Fish").push().getKey();
        // ---- set up the fish object
        Fish newFish = new Fish(species, weightInOz, dateCaught, locationLatitude, locationLongitude);
        // ---- write the vote to Firebase
        myFishDbRef.child(key).setValue(newFish);
        return newFish;
    }

    public void deleteFish(Fish fish) {
        String key = fish.getKey();
        myFishDbRef.child(key).removeValue();
    }

    public List<Fish> getAllFish(DataSnapshot dataSnapshot) {
        List<Fish> fishList = new ArrayList<>();
        for (DataSnapshot data : dataSnapshot.getChildren()) {
            Fish fish = data.getValue(Fish.class);
            fishList.add(fish);
        }
        return fishList;
    }

}
