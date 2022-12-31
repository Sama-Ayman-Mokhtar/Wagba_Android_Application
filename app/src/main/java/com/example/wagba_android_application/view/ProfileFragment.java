package com.example.wagba_android_application.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.wagba_android_application.R;
import com.example.wagba_android_application.adapter.DishesAdapter;
import com.example.wagba_android_application.model.Dish;
import com.example.wagba_android_application.model.Profile;
import com.example.wagba_android_application.viewmodel.DishViewModel;
import com.example.wagba_android_application.viewmodel.ProfileViewModel;
import com.example.wagba_android_application.viewmodel.RestaurantViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import java.util.List;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mProfileViewModel;
    EditText username;
    EditText phoneNum;
    TextView emailTxt;
    Button saveBtn;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        username = view.findViewById(R.id.person_name);
        phoneNum = view.findViewById(R.id.editTextPhone);
        emailTxt = view.findViewById(R.id.email);
        saveBtn = view.findViewById(R.id.save_btn);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(getActivity(), gso);
        mProfileViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(ProfileViewModel.class);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());

        if(acct!=null){
            emailTxt.setText(acct.getEmail());
            String email = acct.getEmail();
            String name = acct.getDisplayName();
            Profile profile = new Profile(email,name,null);
            mProfileViewModel.insertFirstTime(profile);
        }else{
            emailTxt.setText("test@test.com");
        }

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailTxt.getText().toString();
                String phone = phoneNum.getText().toString();
                String name = username.getText().toString();
                Profile profile = new Profile(email,name,phone);
                mProfileViewModel.insert(profile);
            }
        });


        mProfileViewModel.getProfileOfUser(emailTxt.getText().toString()).observe(getActivity(), new Observer<Profile>() {
            @Override
            public void onChanged(@Nullable final Profile profile) {

                if(profile!=null){
                    emailTxt.setText(profile.getEmail());
                    phoneNum.setText(profile.getPhoneNum());
                    username.setText(profile.getUsername());
                }
            }
        });
        return view;
    }
}
