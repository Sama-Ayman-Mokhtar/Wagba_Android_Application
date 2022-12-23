package com.example.wagba_android_application.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.wagba_android_application.model.Profile;

@Dao
public interface ProfileDao {

    @Query("SELECT * from profile_table  WHERE email = :email")
    LiveData<Profile> getProfileOfUser(String email);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Profile profile);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertFirstTime(Profile profile);
}
