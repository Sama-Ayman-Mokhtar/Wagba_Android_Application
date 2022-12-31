package com.example.wagba_android_application.repository;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import com.example.wagba_android_application.database.ProfileDao;
import com.example.wagba_android_application.database.myRoomDB;
import com.example.wagba_android_application.model.Profile;

public class ProfileRepository {
    private ProfileDao profileDao;
    private LiveData<Profile> profile;

    public ProfileRepository(Application application){
        myRoomDB db = myRoomDB.getDatabase(application);
        profileDao = db.profileDao();
    }

    public LiveData<Profile> getProfileOfUser(String email) {
        profile = profileDao.getProfileOfUser(email);
        return profile;
    }

    public void insert (Profile profile){
        new ProfileRepository.insertAsyncTask(profileDao).execute(profile);
    }

    private static class insertAsyncTask extends AsyncTask<Profile, Void, Void> {

        private ProfileDao mAsyncTaskDao;

        insertAsyncTask(ProfileDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Profile... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void insertFirstTime (Profile profile){
        new ProfileRepository.insertFirstTimeAsyncTask(profileDao).execute(profile);
    }

    private static class insertFirstTimeAsyncTask extends AsyncTask<Profile, Void, Void> {

        private ProfileDao mAsyncTaskDao;

        insertFirstTimeAsyncTask(ProfileDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Profile... params) {
            mAsyncTaskDao.insertFirstTime(params[0]);
            return null;
        }
    }
}




