package com.example.wagba_android_application.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.wagba_android_application.model.Profile;
import com.example.wagba_android_application.repository.ProfileRepository;

public class ProfileViewModel extends AndroidViewModel {
    private ProfileRepository mRepository;

    private LiveData<Profile> profile;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        mRepository = new ProfileRepository(application);
    }

    public LiveData<Profile> getProfileOfUser( String email){
        profile = mRepository.getProfileOfUser(email);
        return profile;
    }

    public void insert(Profile profile) { mRepository.insert(profile); }

    public void insertFirstTime(Profile profile) { mRepository.insertFirstTime(profile); }

}
